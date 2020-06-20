package agent.app.service.impl;

import agent.app.converter.CarConverter;
import agent.app.dto.car.CarCreateDTO;
import agent.app.exception.ExistsException;
import agent.app.exception.NotFoundException;
import agent.app.model.Car;
import agent.app.repository.CarRepository;
import agent.app.service.intf.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if(car.getId() != null) {
            if (carRepository.existsById(car.getId())) {
                throw new ExistsException(String.format("Automobil vec postoji."));
            }
        }
        if(car.getCarModel() == null){
            car.setCarModel("");
        }
        return carRepository.save(car);
    }

    @Override
    public void delete(Car car) {
        carRepository.delete(car);
    }

    @Override
    public Car createCar(CarCreateDTO carCreateDTO) {
       Car car = CarConverter.toCreateCarFromRequest(carCreateDTO);
       car = this.save(car);
       return car;
    }

    @Override
    public Car editCar(Car car) {
        this.finById(car.getId());
        Car car1 = this.save(car);
        return car1;
    }

    @Override
    public Integer deleteById(Long id) {
        Car car = this.finById(id);
        this.delete(car);
        return 1;
    }
}
