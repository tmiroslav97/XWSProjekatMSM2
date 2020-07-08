package services.app.carrequestservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.app.carrequestservice.config.RabbitMQConfiguration;
import services.app.carrequestservice.dto.UserFLNameDTO;
import services.app.carrequestservice.dto.ad.AdRatingDTO;
import services.app.carrequestservice.exception.NotFoundException;
import services.app.carrequestservice.model.Ad;
import services.app.carrequestservice.model.Request;
import services.app.carrequestservice.repository.AdRepository;
import services.app.carrequestservice.service.intf.AdService;
import services.app.carrequestservice.service.intf.RequestService;

import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private RequestService requestService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Ad findById(Long id) {
        return adRepository.findById(id).orElseThrow(() -> new NotFoundException("Oglas ne postoji"));
    }

    @Override
    public List<Ad> findAll() {
        return adRepository.findAll();
    }

    @Override
    public Integer deleteById(Long id) {
        Ad ad = this.findById(id);
        this.delete(ad);
        return 1;
    }

    @Override
    @RabbitListener(queues = RabbitMQConfiguration.RATE_AD_QUEUE_NAME)
    public Integer rateAd(String msg) {
        try{
            AdRatingDTO adRatingDTO = objectMapper.readValue(msg, AdRatingDTO.class);
            Ad ad = this.findById(adRatingDTO.getAdId());
            if(ad.getReview()==null) {
                ad.setReview(Integer.valueOf(adRatingDTO.getRating().toString()));
                this.save(ad);
                Request request = requestService.findById(adRatingDTO.getRequestId());
                String userFLNameStr = (String) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.USER_FL_NAME_QUEUE_NAME, request.getPublisherUserId());
                UserFLNameDTO userFLName = objectMapper.readValue(userFLNameStr, UserFLNameDTO.class);
                if (!userFLName.getLocal()) {
                    String routingKey = userFLName.getUserEmail().replace("@", ".") + ".req";
                    String requestStr = objectMapper.writeValueAsString(request);
                    rabbitTemplate.convertAndSend(RabbitMQConfiguration.AGENT_SYNC_QUEUE_NAME, routingKey, requestStr);
                }
                return 1;
            }else{
                return 2;
            }
        }catch (JsonProcessingException exception){
            return  3;
        }
    }

    @Override
    public Boolean existsById(Long id) {
        return adRepository.existsById(id);
    }

    @Override
    public void delete(Ad ad) {
        adRepository.delete(ad);
    }

    @Override
    public Ad save(Ad ad) {
        return adRepository.save(ad);
    }
}
