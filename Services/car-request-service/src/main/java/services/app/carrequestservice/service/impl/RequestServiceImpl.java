package services.app.carrequestservice.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.app.carrequestservice.client.AdServiceClient;
import services.app.carrequestservice.client.AuthenticationClient;
import services.app.carrequestservice.config.RabbitMQConfiguration;
import services.app.carrequestservice.converter.DateAPI;
import services.app.carrequestservice.converter.RequestConverter;
import services.app.carrequestservice.dto.AgentFirmIdentificationDTO;
import services.app.carrequestservice.dto.ad.AdCarInfoDTO;
import services.app.carrequestservice.dto.ad.AdRequestDTO;
import services.app.carrequestservice.dto.carreq.AcceptReqestCalendarTermsDTO;
import services.app.carrequestservice.dto.carreq.SubmitRequestDTO;
import services.app.carrequestservice.exception.NotFoundException;
import services.app.carrequestservice.model.*;
import services.app.carrequestservice.repository.RequestRepository;
import services.app.carrequestservice.service.intf.AdService;
import services.app.carrequestservice.service.intf.RequestService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private AdService adService;

    @Autowired
    private AuthenticationClient authenticationClient;

    @Autowired
    private AdServiceClient adServiceClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Request findById(Long id) {
        return requestRepository.findById(id).orElseThrow(() -> new NotFoundException("Zahtjev za iznajmljivanje vozila ne postoji"));
    }

    @Override
    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    @Override
    public List<Request> findAllByEndUserId(Long id) {
        return requestRepository.findAllByEndUser(id);
    }

    @Override
    public List<Request> findAllByEndUserIdAndByStatus(Long id, String status) {
        return requestRepository.findAllByEndUserAndByStatus(id, RequestStatusEnum.valueOf(status));
    }

    @Override
    public List<Request> findAllByPublisherUserId(Long id) {
        return requestRepository.findAllByPublisherUser(id);
    }

    @Override
    public List<Request> findAllByPublisherUserEmail(String email, String identifier) {
        Long publisherUserId = this.authAgent(email, identifier);
        if (publisherUserId != null) {
            return this.findAllByPublisherUserId(authenticationClient.findPublishUserByEmailWS(email));
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Request> findAllByPublisherUserEmailAndStatus(String email, String identifier, String status) {
        Long publisherUserId = this.authAgent(email, identifier);
        if (publisherUserId != null) {
            return this.findAllByPublisherUserIdAndByStatus(authenticationClient.findPublishUserByEmailWS(email), status);
        } else {
            return new ArrayList<>();
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
    public void deleteAllWithSameAdId(List<Ad> ads) {
        List<Request> requests = requestRepository.findRequestByAds(ads);
        for (Request req : requests) {
            req.setStatus(RequestStatusEnum.CANCELED);
            this.save(req);
        }

    }

    @Override
    public String acceptRequest(AcceptRequest acceptRequest) {
        Long publisherUser = this.authAgent(acceptRequest.getPublisherUserEmail(), acceptRequest.getIdentifier());
        if (publisherUser == null) {
            return "Agent nije registrovan u sistemu";
        }
        Request request = this.findById(acceptRequest.getId());
        if (acceptRequest.getAction().equals("reject")) {
            request.setStatus(RequestStatusEnum.CANCELED);
            this.save(request);
            return "Uspjesno odbijen zahtjev";
        }
        AcceptReqestCalendarTermsDTO acceptReqestCalendarTermsDTO = RequestConverter.toCreateAcceptRequestCalendarTermsDTO(request);
        Boolean flag = adServiceClient.acceptCarCalendar(acceptReqestCalendarTermsDTO);
        if (flag) {
            if (acceptRequest.getAction().equals("accept")) {
                request.setStatus(RequestStatusEnum.PAID);
                this.save(request);
                //this.deleteAllWithSameAdId(request.getAds());
                return "Uspjesno prihvacen zahtjev";
            } else {
                return "Nepoznata akcija";
            }
        } else {
            return "Nije moguce prihvatiti zahtjev";
        }
    }

    @Override
    public List<Request> findAllByPublisherUserIdAndByStatus(Long id, String status) {
        return requestRepository.findAllByPublisherUserAndByStatus(id, RequestStatusEnum.valueOf(status));
    }

    @Override
    public Integer deleteById(Long id) {
        Request request = this.findById(id);
        this.delete(request);
        return 1;
    }

    @Override
    public Integer submitRequest(HashMap<Long, SubmitRequestDTO> submitRequestDTOS, Long userId) {
        Boolean blocked = (Boolean) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.END_USER_IS_BLOCKED_ID_QUEUE_NAME, userId);
        if (blocked) {
            return 2;
        } else {
            for (Map.Entry<Long, SubmitRequestDTO> entry : submitRequestDTOS.entrySet()) {
                SubmitRequestDTO itemSubmitRequestDTO = entry.getValue();
                if (itemSubmitRequestDTO.getBundle()) {
                    List<Ad> ads = new ArrayList<>();
                    for (AdRequestDTO adRequestDTO : itemSubmitRequestDTO.getAds()) {
                        String adCarInfoDTOStr = (String) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.AD_CAR_INFO_QUEUE_NAME, adRequestDTO.getId());
                        AdCarInfoDTO adCarInfoDTO = new AdCarInfoDTO();
                        try {
                            adCarInfoDTO = objectMapper.readValue(adCarInfoDTOStr, AdCarInfoDTO.class);
                        } catch (JsonProcessingException exception) {
                        }
                        Ad ad = Ad.builder()
                                .mainId(adRequestDTO.getId())
                                .adName(adRequestDTO.getAdName())
                                .distanceLimit(adCarInfoDTO.getDistanceLimit())
                                .distanceLimitFlag(DistanceLimitEnum.valueOf(adCarInfoDTO.getDistanceLimitFlag()))
                                .pricePerDay(adCarInfoDTO.getPricePerDay())
                                .pricePerKm(adCarInfoDTO.getPricePerKm())
                                .pricePerKmCDW(adCarInfoDTO.getPricePerKmCDW())
                                .cdw(adCarInfoDTO.getCdw())
                                .rated(false)
                                .startDate(DateAPI.DateTimeStringToDateTimeFromFronted(adRequestDTO.getStartDate()))
                                .endDate(DateAPI.DateTimeStringToDateTimeFromFronted(adRequestDTO.getEndDate()))
                                .build();
                        ad = adService.save(ad);
                    }
                    Request request = Request.builder()
                            .submitDate(DateAPI.DateTimeNow())
                            .status(RequestStatusEnum.PENDING)
                            .ads(ads)
                            .bundle(itemSubmitRequestDTO.getBundle())
                            .publisherUser(entry.getKey())
                            .endUser(userId)
                            .build();
                    this.save(request);
                } else {
                    for (AdRequestDTO adRequestDTO : itemSubmitRequestDTO.getAds()) {
                        List<Ad> ads = new ArrayList<>();
                        String adCarInfoDTOStr = (String) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.AD_CAR_INFO_QUEUE_NAME, adRequestDTO.getId());
                        AdCarInfoDTO adCarInfoDTO = new AdCarInfoDTO();
                        try {
                            adCarInfoDTO = objectMapper.readValue(adCarInfoDTOStr, AdCarInfoDTO.class);
                        } catch (JsonProcessingException exception) {
                        }
                        Ad ad = Ad.builder()
                                .mainId(adRequestDTO.getId())
                                .adName(adRequestDTO.getAdName())
                                .distanceLimit(adCarInfoDTO.getDistanceLimit())
                                .distanceLimitFlag(DistanceLimitEnum.valueOf(adCarInfoDTO.getDistanceLimitFlag()))
                                .pricePerDay(adCarInfoDTO.getPricePerDay())
                                .pricePerKm(adCarInfoDTO.getPricePerKm())
                                .pricePerKmCDW(adCarInfoDTO.getPricePerKmCDW())
                                .cdw(adCarInfoDTO.getCdw())
                                .rated(false)
                                .startDate(DateAPI.DateTimeStringToDateTimeFromFronted(adRequestDTO.getStartDate()))
                                .endDate(DateAPI.DateTimeStringToDateTimeFromFronted(adRequestDTO.getEndDate()))
                                .build();
                        ad = adService.save(ad);
                        ads.add(ad);
                        Request request = Request.builder()
                                .submitDate(DateAPI.DateTimeNow())
                                .status(RequestStatusEnum.PENDING)
                                .ads(ads)
                                .bundle(itemSubmitRequestDTO.getBundle())
                                .publisherUser(entry.getKey())
                                .endUser(userId)
                                .build();
                        this.save(request);
                    }
                }

            }

            return 1;
        }
    }

    @Override
    public void delete(Request request) {
        requestRepository.delete(request);
    }

    @Override
    public Request save(Request request) {
        return requestRepository.save(request);
    }
}
