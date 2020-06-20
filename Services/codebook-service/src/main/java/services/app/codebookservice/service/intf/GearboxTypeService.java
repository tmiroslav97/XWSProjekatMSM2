package services.app.codebookservice.service.intf;



import services.app.codebookservice.dto.GearboxTypeDTO;
import services.app.codebookservice.model.GearboxType;

import java.util.List;

public interface GearboxTypeService {
    GearboxType findById(Long id);

    List<GearboxType> findAll();

    GearboxTypeDTO findAll(Integer page, Integer size);

    Integer createGearboxType(String name);

    Integer editGearboxType(GearboxType gearboxType);

    Integer deleteById(Long id);

    void delete(GearboxType gearboxType);

    GearboxType save(GearboxType gearboxType);
}
