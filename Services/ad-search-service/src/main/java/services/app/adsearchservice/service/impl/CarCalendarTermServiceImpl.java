package services.app.adsearchservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.app.adsearchservice.converter.CarCalendarTermsConverter;
import services.app.adsearchservice.dto.car.CarCalendarTermSynchronizeDTO;
import services.app.adsearchservice.exception.ExistsException;
import services.app.adsearchservice.exception.NotFoundException;
import services.app.adsearchservice.model.CarCalendarTerm;
import services.app.adsearchservice.repository.CarCalendarTermRepository;
import services.app.adsearchservice.service.intf.CarCalendarTermService;

import java.util.List;

@Service
public class CarCalendarTermServiceImpl implements CarCalendarTermService {

    @Autowired
    private CarCalendarTermRepository carCalendarTermRepository;

    @Override
    public CarCalendarTerm findById(Long id) {
        return carCalendarTermRepository.findById(id).orElseThrow(()-> new NotFoundException("Termin ne postoji"));
    }

    @Override
    public List<CarCalendarTerm> findAll() {
        return carCalendarTermRepository.findAll();
    }

    @Override
    public CarCalendarTerm save(CarCalendarTerm carCalendarTerm) {
        if(carCalendarTermRepository.existsById(carCalendarTerm.getId())){
            throw new ExistsException(String.format("Termin vec postoji."));
        }
        return carCalendarTermRepository.save(carCalendarTerm);
    }

    @Override
    public void delete(CarCalendarTerm carCalendarTerm) {
        carCalendarTermRepository.delete(carCalendarTerm);
    }

    @Override
    public CarCalendarTerm createCarCalendarTerm(CarCalendarTermSynchronizeDTO dto) {
        CarCalendarTerm cct = CarCalendarTermsConverter.toCarCalendarTermFromSyncDTO(dto);
        return carCalendarTermRepository.save(cct);
    }

    @Override
    public CarCalendarTerm editCarCalendarTerm(CarCalendarTerm carCalendarTerm) {
        this.findById(carCalendarTerm.getId());
        return this.save(carCalendarTerm);
    }

    @Override
    public Integer deleteById(Long id) {
        CarCalendarTerm cct = this.findById(id);
        this.delete(cct);
        return 1;
    }
}
