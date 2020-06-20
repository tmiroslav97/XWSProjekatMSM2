package services.app.adsearchservice.service.intf;



import org.joda.time.DateTime;
import services.app.adsearchservice.dto.ad.AdPageContentDTO;
import services.app.adsearchservice.dto.ad.AdPageDTO;
import services.app.adsearchservice.model.Ad;

import java.util.List;

public interface AdService {
    Ad findById(Long id);
    List<Ad> findAll();
    Ad save(Ad ad);
    AdPageContentDTO findAll(Integer page, Integer size);
    void syncData();
    AdPageContentDTO findAllOrdinarySearch(Integer page, Integer size, String location, DateTime startDate, DateTime endDate);

    //moguce slati sort parametre
    AdPageDTO advancedSearch();

    void addAd(Ad ad);
}
