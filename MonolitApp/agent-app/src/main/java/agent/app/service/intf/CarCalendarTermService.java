package agent.app.service.intf;

import agent.app.dto.car.CarCalendarTermCreateDTO;
import agent.app.model.CarCalendarTerm;

import java.util.List;

public interface CarCalendarTermService {
    CarCalendarTerm findById(Long id);
    List<CarCalendarTerm> findAll();
    CarCalendarTerm save(CarCalendarTerm carCalendarTerm);
    void delete(CarCalendarTerm carCalendarTerm);
    Integer deleteById(Long id);
    CarCalendarTerm createCarCalendarTerm(CarCalendarTermCreateDTO carCalendarTermCreateDTO);
    CarCalendarTerm editCarCalendarTerm(CarCalendarTerm carCalendarTerm);
}
