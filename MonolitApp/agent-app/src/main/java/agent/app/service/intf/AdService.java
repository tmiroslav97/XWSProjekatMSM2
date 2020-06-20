package agent.app.service.intf;


import agent.app.dto.ad.AdCreateDTO;
import agent.app.dto.ad.AdPageContentDTO;
import agent.app.model.Ad;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Set;

public interface AdService {
    Ad findById(Long id);

    List<Ad> findAll();

    AdPageContentDTO findAll(Integer page, Integer size);

    AdPageContentDTO findAll(Integer page, Integer size, String email);

    Set<Ad> findAllByIds(List<Long> adIds);

    Ad save(Ad ad);

    void delete(Ad ad);

    void logicalDeleteOrRevertAds(List<Ad> ads, Boolean status);

    void logicalDeleteOrRevert(Ad ad, Boolean status);

    Integer deleteById(Long id);

    Integer createAd(AdCreateDTO adCreateDTO, String email);

    void syncData();

    AdPageContentDTO findAllOrdinarySearch(Integer page, Integer size, String location, DateTime startDate, DateTime endDate);

}
