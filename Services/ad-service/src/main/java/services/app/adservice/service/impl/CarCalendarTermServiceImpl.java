package services.app.adservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.app.adservice.converter.CarCalendarTermConverter;
import services.app.adservice.dto.car.CarCalendarTermCreateDTO;
import services.app.adservice.dto.car.CarCalendarTermDTO;
import services.app.adservice.exception.ExistsException;
import services.app.adservice.exception.NotFoundException;
import services.app.adservice.model.Ad;
import services.app.adservice.model.CarCalendarTerm;
import services.app.adservice.repository.CarCalendarTermRepository;
import services.app.adservice.service.intf.AdService;
import services.app.adservice.service.intf.CarCalendarTermService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CarCalendarTermServiceImpl implements CarCalendarTermService {

    @Autowired
    public CarCalendarTermRepository carCalendarTermRepository;

    @Autowired
    public AdService adService;

    @Override
    public CarCalendarTerm findById(Long id) {
        return carCalendarTermRepository.findById(id).orElseThrow(()-> new NotFoundException("Termin u kalendaru ne postoji."));
    }

    @Override
    public List<CarCalendarTerm> findAll() {
        return carCalendarTermRepository.findAll();
    }

    @Override
    public CarCalendarTerm save(CarCalendarTerm carCalendarTerm) {
        if(carCalendarTerm.getId() != null){
            if(carCalendarTermRepository.existsById(carCalendarTerm.getId())){
                throw new ExistsException(String.format("Termin u kalendaru vec postoji."));
            }
        }
        return carCalendarTermRepository.save(carCalendarTerm);
    }

    @Override
    public void delete(CarCalendarTerm carCalendarTerm) {
        carCalendarTermRepository.delete(carCalendarTerm);
    }

    @Override
    public CarCalendarTerm edit(CarCalendarTerm carCalendarTerm) {
        this.findById(carCalendarTerm.getId());
        return carCalendarTermRepository.save(carCalendarTerm);
    }

    @Override
    public Integer deleteById(Long id) {
        CarCalendarTerm carCalendarTerm = this.findById(id);
        this.delete(carCalendarTerm);
        return 1;
    }

    @Override
    public CarCalendarTerm createCarCalendarTerm(CarCalendarTermCreateDTO carCalendarTermCreateDTO) {
        CarCalendarTerm carCalendarTerm = CarCalendarTermConverter.toCreateCarCalendarTermFromRequest(carCalendarTermCreateDTO);
        carCalendarTerm = this.save(carCalendarTerm);
        return carCalendarTerm;
    }

    @Override
    public Integer addCarCalendarTerm(CarCalendarTermDTO carCalendarTermDTO) {
        CarCalendarTerm carCalendarTerm =  CarCalendarTermConverter.toCarCalendarTermFromRequest(carCalendarTermDTO);
        if(carCalendarTerm != null){
            Ad ad = adService.findById(carCalendarTermDTO.getAdId());
            if(ad != null){
                carCalendarTerm.setAd(ad);
                carCalendarTerm = this.save(carCalendarTerm);

                ad.getCarCalendarTerms().add(carCalendarTerm);
                ad = adService.edit(ad);
                System.out.println("usloo u if");
//                System.out.println(ad);
//                carCalendarTerm.setAd(ad);
                return 1;
            }
        }
        return 2;
    }

    @Override
    public CarCalendarTerm editCarCalendarTerm(CarCalendarTerm carCalendarTerm) {
        this.findById(carCalendarTerm.getId());
        CarCalendarTerm carCalendarTerm1 = carCalendarTermRepository.save(carCalendarTerm);
        return carCalendarTerm1;
    }

    @Override
    public List<CarCalendarTermCreateDTO> findByAdId(Long id) {
        List<CarCalendarTermCreateDTO> list = new ArrayList<>();
        Ad ad = adService.findById(id);
        if(ad != null){
            System.out.println("naziv oglasa: "+ad.getName());
            Set<CarCalendarTerm> carCalendarTerms = ad.getCarCalendarTerms();
            for(CarCalendarTerm carCalendarTerm: carCalendarTerms){
                CarCalendarTermCreateDTO dto = new CarCalendarTermCreateDTO();
                dto.setEndDate(carCalendarTerm.getEndDate().toString());
                dto.setStartDate(carCalendarTerm.getStartDate().toString());
                list.add(dto);
                System.out.println("termin: " + carCalendarTerm.getStartDate() + carCalendarTerm.getEndDate() );
            }
        }

        return list;
    }

}
