package agent.app.service.impl;

import agent.app.dto.codebook.GearboxTypeDTO;
import agent.app.exception.ExistsException;
import agent.app.exception.NotFoundException;
import agent.app.model.GearboxType;
import agent.app.repository.GearboxTypeRepository;
import agent.app.service.intf.GearboxTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GearboxTypeServiceImpl implements GearboxTypeService {

    @Autowired
    private GearboxTypeRepository gearboxTypeRepository;

    @Override
    public GearboxType findById(Long id) {
        return gearboxTypeRepository.findById(id).orElseThrow(() -> new NotFoundException("Tip mjenjaca ne postoji u sifarniku."));
    }

    @Override
    public List<GearboxType> findAll() {
        return gearboxTypeRepository.findAll();
    }

    @Override
    public GearboxTypeDTO findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<GearboxType> gearboxTypePage = gearboxTypeRepository.findAll(pageable);
        GearboxTypeDTO gearboxTypeDTO = GearboxTypeDTO.builder()
                .totalPageCnt(gearboxTypePage.getTotalPages())
                .gearboxTypes(gearboxTypePage.getContent())
                .build();

        return gearboxTypeDTO;
    }

    @Override
    public Integer createGearboxType(String name) {
        GearboxType gearboxType = GearboxType.builder()
                .name(name)
                .build();
        this.save(gearboxType);
        return 1;
    }

    @Override
    public Integer editGearboxType(GearboxType gearboxType) {
        this.findById(gearboxType.getId());
        gearboxTypeRepository.save(gearboxType);
        return 1;
    }

    @Override
    public Integer deleteById(Long id) {
        GearboxType gearboxType = this.findById(id);
        this.delete(gearboxType);
        return 1;
    }

    @Override
    public void delete(GearboxType gearboxType) {
        gearboxTypeRepository.delete(gearboxType);
    }

    @Override
    public GearboxType save(GearboxType gearboxType) {
        if (gearboxTypeRepository.existsByName(gearboxType.getName())) {
            throw new ExistsException(String.format("Tip tip mjenjaca sa imenom '%s' vec postoji u sifarniku", gearboxType.getName()));
        }

        return gearboxTypeRepository.save(gearboxType);
    }
}
