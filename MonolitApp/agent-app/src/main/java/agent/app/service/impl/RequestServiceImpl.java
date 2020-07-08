package agent.app.service.impl;

import agent.app.exception.NotFoundException;
import agent.app.model.Request;
import agent.app.model.enumeration.RequestStatusEnum;
import agent.app.repository.RequestRepository;
import agent.app.service.intf.AdService;
import agent.app.service.intf.AgentService;
import agent.app.service.intf.EndUserService;
import agent.app.service.intf.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private AdService adService;

    @Autowired
    private EndUserService endUserService;

    @Autowired
    private AgentService agentService;

    @Override
    public Request findById(Long id) {
        return requestRepository.findById(id).orElseThrow(() -> new NotFoundException("Zahtjev za iznajmljivanje vozila ne postoji"));
    }

    @Override
    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    @Override
    public Integer deleteById(Long id) {
        Request request = this.findById(id);
        this.delete(request);
        return 1;
    }

    @Override
    public String findRequestPublisherUserIdentifier(String email) {
        return agentService.findByEmail(email).getIdentifier();
    }

    @Override
    public List<Request> findAllByPublisherUserAndStatus(String email, String status) {
        return requestRepository.findAllByEndUserAndByStatus(email, RequestStatusEnum.valueOf(status));
    }

//    @Override
//    public Integer submitRequest(List<SubmitRequestDTO> submitRequestDTOS, String email) {
//        Boolean blocked = (Boolean) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.END_USER_IS_BLOCKED_ID_QUEUE_NAME, userId);
//        if (blocked) {
//            return 2;
//        } else {
//            UserFLNameDTO endUserFLName = new UserFLNameDTO();
//            try {
//                String endUserFLNameStr = (String) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.USER_FL_NAME_QUEUE_NAME, userId);
//                endUserFLName = objectMapper.readValue(endUserFLNameStr, UserFLNameDTO.class);
//            } catch (JsonProcessingException exception) {
//            }
//            for (Map.Entry<Long, SubmitRequestDTO> entry : submitRequestDTOS.entrySet()) {
//                SubmitRequestDTO itemSubmitRequestDTO = entry.getValue();
//                UserFLNameDTO publisherUserFLName = new UserFLNameDTO();
//                try {
//                    String publisherUserFLNameStr = (String) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.USER_FL_NAME_QUEUE_NAME, entry.getKey());
//                    publisherUserFLName = objectMapper.readValue(publisherUserFLNameStr, UserFLNameDTO.class);
//                } catch (JsonProcessingException exception) {
//                }
//                if (itemSubmitRequestDTO.getBundle()) {
//                    List<services.app.carrequestservice.model.Ad> ads = new ArrayList<>();
//                    for (AdRequestDTO adRequestDTO : itemSubmitRequestDTO.getAds()) {
//                        String adCarInfoDTOStr = (String) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.AD_CAR_INFO_QUEUE_NAME, adRequestDTO.getId());
//                        AdCarInfoDTO adCarInfoDTO = new AdCarInfoDTO();
//                        try {
//                            adCarInfoDTO = objectMapper.readValue(adCarInfoDTOStr, AdCarInfoDTO.class);
//                        } catch (JsonProcessingException exception) {
//                        }
//                        services.app.carrequestservice.model.Ad ad = services.app.carrequestservice.model.Ad.builder()
//                                .mainId(adRequestDTO.getId())
//                                .adName(adRequestDTO.getAdName())
//                                .token(adCarInfoDTO.getToken())
//                                .distanceLimit(adCarInfoDTO.getDistanceLimit())
//                                .distanceLimitFlag(DistanceLimitEnum.valueOf(adCarInfoDTO.getDistanceLimitFlag()))
//                                .pricePerDay(adCarInfoDTO.getPricePerDay())
//                                .pricePerKm(adCarInfoDTO.getPricePerKm())
//                                .pricePerKmCDW(adCarInfoDTO.getPricePerKmCDW())
//                                .cdw(adCarInfoDTO.getCdw())
//                                .review(null)
//                                .startDate(DateAPI.DateTimeStringToDateTimeFromFronted(adRequestDTO.getStartDate()))
//                                .endDate(DateAPI.DateTimeStringToDateTimeFromFronted(adRequestDTO.getEndDate()))
//                                .build();
//                        ad = adService.save(ad);
//                        ads.add(ad);
//                    }
//                    services.app.carrequestservice.model.Request request = services.app.carrequestservice.model.Request.builder()
//                            .submitDate(DateAPI.DateTimeNow())
//                            .status(services.app.carrequestservice.model.RequestStatusEnum.PENDING)
//                            .ads(ads)
//                            .bundle(itemSubmitRequestDTO.getBundle())
//                            .endUserId(endUserFLName.getUserId())
//                            .endUserFirstName(endUserFLName.getUserFirstName())
//                            .endUserLastName(endUserFLName.getUserLastName())
//                            .endUserEmail(endUserFLName.getUserEmail())
//                            .publisherUserId(publisherUserFLName.getUserId())
//                            .publisherUserFirstName(publisherUserFLName.getUserFirstName())
//                            .publisherUserLastName(publisherUserFLName.getUserLastName())
//                            .publisherUserEmail(publisherUserFLName.getUserEmail())
//                            .build();
//                    this.save(request);
//                } else {
//                    for (AdRequestDTO adRequestDTO : itemSubmitRequestDTO.getAds()) {
//                        List<services.app.carrequestservice.model.Ad> ads = new ArrayList<>();
//                        String adCarInfoDTOStr = (String) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.AD_CAR_INFO_QUEUE_NAME, adRequestDTO.getId());
//                        AdCarInfoDTO adCarInfoDTO = new AdCarInfoDTO();
//                        try {
//                            adCarInfoDTO = objectMapper.readValue(adCarInfoDTOStr, AdCarInfoDTO.class);
//                        } catch (JsonProcessingException exception) {
//                        }
//                        services.app.carrequestservice.model.Ad ad = Ad.builder()
//                                .mainId(adRequestDTO.getId())
//                                .adName(adRequestDTO.getAdName())
//                                .token(adCarInfoDTO.getToken())
//                                .distanceLimit(adCarInfoDTO.getDistanceLimit())
//                                .distanceLimitFlag(DistanceLimitEnum.valueOf(adCarInfoDTO.getDistanceLimitFlag()))
//                                .pricePerDay(adCarInfoDTO.getPricePerDay())
//                                .pricePerKm(adCarInfoDTO.getPricePerKm())
//                                .pricePerKmCDW(adCarInfoDTO.getPricePerKmCDW())
//                                .cdw(adCarInfoDTO.getCdw())
//                                .review(null)
//                                .startDate(DateAPI.DateTimeStringToDateTimeFromFronted(adRequestDTO.getStartDate()))
//                                .endDate(DateAPI.DateTimeStringToDateTimeFromFronted(adRequestDTO.getEndDate()))
//                                .build();
//                        ad = adService.save(ad);
//                        ads.add(ad);
//                        services.app.carrequestservice.model.Request request = services.app.carrequestservice.model.Request.builder()
//                                .submitDate(DateAPI.DateTimeNow())
//                                .status(RequestStatusEnum.PENDING)
//                                .ads(ads)
//                                .bundle(itemSubmitRequestDTO.getBundle())
//                                .endUserId(endUserFLName.getUserId())
//                                .endUserFirstName(endUserFLName.getUserFirstName())
//                                .endUserLastName(endUserFLName.getUserLastName())
//                                .endUserEmail(endUserFLName.getUserEmail())
//                                .publisherUserId(publisherUserFLName.getUserId())
//                                .publisherUserFirstName(publisherUserFLName.getUserFirstName())
//                                .publisherUserLastName(publisherUserFLName.getUserLastName())
//                                .publisherUserEmail(publisherUserFLName.getUserEmail())
//                                .build();
//                        this.save(request);
//                    }
//                }
//
//            }
//
//            return 1;
//        }
//    }

    @Override
    public void delete(Request request) {
        requestRepository.delete(request);
    }

    @Override
    public Request save(Request request) {
        return requestRepository.save(request);
    }
}
