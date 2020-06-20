package services.app.codebookservice.service.intf;



import services.app.codebookservice.dto.CarManufacturerDTO;
import services.app.codebookservice.model.CarManufacturer;

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
