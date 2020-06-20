package agent.app.service.intf;

import agent.app.dto.codebook.GearboxTypeDTO;
import agent.app.model.GearboxType;

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
