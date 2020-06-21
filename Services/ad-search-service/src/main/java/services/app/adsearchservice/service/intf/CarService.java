package services.app.adsearchservice.service.intf;



import services.app.adsearchservice.model.Car;

import java.util.List;

public interface CarService {
    Car finById(Long id);
    List<Car> findAll();
    Car save(Car car);
    void delete(Car car);
    Car edit(Car car);
    Integer deleteById(Long id);



}
