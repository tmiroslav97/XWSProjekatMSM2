package services.app.adsearchservice.service.intf;

import services.app.adsearchservice.dto.car.CarCalendarTermSynchronizeDTO;
import services.app.adsearchservice.model.CarCalendarTerm;

import java.util.List;

public interface CarCalendarTermService {
    CarCalendarTerm findById(Long id);
    List<CarCalendarTerm> findAll();
    CarCalendarTerm save(CarCalendarTerm carCalendarTerm);
    void delete(CarCalendarTerm carCalendarTerm);
    CarCalendarTerm createCarCalendarTerm(CarCalendarTermSynchronizeDTO dto);
    CarCalendarTerm editCarCalendarTerm(CarCalendarTerm carCalendarTerm);
    Integer deleteById(Long id);

}
