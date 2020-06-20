package agent.app.service.intf;

import agent.app.dto.codebook.CarTypeDTO;
import agent.app.model.CarType;

import java.util.List;

public interface CarTypeService {
    CarType findById(Long id);

    List<CarType> findAll();

    CarTypeDTO findAll(Integer page, Integer size);

    Integer createCarType(String name);

    Integer editCarType(CarType carType);

    Integer deleteById(Long id);

    void delete(CarType carType);

    CarType save(CarType carType);
}
