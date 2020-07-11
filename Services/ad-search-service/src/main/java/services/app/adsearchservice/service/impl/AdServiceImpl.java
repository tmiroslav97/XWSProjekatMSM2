package services.app.adsearchservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import services.app.adsearchservice.config.AppConfig;
import services.app.adsearchservice.config.RabbitMQConfiguration;
import services.app.adsearchservice.converter.*;
import services.app.adsearchservice.dto.ad.AdPageContentDTO;
import services.app.adsearchservice.dto.ad.AdPageDTO;
import services.app.adsearchservice.dto.ad.AdSynchronizeDTO;
import services.app.adsearchservice.dto.car.CarCalendarTermSynchronizeDTO;
import services.app.adsearchservice.dto.image.ImagesSynchronizeDTO;
import services.app.adsearchservice.dto.pricelist.PriceListUpdateASDTO;
import services.app.adsearchservice.dto.user.UserFLNameDTO;
import services.app.adsearchservice.exception.ExistsException;
import services.app.adsearchservice.exception.NotFoundException;
import services.app.adsearchservice.model.Ad;
import services.app.adsearchservice.model.Car;
import services.app.adsearchservice.model.CarCalendarTerm;
import services.app.adsearchservice.model.Image;
import services.app.adsearchservice.repository.AdRepository;
import services.app.adsearchservice.service.intf.AdService;
import services.app.adsearchservice.service.intf.CarCalendarTermService;
import services.app.adsearchservice.service.intf.CarService;
import services.app.adsearchservice.service.intf.ImageService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private ImageService imageService;

    @Autowired
    private CarCalendarTermService carCalendarTermService;

    @Autowired
    private CarService carService;

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Ad findById(Long id) {
        return adRepository.findById(id).orElseThrow(() -> new NotFoundException("Oglas ne postoji."));
    }

    @Override
    public List<Ad> findAll() {
        return adRepository.findAll();
    }

    @Override
    public Integer syncData(AdSynchronizeDTO adSynchronizeDTO) {
        System.out.println(adSynchronizeDTO);
        System.out.println("----------------------------------------------");
        Ad ad = AdConverter.toCreateAdFromAdSynchronizeDTO(adSynchronizeDTO);
        ad = adRepository.save(ad);
        System.out.println("ad repository");
        for (ImagesSynchronizeDTO dto : adSynchronizeDTO.getImagesSynchronizeDTOS()) {
            Image im = ImageConverter.toImageFromImageSyncDTO(dto);
            im.setAd(ad);
            im = imageService.save(im);
            System.out.println("slika" + im.getName());
        }
        for (CarCalendarTermSynchronizeDTO dto : adSynchronizeDTO.getCarCalendarTermSynchronizeDTOS()) {
            CarCalendarTerm cct = CarCalendarTermsConverter.toCarCalendarTermFromSyncDTO(dto);
            cct.setAd(ad);
            cct = carCalendarTermService.save(cct);
            System.out.println("termin" + cct.getId());
        }
        Car car = CarConverter.toCarFromCarSyncDTO(adSynchronizeDTO.getCarSynchronizeDTO());
        car = carService.save(car);
        ad.setCar(car);
        System.out.println("automobil " + car.getId());
        ad = adRepository.save(ad);
        System.out.println("----------------------------------------------");
        return 1;
    }

    @Override
    public AdPageContentDTO findAllOrdinarySearch(Integer page, Integer size, String location, String startDate, String endDate, String sort) {
        Pageable pageable = null;
        String[] parts = sort.split(" ");
        String sortParam = parts[0];
        String sortType = parts[1];
        if (sortParam.equals("mileage")) {
            pageable = PageRequest.of(page, size);
        } else {
            if (sortType.equals("asc"))
                pageable = PageRequest.of(page, size, Sort.by(sortParam).ascending());
            else
                pageable = PageRequest.of(page, size, Sort.by(sortParam).descending());
        }
        Page<Ad> ads;
        if (location.equals("") || startDate.equals("") || endDate.equals("")) {
            ads = adRepository.findAllByDeleted(false, pageable);
        } else {

            DateTime startD = DateAPI.DateTimeStringToDateTimeFromFronted(startDate);
            DateTime endD = DateAPI.DateTimeStringToDateTimeFromFronted(endDate);
            ads = adRepository.findByDeletedAndLocationAndCarCalendarTermsStartDateBeforeAndCarCalendarTermsEndDateAfter(false, location, startD, endD, pageable);
        }
        List<AdPageDTO> ret = new ArrayList<>();
        for (Ad ad : ads) {
            String userFLNameDTOStr = (String) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.USER_FL_NAME_QUEUE_NAME, ad.getPublisherUser());
            UserFLNameDTO userFLNameDTO;
            try {
                userFLNameDTO = objectMapper.readValue(userFLNameDTOStr, UserFLNameDTO.class);
            } catch (JsonProcessingException exception) {
                userFLNameDTO = new UserFLNameDTO();
            }
            AdPageDTO adPageDTO = AdConverter.toCreateAdPageDTOFromAd(ad, appConfig.getPhotoDir());
            adPageDTO.setPublisherUserFirstName(userFLNameDTO.getUserFirstName());
            adPageDTO.setPublisherUserLastName(userFLNameDTO.getUserLastName());
            ret.add(adPageDTO);
        }
        if (sortParam.equals("mileage")) {
            if (sortType.equals("asc"))
                ret.sort((AdPageDTO o1, AdPageDTO o2) -> (int) (o1.getMileage() - o2.getMileage()));
            else
                ret.sort((AdPageDTO o1, AdPageDTO o2) -> (int) (o2.getMileage() - o1.getMileage()));
        }
        AdPageContentDTO adPageContentDTO = AdPageContentDTO.builder()
                .totalPageCnt(ads.getTotalPages())
                .ads(ret)
                .build();

        return adPageContentDTO;
    }

    @Override
    public AdPageContentDTO findAllAdvancedSearch(Integer page, Integer size, String location, String startDate, String endDate,
                                                  String carManufacturer, String carModel, String carType, Float mileage,
                                                  Float mileageKM, String gearboxType, String fuelType, Integer childrenSeatNum,
                                                  Boolean cdw, Float startPrice, Float endPrice, String sort) {
//        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        Pageable pageable;

        String[] parts = sort.split(" ");
        String sortParam = parts[0];
        String sortType = parts[1];

        if (sortParam.equals("mileage")) {
            pageable = PageRequest.of(page, size);
        } else {
            if (sortType.equals("asc"))
                pageable = PageRequest.of(page, size, Sort.by(sortParam).ascending());
            else
                pageable = PageRequest.of(page, size, Sort.by(sortParam).descending());
        }
        Page<Ad> ads;
        DateTime startD = DateAPI.DateTimeStringToDateTimeFromFronted(startDate);
        DateTime endD = DateAPI.DateTimeStringToDateTimeFromFronted(endDate);
        ads = adRepository.findByDeletedAndCarCalendarTermsStartDateBeforeAndCarCalendarTermsEndDateAfter(false, location, startD, endD, carManufacturer, carModel,
                carType, mileage, mileageKM, gearboxType, fuelType, childrenSeatNum, cdw, startPrice, endPrice, pageable);

        List<AdPageDTO> ret = new ArrayList<>();
        for (Ad ad : ads) {
            String userFLNameDTOStr = (String) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.USER_FL_NAME_QUEUE_NAME, ad.getPublisherUser());
            UserFLNameDTO userFLNameDTO;
            try {
                userFLNameDTO = objectMapper.readValue(userFLNameDTOStr, UserFLNameDTO.class);
            } catch (JsonProcessingException exception) {
                userFLNameDTO = new UserFLNameDTO();
            }
            AdPageDTO adPageDTO = AdConverter.toCreateAdPageDTOFromAd(ad, appConfig.getPhotoDir());
            adPageDTO.setPublisherUserFirstName(userFLNameDTO.getUserFirstName());
            adPageDTO.setPublisherUserLastName(userFLNameDTO.getUserLastName());
            ret.add(adPageDTO);
        }

        if (sortParam.equals("mileage")) {
            if (sortType.equals("asc"))
                ret.sort((AdPageDTO o1, AdPageDTO o2) -> (int) (o1.getMileage() - o2.getMileage()));
            else
                ret.sort((AdPageDTO o1, AdPageDTO o2) -> (int) (o2.getMileage() - o1.getMileage()));
        }
        AdPageContentDTO adPageContentDTO = AdPageContentDTO.builder()
                .totalPageCnt(ads.getTotalPages())
                .ads(ret)
                .build();

        return adPageContentDTO;
    }

    @Override
    @RabbitListener(queues = RabbitMQConfiguration.AD_SEARCH_SYNC_QUEUE_NAME)
    public Integer syncData(String msg) {
        try {
            AdSynchronizeDTO adSynchronizeDTO = objectMapper.readValue(msg, AdSynchronizeDTO.class);
            Ad ad = AdConverter.toCreateAdFromAdSynchronizeDTO(adSynchronizeDTO);
            ad = adRepository.save(ad);
            for (ImagesSynchronizeDTO dto : adSynchronizeDTO.getImagesSynchronizeDTOS()) {
                Image im = ImageConverter.toImageFromImageSyncDTO(dto);
                im.setAd(ad);
                im = imageService.save(im);
            }
            for (CarCalendarTermSynchronizeDTO dto : adSynchronizeDTO.getCarCalendarTermSynchronizeDTOS()) {
                CarCalendarTerm cct = CarCalendarTermsConverter.toCarCalendarTermFromSyncDTO(dto);
                cct.setAd(ad);
                cct = carCalendarTermService.save(cct);
            }
            Car car = CarConverter.toCarFromCarSyncDTO(adSynchronizeDTO.getCarSynchronizeDTO());
            car = carService.save(car);
            ad.setCar(car);
            ad = adRepository.save(ad);
            return 1;
        } catch (JsonProcessingException exception) {
            return null;
        }
    }

    @Override
    @RabbitListener(queues = RabbitMQConfiguration.UPDATE_PL_AD_SEARCH_QUEUE_NAME)
    public void updatePriceList(String msg) {
        try {
            PriceListUpdateASDTO priceListUpdateASDTO = objectMapper.readValue(msg, PriceListUpdateASDTO.class);
            Ad ad = this.findById(priceListUpdateASDTO.getAdId());
            ad.setPricePerDay(priceListUpdateASDTO.getPricePerDay());
            ad = adRepository.save(ad);
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void addAd(Ad ad) {

    }

    @Override
    public AdPageContentDTO findAll(Integer page, Integer size) {

//        Pageable pageable;
//        if(sort.equals("-")){
//            pageable = PageRequest.of(page, size);
//        }else{
//            String par[] = sort.split(" ");
//            if(par[1].equals("opadajuce")) {
//                pageable = PageRequest.of(page, size, Sort.by(par[0]).descending());
//            }else{
//                pageable = PageRequest.of(page, size, Sort.by(par[0]).ascending());
//            }
//
//        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<Ad> ads = adRepository.findAllByDeleted(false, pageable);

        List<AdPageDTO> ret = new ArrayList<>();
        for (Ad ad : ads) {
            String userFLNameDTOStr = (String) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.USER_FL_NAME_QUEUE_NAME, ad.getPublisherUser());
            UserFLNameDTO userFLNameDTO;
            try {
                userFLNameDTO = objectMapper.readValue(userFLNameDTOStr, UserFLNameDTO.class);
            } catch (JsonProcessingException exception) {
                userFLNameDTO = new UserFLNameDTO();
            }
            AdPageDTO adPageDTO = AdConverter.toCreateAdPageDTOFromAd(ad, appConfig.getPhotoDir());
            adPageDTO.setPublisherUserFirstName(userFLNameDTO.getUserFirstName());
            adPageDTO.setPublisherUserLastName(userFLNameDTO.getUserLastName());
            ret.add(adPageDTO);
        }

        AdPageContentDTO adPageContentDTO = AdPageContentDTO.builder()
                .totalPageCnt(ads.getTotalPages())
                .ads(ret)
                .build();


        return adPageContentDTO;
    }

    @Override
    public Ad save(Ad ad) {
        if (ad.getId() != null) {
            if (adRepository.existsById(ad.getId())) {
                throw new ExistsException(String.format("Oglas vec postoji."));
            }
        }

        return adRepository.save(ad);
    }

}
