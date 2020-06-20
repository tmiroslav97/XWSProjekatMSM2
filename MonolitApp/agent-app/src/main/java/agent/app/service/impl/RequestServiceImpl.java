package agent.app.service.impl;

import agent.app.converter.DateAPI;
import agent.app.dto.carreq.SubmitRequestDTO;
import agent.app.exception.NotFoundException;
import agent.app.model.Ad;
import agent.app.model.EndUser;
import agent.app.model.Request;
import agent.app.model.enumeration.RequestStatusEnum;
import agent.app.repository.RequestRepository;
import agent.app.service.intf.AdService;
import agent.app.service.intf.EndUserService;
import agent.app.service.intf.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private AdService adService;

    @Autowired
    private EndUserService endUserService;

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
    public Integer submitRequest(List<SubmitRequestDTO> submitRequestDTOS, String email) {
        EndUser endUser = endUserService.findByEmail(email);
        for (SubmitRequestDTO submitRequestDTO : submitRequestDTOS) {
            Request request = null;
            if (submitRequestDTO.getBundle()) {
                request = Request.builder()
                        .bundle(true)
                        .startDate(DateAPI.dateStringToDateTime(submitRequestDTO.getStartDate()))
                        .endDate(DateAPI.dateStringToDateTime(submitRequestDTO.getEndDate()))
                        .submitDate(DateAPI.DateTimeNow())
                        .status(RequestStatusEnum.PENDING)
                        .ads(adService.findAllByIds(submitRequestDTO.getAdIds()))
                        .endUser(endUser)
                        .messages(new HashSet<>())
                        .build();
                this.save(request);
            } else {
                for (Long adId : submitRequestDTO.getAdIds()) {
                    Ad ad = adService.findById(adId);
                    Set<Ad> ads = new HashSet<>();
                    ads.add(ad);
                    request = Request.builder()
                            .bundle(false)
                            .startDate(DateAPI.dateStringToDateTime(submitRequestDTO.getStartDate()))
                            .endDate(DateAPI.dateStringToDateTime(submitRequestDTO.getEndDate()))
                            .submitDate(DateAPI.dateTimeNow())
                            .status(RequestStatusEnum.PENDING)
                            .ads(ads)
                            .endUser(endUser)
                            .messages(new HashSet<>())
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
