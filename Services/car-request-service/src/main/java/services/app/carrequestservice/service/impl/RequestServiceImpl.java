package services.app.carrequestservice.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.app.carrequestservice.client.AuthenticationClient;
import services.app.carrequestservice.converter.DateAPI;
import services.app.carrequestservice.dto.carreq.SubmitRequestDTO;
import services.app.carrequestservice.exception.NotFoundException;
import services.app.carrequestservice.model.Ad;
import services.app.carrequestservice.model.Request;
import services.app.carrequestservice.model.RequestStatusEnum;
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
    public List<Request> findAllByPublisherUserEmail(String email) {
        return this.findAllByPublisherUserId(authenticationClient.findPublishUserByEmailWS(email));
    }

    @Override
    public List<Request> findAllByPublisherUserEmailAndStatus(String email, String status) {
        return this.findAllByPublisherUserIdAndByStatus(authenticationClient.findPublishUserByEmailWS(email), status);
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
        for (Map.Entry<Long, SubmitRequestDTO> entry : submitRequestDTOS.entrySet()) {
            SubmitRequestDTO itemSubmitRequestDTO = entry.getValue();
            Request request = null;
            if (itemSubmitRequestDTO.getBundle()) {
                List<Ad> ads = new ArrayList<>();
                for (Ad adItem : itemSubmitRequestDTO.getAds()) {
                    Ad ad = null;
                    if (adService.existsById(adItem.getId())) {
                        ad = adService.findById(adItem.getId());
                    } else {
                        ad = Ad.builder()
                                .id(adItem.getId())
                                .adName(adItem.getAdName())
                                .build();
                        adService.save(ad);
                    }
                    ads.add(ad);
                }
                request = Request.builder()
                        .startDate(DateAPI.dateStringToDateTime(itemSubmitRequestDTO.getStartDate()))
                        .endDate(DateAPI.dateStringToDateTime(itemSubmitRequestDTO.getEndDate()))
                        .submitDate(DateAPI.DateTimeNow())
                        .status(RequestStatusEnum.PENDING)
                        .ads(ads)
                        .bundle(itemSubmitRequestDTO.getBundle())
                        .publisherUser(entry.getKey())
                        .endUser(userId)
                        .build();
                this.save(request);
            } else {
                for (Ad adItem : itemSubmitRequestDTO.getAds()) {
                    List<Ad> ads = new ArrayList<>();
                    Ad ad = null;
                    if (adService.existsById(adItem.getId())) {
                        ad = adService.findById(adItem.getId());
                    } else {
                        ad = Ad.builder()
                                .id(adItem.getId())
                                .adName(adItem.getAdName())
                                .build();
                        adService.save(ad);
                    }
                    ads.add(ad);
                    request = Request.builder()
                            .startDate(DateAPI.dateStringToDateTime(itemSubmitRequestDTO.getStartDate()))
                            .endDate(DateAPI.dateStringToDateTime(itemSubmitRequestDTO.getEndDate()))
                            .submitDate(DateAPI.dateTimeNow())
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

    @Override
    public void delete(Request request) {
        requestRepository.delete(request);
    }

    @Override
    public Request save(Request request) {
        return requestRepository.save(request);
    }
}
