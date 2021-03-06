package services.app.adservice.service.intf;

import services.app.adservice.dto.AcceptReqestCalendarTermsDTO;
import services.app.adservice.dto.ad.*;

import services.app.adservice.dto.car.StatisticCarDTO;
import services.app.adservice.model.Ad;
import services.app.adservice.model.AdSync;

import java.util.List;

public interface AdService {

    Ad findById(Long id);
    List<Ad> findAll();
    AdPageContentDTO findAll(Integer page, Integer size, String userId);
    Ad save(Ad ad);
    Ad edit(Ad ad);
    void delete(Ad ad);
    Integer deleteById(Long id);
    AdPageContentDTO findAll(Integer page, Integer size);
    Integer createAd(AdCreateDTO adCreateDTO);
    List<StatisticCarDTO> getCarsWithBestRating(Long publisherId);
    Boolean acceptCarCalendar(String msg);
    Long syncAd(String msg);
    void setRating(AdRatingDTO ad);
    void logicalDeleteOrRevertAds(List<Ad> ads, Boolean status);
    void logicalDeleteOrRevert(Ad ad, Boolean status);
    String generateToken();
    Integer isExistToken(String token);
    Integer addRatingToAd(AdRatingDTO adRatingDTO);
    AdDetailViewDTO getAdDetailView(Long ad_id);
    List<Long> findPricelistsFromAds();
    List<Ad> findAllFromPublisher(Long publisherId);
    Integer reversePricelist(ReversePricelistDTO reversePricelistDTO);
    String findAdCarInfoById(Long id);
    List<Long> findAdsFromDiscount(Long discountId);
    Integer addDiscountToAd(Long discountId, Long adId);
    Integer removeDiscountToAd(Long discountId, Long adId);
    Integer addDiscount(Long discountId);
    void editPriceList(String msg);
    List<Ad> findMyAds(Long publisher_id);
    AdStatisticsDTO findBestAverageGrade(Long publisher_id);
    AdStatisticsDTO findMaxMileage(Long publisher_id);
    AdStatisticsDTO findMaxComment(Long publisher_id);


    Integer deleteDiscount(Long discountId);
    void addDiscountRabbit(Long discountId);
    void deleteDiscountRabbit(Long discountId);
    void addDiscountToAdRabbit(String string);
    void deleteDiscountFromAdRabbit(String string);
    Long authAgent(String email, String identifier);
    Long createAdFromAgent(AdSync adSync);
    void deleteAd(Long publisherUserId);
    void revertAd(Long publisherUserId);

}
