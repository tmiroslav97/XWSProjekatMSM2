package services.app.codebookservice.service.intf;



import services.app.codebookservice.dto.CarTypeDTO;
import services.app.codebookservice.model.CarType;

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
