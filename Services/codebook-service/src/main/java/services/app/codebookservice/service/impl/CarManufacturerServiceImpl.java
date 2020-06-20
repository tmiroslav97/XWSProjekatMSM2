package services.app.codebookservice.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import services.app.codebookservice.converter.CarModelConverter;
import services.app.codebookservice.dto.CarManufacturerDTO;
import services.app.codebookservice.exception.ExistsException;
import services.app.codebookservice.exception.NotFoundException;
import services.app.codebookservice.model.CarManufacturer;
import services.app.codebookservice.repository.CarManufacturerRepository;
import services.app.codebookservice.service.intf.CarManufacturerService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class CarManufacturerServiceImpl implements CarManufacturerService {

    @Autowired
    private CarManufacturerRepository carManufacturerRepository;

    @Override
    public CarManufacturer findById(Long id) {
        return carManufacturerRepository.findById(id).orElseThrow(() -> new NotFoundException("Proizvodjac automobila ne postoji u sifarniku."));
    }

    @Override
    public List<CarManufacturer> findAll() {
        return carManufacturerRepository.findAll();
    }

    @Override
    public CarManufacturerDTO findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<CarManufacturer> carManufacturerPage = carManufacturerRepository.findAll(pageable);
        CarManufacturerDTO carManufacturerDTO = CarManufacturerDTO.builder()
                .totalPageCnt(carManufacturerPage.getTotalPages())
                .carManufacturers(carManufacturerPage.getContent())
                .build();

        return carManufacturerDTO;
    }

    @Override
    public List<String> findCarModelsById(Long id) {
        CarManufacturer carManufacturer = findById(id);
        return CarModelConverter.fromEntityList(new ArrayList<>(carManufacturer.getCarModels()), CarModelConverter::fromEntityToString);
    }

    @Override
    public Integer createCarManufacturer(String name) {
        CarManufacturer carManufacturer = CarManufacturer.builder()
                .name(name)
                .carModels(new HashSet<>())
                .build();
        this.save(carManufacturer);
        return 1;
    }

    @Override
    public Integer editCarManufacturer(CarManufacturer carManufacturer) {
        this.findById(carManufacturer.getId());
        carManufacturerRepository.save(carManufacturer);
        return 1;
    }

    @Override
    public Integer deleteById(Long id) {
        CarManufacturer carManufacturer = this.findById(id);
        this.delete(carManufacturer);
        return 1;
    }

    @Override
    public void delete(CarManufacturer carManufacturer) {
        carManufacturerRepository.delete(carManufacturer);
    }

    @Override
    public CarManufacturer save(CarManufacturer carManufacturer) {
        if (carManufacturerRepository.existsByName(carManufacturer.getName())) {
            throw new ExistsException(String.format("Proizvodjac automobila sa imenom '%s' vec postoji u sifarniku", carManufacturer.getName()));
        }

        return carManufacturerRepository.save(carManufacturer);
    }
}
