package agent.app.service.intf;

import agent.app.dto.car.CarCalendarTermCreateDTO;
import agent.app.dto.car.CarCalendarTermDTO;
import agent.app.model.CarCalendarTerm;
import org.joda.time.DateTime;

import java.util.List;

public interface CarCalendarTermService {
    CarCalendarTerm findById(Long id);
    List<CarCalendarTerm> findAll();
    CarCalendarTerm save(CarCalendarTerm carCalendarTerm);
    void delete(CarCalendarTerm carCalendarTerm);
    CarCalendarTerm edit(CarCalendarTerm carCalendarTerm);
    Integer deleteById(Long id);
    CarCalendarTerm createCarCalendarTerm(CarCalendarTermCreateDTO carCalendarTermCreateDTO);
    Integer addCarCalendarTerm(CarCalendarTermDTO carCalendarTermDTO);
    CarCalendarTerm editCarCalendarTerm(CarCalendarTerm carCalendarTerm);
    List<CarCalendarTermCreateDTO> findByAdId(Long id);
    CarCalendarTerm findByAdAndDate(Long adId, DateTime startDate, DateTime endDate);
    void carCalendarTermSync(String msg);
    Integer addCarCalendarTermOccupation(CarCalendarTermDTO carCalendarTermDTO);
    Boolean splitCarCalendarTerm(Long adId, DateTime startDate, DateTime endDate);
    Boolean splitCarCalendarTermSync(Long adId, DateTime startDate, DateTime endDate);
    Boolean canSplitCarCalendarTerm(Long adId, DateTime startDate, DateTime endDate);
}
