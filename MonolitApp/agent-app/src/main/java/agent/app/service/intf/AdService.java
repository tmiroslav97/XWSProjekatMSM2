package agent.app.service.intf;


import agent.app.dto.ad.*;
import agent.app.model.Ad;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Set;

public interface AdService {
    Ad findById(Long id);

    List<Ad> findAll();

    AdPageContentDTO findAll(Integer page, Integer size);

    AdPageContentDTO findAll(Integer page, Integer size, String email);

    List<Ad> findMyAds(String email);

    Set<Ad> findAllByIds(List<Long> adIds);

    Integer syncData(String identifier, String email);

    Ad save(Ad ad);

    Ad edit(Ad ad);

    void delete(Ad ad);

    void logicalDeleteOrRevertAds(List<Ad> ads, Boolean status);

    void logicalDeleteOrRevert(Ad ad, Boolean status);

    Integer deleteById(Long id);

    Integer createAd(AdCreateDTO adCreateDTO, String email);

    Integer isExistToken(String token);

    String generateToken();

    Integer addRatingToAd(AdRatingDTO adRatingDTO);

    void syncData();

    AdPageContentDTO findAllOrdinarySearch(Integer page, Integer size, String location, DateTime startDate, DateTime endDate);

}
