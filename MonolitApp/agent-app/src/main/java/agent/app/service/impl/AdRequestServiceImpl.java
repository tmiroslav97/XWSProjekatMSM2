package agent.app.service.impl;

import agent.app.exception.NotFoundException;
import agent.app.model.AdRequest;
import agent.app.repository.AdRequestRepository;
import agent.app.service.intf.AdRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdRequestServiceImpl implements AdRequestService {

    @Autowired
    private AdRequestRepository adRequestRepository;

    @Override
    public AdRequest findById(Long id) {
        return adRequestRepository.findById(id).orElseThrow(() -> new NotFoundException("Oglas vezan za zahtjev ne postoji."));
    }

    @Override
    public List<AdRequest> findAll() {
        return adRequestRepository.findAll();
    }

    @Override
    public AdRequest save(AdRequest adRequest) {
        return adRequestRepository.save(adRequest);
    }

    @Override
    public void delete(AdRequest adRequest) {
        adRequestRepository.delete(adRequest);
    }
}
