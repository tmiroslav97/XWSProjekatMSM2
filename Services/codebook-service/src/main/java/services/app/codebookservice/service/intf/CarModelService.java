package services.app.codebookservice.service.intf;



import services.app.codebookservice.dto.CarModelDTO;
import services.app.codebookservice.model.CarModel;

import java.util.List;

public interface CarModelService {
    CarModel findById(Long id);

    List<CarModel> findAll();

    CarModelDTO findAll(Integer page, Integer size);

    Integer createCarModel(CarModel carModel);

    Integer editCarModel(CarModel carModel);

    Integer deleteById(Long id);

    void delete(CarModel carModel);

    CarModel save(CarModel carModel);
}
