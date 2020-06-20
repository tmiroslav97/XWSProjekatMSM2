package services.app.carrequestservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.app.carrequestservice.exception.NotFoundException;
import services.app.carrequestservice.model.Ad;
import services.app.carrequestservice.repository.AdRepository;
import services.app.carrequestservice.service.intf.AdService;

import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdRepository adRepository;

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
