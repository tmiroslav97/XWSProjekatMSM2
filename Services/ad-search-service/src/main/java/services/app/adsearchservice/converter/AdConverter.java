package services.app.adsearchservice.converter;



import services.app.adsearchservice.dto.ad.AdPageDTO;
import services.app.adsearchservice.model.Ad;

public class AdConverter {


    public static AdPageDTO toCreateAdPageDTOFromAd(Ad ad){
        return AdPageDTO.builder()
                .id(ad.getId())
                .name(ad.getName())
                .location(ad.getLocation())
                .coverPhoto(ad.getCoverPhoto())
                .price(ad.getPrice())
                .carManufacturer(ad.getCar().getCarManufacturer())
                .carModel(ad.getCar().getCarModel())
                .childrenSeatNum(ad.getCar().getChildrenSeatNum())
                .fuelType(ad.getCar().getFuelType())
                .mileage(ad.getCar().getMileage())
                .publisherUserId(ad.getPublisherUser())
                .build();
    }


}
