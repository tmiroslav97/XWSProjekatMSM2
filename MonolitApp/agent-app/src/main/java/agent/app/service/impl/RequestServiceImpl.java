package agent.app.service.impl;

import agent.app.converter.RequestConverter;
import agent.app.dto.SignUpDTO;
import agent.app.dto.carreq.RequestDTO;
import agent.app.exception.NotFoundException;
import agent.app.model.*;
import agent.app.model.enumeration.DistanceLimitEnum;
import agent.app.model.enumeration.RequestStatusEnum;
import agent.app.repository.RequestRepository;
import agent.app.service.intf.*;
import agent.app.ws.client.RequestClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private AdService adService;

    @Autowired
    private CarCalendarTermService carCalendarTermService;

    @Autowired
    private AgentService agentService;

    @Autowired
    private EndUserService endUserService;

    @Autowired
    private PublisherUserService publisherUserService;

    @Autowired
    private AdRequestService adRequestService;

    @Autowired
    private AuthenticationService authenticationService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private RequestClient requestClient;


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
    public List<RequestDTO> findAllByPublisherUserAndStatus(String email, String status) {
        List<Request> requests = requestRepository.findAllByEndUserAndByStatus(email, RequestStatusEnum.valueOf(status));
        List<RequestDTO> requestDTOS = RequestConverter.fromEntityList(requests, RequestConverter::toRequestDTOFromRequest);
        return requestDTOS;
    }

    @Override
    public String acceptRequest(String email, Long id, String action) {
        Request request = this.findById(id);
        if (action.equals("reject")) {
            request.setStatus(RequestStatusEnum.CANCELED);
            this.save(request);
            if (request.getMainId() != null) {
                String identifier = this.findRequestPublisherUserIdentifier(email);
                String response = requestClient.acceptRequest(email, identifier, request.getMainId(), action);
            }
            return "Uspjesno odbijen zahtjev";
        }
        if (!action.equals("accept")) {
            return "Nepoznata akcija";
        }

        int cnt = 0;
        for (AdRequest adRequest : request.getAds()) {
            Boolean flag = carCalendarTermService.canSplitCarCalendarTerm(adRequest.getAdId(), adRequest.getStartDate(), adRequest.getEndDate());
            if (flag) {
                cnt++;
            }
        }
        if (cnt == request.getAds().size()) {
            for (AdRequest adRequest : request.getAds()) {
                carCalendarTermService.splitCarCalendarTerm(adRequest.getAdId(), adRequest.getStartDate(), adRequest.getEndDate());
            }
            request.setStatus(RequestStatusEnum.PAID);
            this.save(request);
            if (request.getMainId() != null) {
                String identifier = this.findRequestPublisherUserIdentifier(email);
                String response = requestClient.acceptRequest(email, identifier, request.getMainId(), action);
            }
            return "Uspjesno prihvacen zahtjev";
        } else {
            return "Nije moguce prihvatiti zahtjev";
        }


    }

    @Override
    @RabbitListener(queues = "#{autoDeleteRequest.name}")
    public void requestSync(String msg) {
        try {
            services.app.carrequestservice.model.Request request = objectMapper.readValue(msg, services.app.carrequestservice.model.Request.class);
            if (requestRepository.existsByMainId(request.getId())) {
                Request requestAgent = requestRepository.findByMainId(request.getId());
                requestAgent.setStatus(RequestStatusEnum.valueOf(request.getStatus().toString()));
                this.save(requestAgent);
            } else {
                List<AdRequest> adRequests = new ArrayList<>();
                for (services.app.carrequestservice.model.Ad ad : request.getAds()) {
                    Ad adAgent = adService.findByMainId(ad.getMainId());
                    AdRequest adRequest = AdRequest.builder()
                            .adName(ad.getAdName())
                            .cdw(ad.isCdw())
                            .distanceLimit(ad.getDistanceLimit())
                            .distanceLimitFlag(DistanceLimitEnum.valueOf(ad.getDistanceLimitFlag().toString()))
                            .startDate(ad.getStartDate())
                            .endDate(ad.getEndDate())
                            .pricePerDay(ad.getPricePerDay())
                            .pricePerKm(ad.getPricePerKm())
                            .pricePerKmCDW(ad.getPricePerKmCDW())
                            .mainId(ad.getMainId())
                            .adId(adAgent.getId())
                            .build();
                    adRequest = adRequestService.save(adRequest);
                    adRequests.add(adRequest);
                }
                PublisherUser publisherUser = publisherUserService.findByEmail(request.getPublisherUserEmail());
                EndUser endUser = endUserService.findByEmail(request.getEndUserEmail());
                if (endUser == null) {
                    SignUpDTO signUpDTO = SignUpDTO.builder()
                            .email(request.getEndUserEmail())
                            .firstName(request.getEndUserFirstName())
                            .lastName(request.getEndUserLastName())
                            .password("12345")
                            .password2("12345")
                            .build();
                    authenticationService.signUp(signUpDTO);
                    endUser = endUserService.findByEmail(request.getEndUserEmail());
                }

                Request requestAgent = Request.builder()
                        .publisherUser(publisherUser)
                        .endUser(endUser)
                        .ads(adRequests)
                        .bundle(request.isBundle())
                        .mainId(request.getId())
                        .submitDate(request.getSubmitDate())
                        .status(RequestStatusEnum.valueOf(request.getStatus().toString()))
                        .build();
                this.save(requestAgent);
            }
        } catch (JsonProcessingException exception) {
            System.out.println("Catch");
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
