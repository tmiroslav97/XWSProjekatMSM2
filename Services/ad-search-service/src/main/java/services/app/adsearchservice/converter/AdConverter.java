package services.app.adsearchservice.converter;



import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import services.app.adsearchservice.dto.ad.AdPageDTO;
import services.app.adsearchservice.dto.ad.AdSynchronizeDTO;
import services.app.adsearchservice.model.Ad;
import services.app.adsearchservice.model.enumeration.DistanceLimitEnum;

import java.io.File;
import java.util.Base64;

public class AdConverter {

    @Value("${directory.prop}")
    private static String photoDir;

    public static AdPageDTO toCreateAdPageDTOFromAd(Ad ad){

        String encodedString = "";
        try {
            byte[]  fileContent = FileUtils.readFileToByteArray(new File(photoDir + ad.getCoverPhoto()));
            encodedString = Base64.getEncoder().encodeToString(fileContent);
        } catch (Exception e) {
            encodedString = "Nije uspjelo";
        }
        return AdPageDTO.builder()
                .id(ad.getId())
                .name(ad.getName())
                .location(ad.getLocation())
                .coverPhoto(encodedString)
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
