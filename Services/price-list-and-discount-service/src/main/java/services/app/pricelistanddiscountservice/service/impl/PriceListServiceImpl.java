package services.app.pricelistanddiscountservice.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import services.app.pricelistanddiscountservice.client.AuthenticationClient;
import services.app.pricelistanddiscountservice.config.RabbitMQConfiguration;
import services.app.pricelistanddiscountservice.converter.PriceListConverter;
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
        this.findById(priceList.getId());
        PriceList priceList1 = priceListRepository.save(priceList);
        return 1;
    }

    @Override
    public Integer deleteById(Long id) {
        PriceList priceList = this.findById(id);
        this.delete(priceList);
        return 1;
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


}
