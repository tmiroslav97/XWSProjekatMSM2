package agent.app.service.impl;

import agent.app.dto.car.CarCalendarTermCreateDTO;
import agent.app.converter.CarCalendarTermConverter;
import agent.app.exception.ExistsException;
import agent.app.exception.NotFoundException;
import agent.app.model.CarCalendarTerm;
import agent.app.repository.CarCalendarTermRepository;
import agent.app.service.intf.CarCalendarTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarCalendarTermServiceImpl implements CarCalendarTermService {

    @Autowired
    public CarCalendarTermRepository carCalendarTermRepository;

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
    public CarCalendarTerm editCarCalendarTerm(CarCalendarTerm carCalendarTerm) {
        this.findById(carCalendarTerm.getId());
        CarCalendarTerm carCalendarTerm1 = carCalendarTermRepository.save(carCalendarTerm);
        return carCalendarTerm1;
    }

}
