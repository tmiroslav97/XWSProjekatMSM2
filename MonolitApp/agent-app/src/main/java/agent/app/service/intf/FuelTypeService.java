package agent.app.service.intf;

import agent.app.dto.codebook.FuelTypeDTO;
import agent.app.model.FuelType;

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
