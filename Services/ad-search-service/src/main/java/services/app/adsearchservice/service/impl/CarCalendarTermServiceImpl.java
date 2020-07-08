package services.app.adsearchservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.app.adsearchservice.config.RabbitMQConfiguration;
import services.app.adsearchservice.converter.CarCalendarTermsConverter;
import services.app.adsearchservice.dto.car.CarCalendarTermSynchronizeDTO;
import services.app.adsearchservice.exception.ExistsException;
import services.app.adsearchservice.exception.NotFoundException;
import services.app.adsearchservice.model.Ad;
import services.app.adsearchservice.model.CarCalendarTerm;
import services.app.adsearchservice.repository.CarCalendarTermRepository;
import services.app.adsearchservice.service.intf.AdService;
import services.app.adsearchservice.service.intf.CarCalendarTermService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CarCalendarTermServiceImpl implements CarCalendarTermService {

    @Autowired
    private CarCalendarTermRepository carCalendarTermRepository;

    @Autowired
    private AdService adService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public CarCalendarTerm findById(Long id) {
        return carCalendarTermRepository.findById(id).orElseThrow(() -> new NotFoundException("Termin ne postoji"));
    }

    @Override
    public List<CarCalendarTerm> findAll() {
        return carCalendarTermRepository.findAll();
    }

    @Override
    public CarCalendarTerm save(CarCalendarTerm carCalendarTerm) {
        if (carCalendarTermRepository.existsById(carCalendarTerm.getId())) {
            throw new ExistsException(String.format("Termin vec postoji."));
        }
        return carCalendarTermRepository.save(carCalendarTerm);
    }

    @Override
    public void delete(CarCalendarTerm carCalendarTerm) {
        carCalendarTermRepository.delete(carCalendarTerm);
    }

    @Override
    public CarCalendarTerm createCarCalendarTerm(CarCalendarTermSynchronizeDTO dto) {
        CarCalendarTerm cct = CarCalendarTermsConverter.toCarCalendarTermFromSyncDTO(dto);
        return carCalendarTermRepository.save(cct);
    }

    @Override
    public CarCalendarTerm editCarCalendarTerm(CarCalendarTerm carCalendarTerm) {
        this.findById(carCalendarTerm.getId());
        return this.save(carCalendarTerm);
    }

    @Override
    public Integer deleteById(Long id) {
        CarCalendarTerm cct = this.findById(id);
        this.delete(cct);
        return 1;
    }

    @Override
    @RabbitListener(queues = RabbitMQConfiguration.CCT_SYNC_QUEUE_NAME)
    public void carCalendarTermSync(String msg) {
        try {
            List<CarCalendarTermSynchronizeDTO> carCalendarTermSynchronizeDTOS = Arrays.asList(objectMapper.readValue(msg, CarCalendarTermSynchronizeDTO[].class));
            List<CarCalendarTerm> carCalendarTerms = new ArrayList<>();
            for (CarCalendarTermSynchronizeDTO carCalendarTermSynchronizeDTO : carCalendarTermSynchronizeDTOS) {
                CarCalendarTerm carCalendarTerm = CarCalendarTermsConverter.toCarCalendarTermFromSyncDTO(carCalendarTermSynchronizeDTO);
                Ad ad = adService.findById(carCalendarTermSynchronizeDTO.getAdId());
                carCalendarTerm.setAd(ad);
                carCalendarTerms.add(carCalendarTerm);
            }
            carCalendarTermRepository.saveAll(carCalendarTerms);
            return;
        } catch (JsonProcessingException exception) {
            return;
        }
    }
}
