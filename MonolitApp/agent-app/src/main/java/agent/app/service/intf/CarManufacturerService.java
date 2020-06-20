package agent.app.service.intf;

import agent.app.dto.codebook.CarManufacturerDTO;
import agent.app.model.CarManufacturer;

import java.util.List;

public interface CarManufacturerService {
    CarManufacturer findById(Long id);

    List<CarManufacturer> findAll();

    CarManufacturerDTO findAll(Integer page, Integer size);

    List<String> findCarModelsById(Long id);

    Integer createCarManufacturer(String name);

    Integer editCarManufacturer(CarManufacturer carManufacturer);

    Integer deleteById(Long id);

    void delete(CarManufacturer carManufacturer);

    CarManufacturer save(CarManufacturer carManufacturer);
}
