package services.app.pricelistanddiscountservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import services.app.pricelistanddiscountservice.client.AdClient;
import services.app.pricelistanddiscountservice.client.AuthenticationClient;
import services.app.pricelistanddiscountservice.config.RabbitMQConfiguration;
import services.app.pricelistanddiscountservice.converter.DiscountListConverter;
import services.app.pricelistanddiscountservice.dto.AgentFirmIdentificationDTO;
import services.app.pricelistanddiscountservice.dto.discount.DiscountAndAdDTO;
import services.app.pricelistanddiscountservice.dto.discount.DiscountInfoDTO;
import services.app.pricelistanddiscountservice.dto.discount.DiscountListCreateDTO;
import services.app.pricelistanddiscountservice.dto.discount.DiscountListDTO;
import services.app.pricelistanddiscountservice.dto.sync.DiscountListSyncDTO;
import services.app.pricelistanddiscountservice.exception.NotFoundException;
import services.app.pricelistanddiscountservice.model.CustomPrincipal;
import services.app.pricelistanddiscountservice.model.DiscountList;
import services.app.pricelistanddiscountservice.repository.DiscountListRepository;
import services.app.pricelistanddiscountservice.service.intf.DiscountListService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountListServiceImpl implements DiscountListService {

    @Autowired
    private DiscountListRepository discountListRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AuthenticationClient authenticationClient;

    @Autowired
    private AdClient adClient;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public DiscountList findById(Long id) {
        return discountListRepository.findById(id).orElseThrow(() -> new NotFoundException("Popust ne postoji."));

    }

    @Override
    public List<DiscountListDTO> findAll() {
        List<DiscountList> discountLists = discountListRepository.findAll();
        return DiscountListConverter.fromEntityList(discountLists, DiscountListConverter::toDiscountListDTOFromDiscountList);
    }

    @Override
    public List<DiscountList> findAllByAgent() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();
        return discountListRepository.findAllByAgent(Long.parseLong(principal.getUserId()));
    }

    @Override
    public List<DiscountListDTO> findAllByAgentDTO() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();

        List<DiscountList> discountLists = this.findAllByAgent();
        List<DiscountListDTO> discountListDTOS = new ArrayList<>();

        for (DiscountList dl : discountLists) {
            DiscountListDTO discountListDTO = DiscountListConverter.toDiscountListDTOFromDiscountList(dl);
            List<Long> adsId = adClient.getAdsFromDiscount(dl.getId(), principal.getUserId(), principal.getEmail(), principal.getRoles(), principal.getToken());
            discountListDTO.setAdsId(adsId);
            discountListDTOS.add(discountListDTO);
        }
        return discountListDTOS;
    }

    @Override
    public DiscountList save(DiscountList discountList) {
        return discountListRepository.save(discountList);
    }

    @Override
    public void delete(DiscountList discountList) {
        discountListRepository.delete(discountList);
    }

    @Override
    public Integer deleteById(Long id) {
        DiscountList discountList = this.findById(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();
        Integer rez = adClient.deleteDiscount(discountList.getId(), principal.getUserId(), principal.getEmail(), principal.getRoles(), principal.getToken());
        if (rez == 2) {
            return 2;
        }
        this.delete(discountList);
        return 1;

    }

    @Override
    public Integer edit(DiscountList discountList) {
        DiscountList discountList1 = this.findById(discountList.getId());
        discountList1.setDayNum(discountList.getDayNum());
        discountList1.setDiscount(discountList.getDiscount());
        discountList1 = discountListRepository.save(discountList1);
        return 1;
        //TODO 4: dodati edit i u ad servisu
    }

    @Override
    @RabbitListener(queues = RabbitMQConfiguration.DL_SYNC_QUEUE_NAME)
    public Long syncDiscountList(String msg) {
        try {
            DiscountListSyncDTO discountListSyncDTO = objectMapper.readValue(msg, DiscountListSyncDTO.class);
            DiscountList discountList = DiscountListConverter.toDiscountListFromDiscountListSyncDTO(discountListSyncDTO);
            Long userId = (Long) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.USER_ID_QUEUE_NAME, discountListSyncDTO.getEmail());
            discountList.setAgent(userId);
            discountList = this.save(discountList);
            rabbitTemplate.convertAndSend(RabbitMQConfiguration.ADD_DISCOUNT_QUEUE_NAME, discountList.getId());
            return discountList.getId();
        } catch (JsonProcessingException exception) {
            return null;
        }
    }

    @Override
    public Integer createDiscount(DiscountListCreateDTO discountListCreateDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();

        DiscountList discountList = DiscountListConverter.toDiscountListFromDiscountListCreateDTO(discountListCreateDTO);
        discountList.setAgent(Long.parseLong(principal.getUserId()));
        discountList = this.save(discountList);
        Integer i = adClient.addDiscount(discountList.getId(), principal.getUserId(), principal.getEmail(), principal.getRoles(), principal.getToken());
        return i;
    }

    @Override
    public Integer addDiscountToAd(Long discountId, Long adId) {
        System.out.println("dodavanjeeee");
        System.out.println(discountId + "    " + adId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();
        DiscountList discountList = this.findById(discountId);
        Integer i = adClient.addDiscountToAd(discountId, adId, principal.getUserId(), principal.getEmail(), principal.getRoles(), principal.getToken());
        System.out.println("USPESNOO DODAVANJE");
        return i;
    }

    @Override
    public Integer deleteDiscountFromAd(Long discountId, Long adId) {
        System.out.println("brisanjeee");
        System.out.println(discountId + "    " + adId);
        DiscountList discountList = this.findById(discountId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();
        Integer i = adClient.removeDiscountToAd(discountId, adId, principal.getUserId(), principal.getEmail(), principal.getRoles(), principal.getToken());
        return i;
    }

    @Override
    public Long authAgent(String email, String identifier) {
        AgentFirmIdentificationDTO agentFirmIdentificationDTO = AgentFirmIdentificationDTO.builder()
                .email(email)
                .identifier(identifier)
                .build();
        try {
            String agentFirmIdentificationDTOStr = objectMapper.writeValueAsString(agentFirmIdentificationDTO);
            Long publisherUserId = (Long) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.AGENT_ID_BY_EMAIL_ID_QUEUE_NAME, agentFirmIdentificationDTOStr);
            return publisherUserId;
        } catch (JsonProcessingException exception) {
            return null;
        }
    }

    @Override
    public Long addDiscountListFromAgent(Long publisherId, Integer dayNum, Float discount) {
        DiscountList discountList = new DiscountList();
        discountList.setAgent(publisherId);
        discountList.setDayNum(dayNum);
        discountList.setDiscount(discount);
        discountList = this.save(discountList);

        rabbitTemplate.convertAndSend(RabbitMQConfiguration.ADD_DISCOUNT_QUEUE_NAME, discountList.getId());

        return discountList.getId();
    }

    @Override
    public Long editDiscountListFromAgent(Long publisherId, Integer dayNum, Float discount, Long mainId) {
        DiscountList discountList = this.findById(mainId);
        discountList.setDayNum(dayNum);
        discountList.setDiscount(discount);
        discountList = discountListRepository.save(discountList);
        return discountList.getId();
    }

    @Override
    public Long deleteDiscountListFromAgent(Long publisherId, Long mainId) {
        DiscountList discountList = this.findById(mainId);
        this.delete(discountList);
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.DELETE_DISCOUNT_QUEUE_NAME, discountList.getId());
        return discountList.getId();
    }

    @Override
    public Long addDiscountListToAdFromAgent(Long publisherId, Long mainIdDiscount, Long mainIdAd) {
        DiscountList discountList = this.findById(mainIdDiscount);

        DiscountAndAdDTO discountAndAdDTO = new DiscountAndAdDTO();
        discountAndAdDTO.setMainIdAd(mainIdAd);
        discountAndAdDTO.setMainIdDiscount(mainIdDiscount);
        String string = null;
        try {
            string = objectMapper.writeValueAsString(discountAndAdDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.ADD_DISCOUNT_TO_AD_QUEUE_NAME, string);

        return discountList.getId();
    }

    @Override
    public Long removeDiscountListFromAdFromAgent(Long publisherId, Long mainIdDiscount, Long mainIdAd) {
        DiscountList discountList = this.findById(mainIdDiscount);

        DiscountAndAdDTO discountAndAdDTO = new DiscountAndAdDTO();
        discountAndAdDTO.setMainIdAd(mainIdAd);
        discountAndAdDTO.setMainIdDiscount(mainIdDiscount);
        String string = null;
        try {
            string = objectMapper.writeValueAsString(discountAndAdDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.DELETE_DISCOUNT_FROM_AD_QUEUE_NAME, string);

        return discountList.getId();
    }

    @Override
    @RabbitListener(queues = RabbitMQConfiguration.DISCOUNT_INFO_BY_ID_QUEUE_NAME)
    public String discountInfoById(Long id) {
        DiscountList discountList = this.findById(id);
        DiscountInfoDTO discountInfoDTO = DiscountInfoDTO.builder()
                .dayNum(discountList.getDayNum())
                .discount(discountList.getDiscount())
                .build();
        try {
            String discountInfoDTOStr = objectMapper.writeValueAsString(discountInfoDTO);
            return discountInfoDTOStr;
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();
            return null;
        }
    }

}
