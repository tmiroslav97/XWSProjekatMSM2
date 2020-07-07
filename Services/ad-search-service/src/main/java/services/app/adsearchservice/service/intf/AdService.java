package services.app.adsearchservice.service.intf;



import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.RequestParam;
import services.app.adsearchservice.dto.ad.AdPageContentDTO;
import services.app.adsearchservice.dto.ad.AdPageDTO;
import services.app.adsearchservice.dto.ad.AdSynchronizeDTO;
import services.app.adsearchservice.model.Ad;

import java.util.List;

public interface AdService {
    Ad findById(Long id);
    List<Ad> findAll();
    Ad save(Ad ad);
    AdPageContentDTO findAll(Integer page, Integer size);
    Integer syncData(AdSynchronizeDTO adSynchronizeDTO);
    AdPageContentDTO findAllOrdinarySearch(Integer page, Integer size, String location, String startDate, String endDate, String sort);
    AdPageContentDTO findAllAdvancedSearch(Integer page, Integer size, String location, String startDate, String endDate,
                                           String carManufacturer, String carModel, String carType, Float mileage,
                                           Float mileageKM, String gearboxType, String fuelType, Integer childrenSeatNum,
                                           Boolean cdw, Float startPrice, Float endPrice, String sort);

    Integer syncData(String msg);

    void addAd(Ad ad);
}
