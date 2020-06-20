package services.app.adservice.service.intf;

import services.app.adservice.dto.car.CarCreateDTO;
import services.app.adservice.dto.car.StatisticCarDTO;
import services.app.adservice.model.Car;

import java.util.List;

public interface CarService {

    Car finById(Long id);
    List<Car> findAll();
    Car save(Car car);
    void delete(Car car);
    Car createCar(CarCreateDTO carCreateDTO);
    Car editCar(Car car);
    Integer deleteById(Long id);
    List<StatisticCarDTO> getCarsWithHighestMileage(Long publisherId);

}
