package services.app.pricelistanddiscountservice.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import services.app.pricelistanddiscountservice.client.AdClient;
import services.app.pricelistanddiscountservice.client.AuthenticationClient;
import services.app.pricelistanddiscountservice.config.RabbitMQConfiguration;
import services.app.pricelistanddiscountservice.converter.PriceListConverter;
import services.app.pricelistanddiscountservice.dto.AgentFirmIdentificationDTO;
import services.app.pricelistanddiscountservice.dto.pricelist.EditedPriceListDTO;
import services.app.pricelistanddiscountservice.dto.pricelist.PriceListCreateDTO;
import services.app.pricelistanddiscountservice.dto.sync.PriceListSyncDTO;
import services.app.pricelistanddiscountservice.exception.ExistsException;
import services.app.pricelistanddiscountservice.exception.NotFoundException;
import services.app.pricelistanddiscountservice.model.CustomPrincipal;
import services.app.pricelistanddiscountservice.model.PriceList;
import services.app.pricelistanddiscountservice.repository.PriceListRepository;
import services.app.pricelistanddiscountservice.service.intf.PriceListService;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PriceListServiceImpl implements PriceListService {

    @Autowired
    PriceListRepository priceListRepository;

    @Autowired
    private AuthenticationClient authenticationClient;

    @Autowired
    private AdClient adClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public PriceList findById(Long id) {
        return priceListRepository.findById(id).orElseThrow(() -> new NotFoundException("Cenovnik ne postoji."));
    }

    @Override
    public List<PriceList> findAll() {
        return priceListRepository.findAll();
    }

    @Override
    public List<PriceListCreateDTO> findAllListDTO() {
        return PriceListConverter.fromEntityList(this.findAll(), PriceListConverter::toCreatePriceListCreateDTOFromPriceList);
    }

    @Override
    public List<PriceListCreateDTO> findAllListDTOFromPublisher(Long userId) {
        List<PriceList> priceListsFromPublishUser = priceListRepository.findAllByPublisherUser(userId);
        List<PriceListCreateDTO> ret = priceListsFromPublishUser.stream().map(PriceListConverter::toCreatePriceListCreateDTOFromPriceList).collect(Collectors.toList());
        return ret;
    }

    @Override
    @RabbitListener(queues = RabbitMQConfiguration.PL_GET_QUEUE_NAME)
    public String findPriceListById(Long id) {
        try {
            PriceList priceList = this.findById(id);
            return objectMapper.writeValueAsString(priceList);
        }catch (JsonProcessingException exception){
            return null;
        }
    }

    @Override
    public PriceList save(PriceList priceList) {
        if (priceList.getId() != null) {
            if (priceListRepository.existsById(priceList.getId())) {
                throw new ExistsException(String.format("Cenovnik vec postoji."));
            }
        }

        return priceListRepository.save(priceList);
    }

    @Override
    public void delete(PriceList priceList) {
        priceListRepository.delete(priceList);
    }

    @Override
    @RabbitListener(queues = RabbitMQConfiguration.PL_NEW_EDIT_QUEUE_NAME)
    public Long createPriceListRMQ(String msg) {
        try {
            PriceListCreateDTO priceListCreateDTO = objectMapper.readValue(msg, PriceListCreateDTO.class);
            PriceList priceList = PriceListConverter.toCreatePriceListFromRequest(priceListCreateDTO);
            priceList = this.priceListRepository.save(priceList);
            return priceList.getId();
        } catch (JsonProcessingException exception) {
            return null;
        }
    }

    @Override
    public PriceList createPriceList(PriceListCreateDTO priceListCreateDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();
        PriceList priceList = PriceListConverter.toCreatePriceListFromRequest(priceListCreateDTO);
        priceList.setPublisherUser(Long.valueOf(principal.getUserId()));
        priceList = this.priceListRepository.save(priceList);
        return priceList;
    }

    @Override
    public Integer editPriceList(PriceList priceList) {
        PriceList priceList1 = this.findById(priceList.getId());
        priceList1.setPricePerDay(priceList.getPricePerDay());
        priceList1.setPricePerKm(priceList.getPricePerKm());
        priceList1.setPricePerKmCDW(priceList.getPricePerKmCDW());
        priceList1 = priceListRepository.save(priceList1);
        try{
            EditedPriceListDTO editedPriceListDTO = EditedPriceListDTO.builder()
                    .priceListId(priceList1.getId())
                    .pricePerDay(priceList1.getPricePerDay())
                    .build();
            String editedPriceListDTOStr  = objectMapper.writeValueAsString(editedPriceListDTO);
            rabbitTemplate.convertAndSend(RabbitMQConfiguration.UPDATE_PL_AD_QUEUE_NAME, editedPriceListDTOStr);
        }catch (JsonProcessingException exception){
            exception.printStackTrace();
        }
        return 1;
    }

    @Override
    public Integer deleteById(Long id) {
        PriceList priceList = this.findById(id);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();
        List<Long> usedPricelists = adClient.getPricelistFromAds(principal.getUserId(), principal.getEmail(), principal.getRoles(), principal.getToken());
        if(!usedPricelists.contains(id)){
            System.out.println("ne sadrzi cenovnik mozes obrisati.");
            this.delete(priceList);
            return 1;
        }
        System.out.println("sadrzi cenovnik ne  mozes obrisati.");
        return 2;

    }

    @Override
    @RabbitListener(queues = RabbitMQConfiguration.PL_SYNC_QUEUE_NAME)
    public Long syncPriceList(String msg) {
        try {
            PriceListSyncDTO priceListSyncDTO = objectMapper.readValue(msg, PriceListSyncDTO.class);
            PriceList priceList = PriceListConverter.toPriceListFromPriceListSyncDTO(priceListSyncDTO);
            Long userId = (Long) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.USER_ID_QUEUE_NAME, priceListSyncDTO.getEmail());
            priceList.setPublisherUser(userId);
            priceList = this.save(priceList);
            return priceList.getId();
        } catch (JsonProcessingException exception) {
            return null;
        }
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
    public Long createPriceListFromAgent(Long publisherUser, DateTime creationDate, Float pricePerDay,
                                         Float pricePerKm, Float pricePerKmCDW) {
        PriceList priceList = new PriceList();
        priceList.setCreationDate(creationDate);
        priceList.setPricePerDay(pricePerDay);
        priceList.setPricePerKm(pricePerKm);
        priceList.setPricePerKmCDW(pricePerKmCDW);
        priceList.setPublisherUser(publisherUser);
        priceList = this.priceListRepository.save(priceList);
        return priceList.getId();
    }

    @Override
    public Long editPriceListFromAgent(Float pricePerDay, Float pricePerKm, Float pricePerKmCDW, Long mainId) {
        PriceList priceList = this.findById(mainId);
        priceList.setPricePerDay(pricePerDay);
        priceList.setPricePerKm(pricePerKm);
        priceList.setPricePerKmCDW(pricePerKmCDW);
        priceList = this.priceListRepository.save(priceList);
        try{
            EditedPriceListDTO editedPriceListDTO = EditedPriceListDTO.builder()
                    .priceListId(priceList.getId())
                    .pricePerDay(priceList.getPricePerDay())
                    .build();
            String editedPriceListDTOStr  = objectMapper.writeValueAsString(editedPriceListDTO);
            rabbitTemplate.convertAndSend(RabbitMQConfiguration.UPDATE_PL_AD_QUEUE_NAME, editedPriceListDTOStr);
        }catch (JsonProcessingException exception){
            exception.printStackTrace();
        }
        return priceList.getId();
    }

    @Override
    public Long deletePriceListFromAgent(Long mainId) {
        PriceList priceList = this.findById(mainId);
        System.out.println("ne sadrzi cenovnik mozes obrisati.");
        this.delete(priceList);
        return mainId;
    }


}
