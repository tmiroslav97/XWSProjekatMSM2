package services.app.adsearchservice.service.intf;



import services.app.adsearchservice.model.Car;

import java.util.List;

public interface CarService {
    Car finById(Long id);
    List<Car> findAll();
}
