package services.app.adsearchservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.app.adsearchservice.config.RabbitMQConfiguration;
import services.app.adsearchservice.dto.MileageUpdateDTO;
import services.app.adsearchservice.exception.ExistsException;
import services.app.adsearchservice.exception.NotFoundException;
import services.app.adsearchservice.model.Ad;
import services.app.adsearchservice.model.Car;
import services.app.adsearchservice.repository.CarRepository;
import services.app.adsearchservice.service.intf.AdService;
import services.app.adsearchservice.service.intf.CarService;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private AdService adService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Car finById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new NotFoundException("Automobil ne postoji."));
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car save(Car car) {
        if (carRepository.existsById(car.getId())) {
            throw new ExistsException(String.format("Automobil vec postoji."));
        }
        return carRepository.save(car);
    }

    @Override
    public void delete(Car car) {
        carRepository.delete(car);
    }

    @Override
    public Car edit(Car car) {
        this.finById(car.getId());
        return this.save(car);
    }

    @Override
    public Integer deleteById(Long id) {
        Car car = this.finById(id);
        this.delete(car);
        return 1;
    }

    @Override
    @RabbitListener(queues = RabbitMQConfiguration.UPDATE_MILEAGE_AD_SEARCH_QUEUE_NAME)
    public void setMileage(String msg) {
        try {
            MileageUpdateDTO mileageUpdateDTO = objectMapper.readValue(msg, MileageUpdateDTO.class);
            Ad ad = adService.findById(mileageUpdateDTO.getAdId());
            Car car = ad.getCar();
            car.setMileage(mileageUpdateDTO.getMileage());
            car = carRepository.save(car);
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();
        }
    }


}
