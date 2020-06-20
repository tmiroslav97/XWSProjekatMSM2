package services.app.adsearchservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.app.adsearchservice.exception.ExistsException;
import services.app.adsearchservice.exception.NotFoundException;
import services.app.adsearchservice.model.Car;
import services.app.adsearchservice.repository.CarRepository;
import services.app.adsearchservice.service.intf.CarService;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public Car finById(Long id) {
        return carRepository.findById(id).orElseThrow(()-> new NotFoundException("Automobil ne postoji."));
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }


}
