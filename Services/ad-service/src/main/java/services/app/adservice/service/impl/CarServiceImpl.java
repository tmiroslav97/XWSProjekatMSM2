package services.app.adservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.app.adservice.converter.CarConverter;
import services.app.adservice.dto.car.StatisticCarDTO;
import services.app.adservice.dto.car.CarCreateDTO;
import services.app.adservice.exception.ExistsException;
import services.app.adservice.exception.NotFoundException;
import services.app.adservice.model.Car;
import services.app.adservice.repository.CarRepository;
import services.app.adservice.service.intf.CarService;

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

    @Override
    public Car save(Car car) {
        if(car.getId() != null){
            if(carRepository.existsById(car.getId())){
                throw new ExistsException(String.format("Automobil vec postoji."));
            }
        }
        return carRepository.save(car);    }

    @Override
    public void delete(Car car) {
        carRepository.delete(car);
    }

    @Override
    public Car createCar(CarCreateDTO carCreateDTO) {
        Car car = CarConverter.toCreateCarFromRequest(carCreateDTO);
        car = this.carRepository.save(car);
        return car;
    }

    @Override
    public Car editCar(Car car) {
        this.finById(car.getId());
        Car car1 = carRepository.save(car);
        return car1;
    }

    @Override
    public Integer deleteById(Long id) {
        Car car = this.finById(id);
        this.delete(car);
        return 1;
    }

    @Override
    public List<StatisticCarDTO> getCarsWithHighestMileage(Long publisherId) {
        return null;
    }
}
