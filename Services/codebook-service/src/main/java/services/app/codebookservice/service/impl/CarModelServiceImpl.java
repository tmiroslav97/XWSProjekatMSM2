package services.app.codebookservice.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import services.app.codebookservice.dto.CarModelDTO;
import services.app.codebookservice.exception.ExistsException;
import services.app.codebookservice.exception.NotFoundException;
import services.app.codebookservice.model.CarManufacturer;
import services.app.codebookservice.model.CarModel;
import services.app.codebookservice.repository.CarModelRepository;
import services.app.codebookservice.service.intf.CarManufacturerService;
import services.app.codebookservice.service.intf.CarModelService;

import java.util.List;

@Service
public class CarModelServiceImpl implements CarModelService {

    @Autowired
    private CarModelRepository carModelRepository;

    @Autowired
    private CarManufacturerService carManufacturerService;

    @Override
    public CarModel findById(Long id) {
        return carModelRepository.findById(id).orElseThrow(() -> new NotFoundException("Model automobila ne postoji u sifarniku."));
    }

    @Override
    public List<CarModel> findAll() {
        return carModelRepository.findAll();
    }

    @Override
    public CarModelDTO findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<CarModel> carModelPage = carModelRepository.findAll(pageable);
        CarModelDTO carModelDTO = CarModelDTO.builder()
                .totalPageCnt(carModelPage.getTotalPages())
                .carModels(carModelPage.getContent())
                .build();

        return carModelDTO;
    }

    @Override
    public Integer createCarModel(CarModel carModel) {
        CarManufacturer carManufacturer = carManufacturerService.findById(carModel.getCarManufacturer().getId());
        carModel.setCarManufacturer(carManufacturer);
        this.save(carModel);
        return 1;
    }

    @Override
    public Integer editCarModel(CarModel carModel) {
        this.findById(carModel.getId());
        carModelRepository.save(carModel);
        return 1;
    }

    @Override
    public Integer deleteById(Long id) {
        CarModel carModel = this.findById(id);
        this.delete(carModel);
        return 1;
    }

    @Override
    public void delete(CarModel carModel) {
        carModelRepository.delete(carModel);
    }

    @Override
    public CarModel save(CarModel carModel) {
        if (carModelRepository.existsByName(carModel.getName())) {
            throw new ExistsException(String.format("Model automobila sa imenom '%s' vec postoji u sifarniku", carModel.getName()));
        }

        return carModelRepository.save(carModel);
    }
}
