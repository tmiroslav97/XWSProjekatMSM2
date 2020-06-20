package agent.app.service.impl;

import agent.app.dto.codebook.CarModelDTO;
import agent.app.exception.ExistsException;
import agent.app.exception.NotFoundException;
import agent.app.model.CarManufacturer;
import agent.app.model.CarModel;
import agent.app.repository.CarModelRepository;
import agent.app.service.intf.CarManufacturerService;
import agent.app.service.intf.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
