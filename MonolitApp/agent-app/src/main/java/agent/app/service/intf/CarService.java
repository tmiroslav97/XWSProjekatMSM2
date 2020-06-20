package agent.app.service.intf;

import agent.app.dto.car.CarCreateDTO;
import agent.app.model.Car;
import java.util.List;

public interface CarService {
    Car finById(Long id);
    List<Car> findAll();
    Car save(Car car);
    void delete(Car car);
    Car createCar(CarCreateDTO carCreateDTO);
    Car editCar(Car car);
    Integer deleteById(Long id);
}
