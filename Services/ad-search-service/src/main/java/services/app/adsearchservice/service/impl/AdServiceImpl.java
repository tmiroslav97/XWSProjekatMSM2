package services.app.adsearchservice.service.impl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import services.app.adsearchservice.converter.AdConverter;
import services.app.adsearchservice.converter.CarCalendarTermsConverter;
import services.app.adsearchservice.converter.CarConverter;
import services.app.adsearchservice.converter.ImageConverter;
import services.app.adsearchservice.dto.ad.AdPageContentDTO;
import services.app.adsearchservice.dto.ad.AdPageDTO;
import services.app.adsearchservice.dto.ad.AdSynchronizeDTO;
import services.app.adsearchservice.dto.car.CarCalendarTermSynchronizeDTO;
import services.app.adsearchservice.dto.car.CarSynchronizeDTO;
import services.app.adsearchservice.dto.image.ImagesSynchronizeDTO;
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
import java.util.stream.Collectors;

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
        for(ImagesSynchronizeDTO dto : adSynchronizeDTO.getImagesSynchronizeDTOS()){
            Image im = ImageConverter.toImageFromImageSyncDTO(dto);
            im.setAd(ad);
            im = imageService.save(im);
            System.out.println("slika" + im.getName());
        }
        for(CarCalendarTermSynchronizeDTO dto: adSynchronizeDTO.getCarCalendarTermSynchronizeDTOS()){
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
    public AdPageContentDTO findAllOrdinarySearch(Integer page, Integer size, String location, DateTime startDate, DateTime endDate) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<Ad> ads = adRepository.findByDeletedAndLocationAndCarCalendarTermsStartDateBeforeAndCarCalendarTermsEndDateAfter(false, location, startDate, endDate, pageable);
//        List<AdPageDTO> ret = ads.stream().map(AdConverter::toCreateAdPageDTOFromAd).collect(Collectors.toList());
        List<AdPageDTO> ret = new ArrayList<>();
                System.out.println(ret.size());
        AdPageContentDTO adPageContentDTO = AdPageContentDTO.builder()
                .totalPageCnt(ads.getTotalPages())
                .ads(ret)
                .build();

        return adPageContentDTO;
    }

    @Override
    public AdPageDTO advancedSearch() {
        return null;
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


        List<AdPageDTO> ret = ads.stream().map(AdConverter::toCreateAdPageDTOFromAd).collect(Collectors.toList());

        System.out.println(ret.size());
        AdPageContentDTO adPageContentDTO = AdPageContentDTO.builder()
                .totalPageCnt(ads.getTotalPages())
                .ads(ret)
                .build();


        return adPageContentDTO;
    }

    @Override
    public Ad save(Ad ad) {
        if(ad.getId() != null){
            if(adRepository.existsById(ad.getId())){
                throw new ExistsException(String.format("Oglas vec postoji."));
            }
        }

        return adRepository.save(ad);    }

}
