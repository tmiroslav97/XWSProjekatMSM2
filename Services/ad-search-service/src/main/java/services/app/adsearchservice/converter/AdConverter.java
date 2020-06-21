package services.app.adsearchservice.converter;



import services.app.adsearchservice.dto.ad.AdPageDTO;
import services.app.adsearchservice.dto.ad.AdSynchronizeDTO;
import services.app.adsearchservice.model.Ad;
import services.app.adsearchservice.model.enumeration.DistanceLimitEnum;

public class AdConverter {


    public static AdPageDTO toCreateAdPageDTOFromAd(Ad ad){
        return AdPageDTO.builder()
                .id(ad.getId())
                .name(ad.getName())
                .location(ad.getLocation())
                .coverPhoto(ad.getCoverPhoto())
                .price(ad.getPricePerDay())
                .carManufacturer(ad.getCar().getCarManufacturer())
                .carModel(ad.getCar().getCarModel())
                .childrenSeatNum(ad.getCar().getChildrenSeatNum())
                .fuelType(ad.getCar().getFuelType())
                .mileage(ad.getCar().getMileage())
                .publisherUserId(ad.getPublisherUser())
                .build();
    }

    public static Ad toCreateAdFromAdSynchronizeDTO(AdSynchronizeDTO dto){
        System.out.println("publish date: "+ dto.getPublishedDate());
        return Ad.builder()
                .id(dto.getId())
                .name(dto.getName())
                .location(dto.getLocation())
                .coverPhoto(dto.getCoverPhoto())
                .distanceLimitFlag(DistanceLimitEnum.valueOf(dto.getDistanceLimitFlag()))
                .distanceLimit(dto.getDistanceLimit())
                .publishedDate(DateAPI.dateStringToDateTime(dto.getPublishedDate()))
                .ratingNum(dto.getRatingNum())
                .ratingCnt(dto.getRatingCnt())
                .deleted(dto.getDeleted())
                .enabled(dto.getEnabled())
                .rentCnt(dto.getRentCnt())
                .pricePerDay(dto.getPricePerDay())
                .publisherUser(dto.getPublisherUser())
                .build();
    }

}
