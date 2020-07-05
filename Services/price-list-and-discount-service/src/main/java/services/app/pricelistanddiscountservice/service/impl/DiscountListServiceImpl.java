package services.app.pricelistanddiscountservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.app.pricelistanddiscountservice.config.RabbitMQConfiguration;
import services.app.pricelistanddiscountservice.converter.DiscountListConverter;
import services.app.pricelistanddiscountservice.dto.sync.DiscountListSyncDTO;
import services.app.pricelistanddiscountservice.exception.NotFoundException;
import services.app.pricelistanddiscountservice.model.DiscountList;
import services.app.pricelistanddiscountservice.repository.DiscountListRepository;
import services.app.pricelistanddiscountservice.service.intf.DiscountListService;

import java.util.List;

@Service
public class DiscountListServiceImpl implements DiscountListService {

    @Autowired
    private DiscountListRepository discountListRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public DiscountList findById(Long id) {
        return discountListRepository.findById(id).orElseThrow(() -> new NotFoundException("Popust ne postoji."));

    }

    @Override
    public List<DiscountList> findAll() {
        return discountListRepository.findAll();
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
        this.delete(discountList);
        return 1;
    }

    @Override
    public Integer edit(Long id) {
        return null;
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
            return discountList.getId();
        } catch (JsonProcessingException exception) {
            return null;
        }
    }
}
