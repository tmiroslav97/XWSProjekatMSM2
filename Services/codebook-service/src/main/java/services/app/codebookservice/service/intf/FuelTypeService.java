package services.app.codebookservice.service.intf;



import services.app.codebookservice.dto.FuelTypeDTO;
import services.app.codebookservice.model.FuelType;

import java.util.List;

public interface FuelTypeService {
    FuelType findById(Long id);

    List<FuelType> findAll();

    FuelTypeDTO findAll(Integer page, Integer size);

    Integer createFuelType(String name);

    Integer editFuelType(FuelType fuelType);

    Integer deleteById(Long id);

    void delete(FuelType fuelType);

    FuelType save(FuelType fuelType);
}
