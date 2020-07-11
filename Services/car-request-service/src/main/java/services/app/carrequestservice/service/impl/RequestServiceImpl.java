package services.app.carrequestservice.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.Days;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import services.app.carrequestservice.config.RabbitMQConfiguration;
import services.app.carrequestservice.converter.DateAPI;
import services.app.carrequestservice.converter.RequestConverter;
import services.app.carrequestservice.dto.AgentFirmIdentificationDTO;
import services.app.carrequestservice.dto.UserFLNameDTO;
import services.app.carrequestservice.dto.ad.AdCarInfoDTO;
import services.app.carrequestservice.dto.ad.AdRequestDTO;
import services.app.carrequestservice.dto.carreq.AcceptReqestCalendarTermsDTO;
import services.app.carrequestservice.dto.carreq.SubmitRequestDTO;
import services.app.carrequestservice.dto.discountlist.DiscountInfoDTO;
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
        return requestRepository.findAllByEndUserId(id);
    }

    @Override
    public List<Request> findAllByEndUserIdAndByStatus(Long id, String status) {
        return requestRepository.findAllByEndUserAndByStatus(id, RequestStatusEnum.valueOf(status));
    }

    @Override
    public List<Request> findAllByPublisherUserId(Long id) {
        return requestRepository.findAllByPublisherUserId(id);
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
    public Integer quitRequest(Long id) {
        Request request = this.findById(id);
        if (!request.getStatus().toString().equals("PENDING")) {
            return 2;
        }
        request.setStatus(RequestStatusEnum.CANCELED);
        request = this.save(request);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.END_USER_CANCELED_RENT_CNT_QUEUE_NAME, Long.valueOf(cp.getUserId()));
        try {
            String userFLNameStr = (String) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.USER_FL_NAME_QUEUE_NAME, request.getPublisherUserId());
            UserFLNameDTO userFLName = objectMapper.readValue(userFLNameStr, UserFLNameDTO.class);
            if (!userFLName.getLocal()) {
                String routingKey = userFLName.getUserEmail().replace("@", ".") + ".req";
                String requestStr = objectMapper.writeValueAsString(request);
                rabbitTemplate.convertAndSend(RabbitMQConfiguration.AGENT_SYNC_QUEUE_NAME, routingKey, requestStr);
            }
        } catch (JsonProcessingException exception) {
            return 3;
        }
        return 1;
    }

    @Override
    @Scheduled(cron = "${reject.cron}")
    public void autoRejectRequests() {
        List<Request> requests = requestRepository.findAllByStatusAndSubmitDate(RequestStatusEnum.PENDING, DateAPI.DateTimeNow().minusDays(1));
        requests.stream().forEach(request -> {
            request.setStatus(RequestStatusEnum.CANCELED);
            try {
                String userFLNameStr = (String) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.USER_FL_NAME_QUEUE_NAME, request.getPublisherUserId());
                UserFLNameDTO userFLName = objectMapper.readValue(userFLNameStr, UserFLNameDTO.class);
                if (!userFLName.getLocal()) {
                    String routingKey = userFLName.getUserEmail().replace("@", ".") + ".req";
                    String requestStr = objectMapper.writeValueAsString(request);
                    rabbitTemplate.convertAndSend(RabbitMQConfiguration.AGENT_SYNC_QUEUE_NAME, routingKey, requestStr);
                }
            } catch (JsonProcessingException exception) {
            }
        });
        requestRepository.saveAll(requests);
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
    public String acceptRequest(Long requestId, String action) {
        Request request = this.findById(requestId);
        if (!request.getStatus().toString().equals("PENDING")) {
            return "Zahtjev je vec obradjen";
        }
        if (action.equals("reject")) {
            request.setStatus(RequestStatusEnum.CANCELED);
            this.save(request);
            return "Uspjesno odbijen zahtjev";
        }
        if (!action.equals("accept")) {
            return "Nepoznata akcija";
        }
        AcceptReqestCalendarTermsDTO acceptReqestCalendarTermsDTO = RequestConverter.toCreateAcceptRequestCalendarTermsDTO(request);
        try {
            String acceptReqestCalendarTermsDTOStr = objectMapper.writeValueAsString(acceptReqestCalendarTermsDTO);
            Boolean flag = (Boolean) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.ACCEPT_REQUEST_QUEUE_NAME, acceptReqestCalendarTermsDTOStr);
            if (flag) {
                request.setStatus(RequestStatusEnum.PAID);
                this.save(request);
                this.rejectOtherRequests(request);
                return "Uspjesno prihvacen zahtjev";
            } else {
                return "Nije moguce prihvatiti zahtjev";
            }
        } catch (JsonProcessingException exception) {
            return "Nije moguce prihvatiti zahtjev";
        }

    }

    @Override
    public Boolean rejectOtherRequests(Request request) {
        for (Ad ad : request.getAds()) {
            List<Request> requests = requestRepository.findAllRequestContainsAdAndOverlapDate(request.getId(), ad.getMainId(), ad.getStartDate(), ad.getEndDate());
            requests.stream().forEach(request1 -> request1.setStatus(RequestStatusEnum.CANCELED));
            requestRepository.saveAll(requests);
        }
        return true;
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
    public Float findClossestDiscount(List<DiscountInfoDTO> discountInfoDTOS, Integer dayOfRent) {
        Float discount = null;
        discountInfoDTOS.sort((o1, o2) -> o1.getDayNum() - o2.getDayNum());
        for (DiscountInfoDTO discountInfoDTO : discountInfoDTOS) {
            if (discountInfoDTO.getDayNum() > dayOfRent) {
                break;
            }
            discount = discountInfoDTO.getDiscount();
        }
        return discount;
    }


    @Override
    public Integer submitRequest(HashMap<Long, SubmitRequestDTO> submitRequestDTOS, Long userId) {
        Boolean blocked = (Boolean) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.END_USER_IS_BLOCKED_ID_QUEUE_NAME, userId);
        if (blocked) {
            return 2;
        } else {
            UserFLNameDTO endUserFLName = new UserFLNameDTO();
            try {
                String endUserFLNameStr = (String) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.USER_FL_NAME_QUEUE_NAME, userId);
                endUserFLName = objectMapper.readValue(endUserFLNameStr, UserFLNameDTO.class);
            } catch (JsonProcessingException exception) {
            }
            for (Map.Entry<Long, SubmitRequestDTO> entry : submitRequestDTOS.entrySet()) {
                SubmitRequestDTO itemSubmitRequestDTO = entry.getValue();
                UserFLNameDTO publisherUserFLName = new UserFLNameDTO();
                try {
                    String publisherUserFLNameStr = (String) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.USER_FL_NAME_QUEUE_NAME, entry.getKey());
                    publisherUserFLName = objectMapper.readValue(publisherUserFLNameStr, UserFLNameDTO.class);
                } catch (JsonProcessingException exception) {
                }
                if (itemSubmitRequestDTO.getBundle()) {
                    List<Ad> ads = new ArrayList<>();
                    for (AdRequestDTO adRequestDTO : itemSubmitRequestDTO.getAds()) {
                        String adCarInfoDTOStr = (String) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.AD_CAR_INFO_QUEUE_NAME, adRequestDTO.getId());
                        AdCarInfoDTO adCarInfoDTO = new AdCarInfoDTO();
                        try {
                            adCarInfoDTO = objectMapper.readValue(adCarInfoDTOStr, AdCarInfoDTO.class);
                        } catch (JsonProcessingException exception) {
                        }
                        Float discount = findClossestDiscount(adCarInfoDTO.getDiscountInfoDTOS(), Days.daysBetween(DateAPI.DateTimeStringToDateTimeFromFronted(adRequestDTO.getStartDate()).toLocalDate(),DateAPI.DateTimeStringToDateTimeFromFronted(adRequestDTO.getEndDate()).toLocalDate()).getDays());
                        Ad ad = Ad.builder()
                                .mainId(adRequestDTO.getId())
                                .adName(adRequestDTO.getAdName())
                                .token(adCarInfoDTO.getToken())
                                .distanceLimit(adCarInfoDTO.getDistanceLimit())
                                .distanceLimitFlag(DistanceLimitEnum.valueOf(adCarInfoDTO.getDistanceLimitFlag()))
                                .pricePerDay(adCarInfoDTO.getPricePerDay())
                                .pricePerKm(adCarInfoDTO.getPricePerKm())
                                .pricePerKmCDW(adCarInfoDTO.getPricePerKmCDW())
                                .cdw(adCarInfoDTO.getCdw())
                                .review(null)
                                .mileage(adCarInfoDTO.getMileage())
                                .discount(discount)
                                .startDate(DateAPI.DateTimeStringToDateTimeFromFronted(adRequestDTO.getStartDate()))
                                .endDate(DateAPI.DateTimeStringToDateTimeFromFronted(adRequestDTO.getEndDate()))
                                .build();
                        ad = adService.save(ad);
                        ads.add(ad);
                    }
                    Request request = Request.builder()
                            .submitDate(DateAPI.DateTimeNow())
                            .status(RequestStatusEnum.PENDING)
                            .ads(ads)
                            .bundle(itemSubmitRequestDTO.getBundle())
                            .endUserId(endUserFLName.getUserId())
                            .endUserFirstName(endUserFLName.getUserFirstName())
                            .endUserLastName(endUserFLName.getUserLastName())
                            .endUserEmail(endUserFLName.getUserEmail())
                            .publisherUserId(publisherUserFLName.getUserId())
                            .publisherUserFirstName(publisherUserFLName.getUserFirstName())
                            .publisherUserLastName(publisherUserFLName.getUserLastName())
                            .publisherUserEmail(publisherUserFLName.getUserEmail())
                            .build();
                    request = this.save(request);
                    if (!publisherUserFLName.getLocal()) {
                        try {
                            String routingKey = publisherUserFLName.getUserEmail().replace("@", ".") + ".req";
                            String requestStr = objectMapper.writeValueAsString(request);
                            rabbitTemplate.convertAndSend(RabbitMQConfiguration.AGENT_SYNC_QUEUE_NAME, routingKey, requestStr);
                        } catch (JsonProcessingException exception) {

                        }
                    }
                } else {
                    for (AdRequestDTO adRequestDTO : itemSubmitRequestDTO.getAds()) {
                        List<Ad> ads = new ArrayList<>();
                        String adCarInfoDTOStr = (String) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.AD_CAR_INFO_QUEUE_NAME, adRequestDTO.getId());
                        AdCarInfoDTO adCarInfoDTO = new AdCarInfoDTO();
                        try {
                            adCarInfoDTO = objectMapper.readValue(adCarInfoDTOStr, AdCarInfoDTO.class);
                        } catch (JsonProcessingException exception) {
                        }
                        Float discount = findClossestDiscount(adCarInfoDTO.getDiscountInfoDTOS(), Days.daysBetween(DateAPI.DateTimeStringToDateTimeFromFronted(adRequestDTO.getStartDate()).toLocalDate(),DateAPI.DateTimeStringToDateTimeFromFronted(adRequestDTO.getEndDate()).toLocalDate()).getDays());
                        Ad ad = Ad.builder()
                                .mainId(adRequestDTO.getId())
                                .adName(adRequestDTO.getAdName())
                                .token(adCarInfoDTO.getToken())
                                .distanceLimit(adCarInfoDTO.getDistanceLimit())
                                .distanceLimitFlag(DistanceLimitEnum.valueOf(adCarInfoDTO.getDistanceLimitFlag()))
                                .pricePerDay(adCarInfoDTO.getPricePerDay())
                                .pricePerKm(adCarInfoDTO.getPricePerKm())
                                .pricePerKmCDW(adCarInfoDTO.getPricePerKmCDW())
                                .cdw(adCarInfoDTO.getCdw())
                                .review(null)
                                .mileage(adCarInfoDTO.getMileage())
                                .discount(discount)
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
                                .endUserId(endUserFLName.getUserId())
                                .endUserFirstName(endUserFLName.getUserFirstName())
                                .endUserLastName(endUserFLName.getUserLastName())
                                .endUserEmail(endUserFLName.getUserEmail())
                                .publisherUserId(publisherUserFLName.getUserId())
                                .publisherUserFirstName(publisherUserFLName.getUserFirstName())
                                .publisherUserLastName(publisherUserFLName.getUserLastName())
                                .publisherUserEmail(publisherUserFLName.getUserEmail())
                                .build();
                        request = this.save(request);
                        if (!publisherUserFLName.getLocal()) {
                            try {
                                String routingKey = publisherUserFLName.getUserEmail().replace("@", ".") + ".req";
                                String requestStr = objectMapper.writeValueAsString(request);
                                rabbitTemplate.convertAndSend(RabbitMQConfiguration.AGENT_SYNC_QUEUE_NAME, routingKey, requestStr);
                            } catch (JsonProcessingException exception) {
                            }
                        }
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
