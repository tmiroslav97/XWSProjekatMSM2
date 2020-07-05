package agent.app.converter;

import agent.app.dto.ad.AdCreateDTO;
import agent.app.dto.ad.AdDetailViewDTO;
import agent.app.dto.ad.AdPageDTO;
import agent.app.dto.ad.AdStatisticsDTO;
import agent.app.dto.sync.AdSyncDTO;
import agent.app.model.Ad;
import agent.app.model.Image;
import agent.app.model.enumeration.DistanceLimitEnum;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.*;

public class AdConverter extends AbstractConverter {

    public static Ad toCreateAdFromRequest(AdCreateDTO adCreateDTO) {
        return Ad.builder()
                .name(adCreateDTO.getName())
                .location(adCreateDTO.getLocation())
                .coverPhoto(adCreateDTO.getCoverPhoto())
                .distanceLimitFlag(DistanceLimitEnum.LIMITED)
                .distanceLimit(adCreateDTO.getDistanceLimit())
                .publishedDate(DateAPI.DateTimeNow())
                .deleted(false)
                .enabled(true)
                .discountLists(new HashSet<>())
                .requests(new HashSet<>())
                .comments(new HashSet<>())
                .carCalendarTerms(new HashSet<>())
                .rentCnt(0L)
                .ratingCnt(0L)
                .ratingNum(0L)
                .build();
    }

    public static AdPageDTO toCreateAdPageDTOFromAd(Ad ad, String photoDir) {
        String encodedString = "";
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(new File(photoDir + File.separator + ad.getCoverPhoto()));
            encodedString = Base64.getEncoder().encodeToString(fileContent);
        } catch (Exception e) {
            encodedString = "Nije uspjelo";
        }
        return AdPageDTO.builder()
                .id(ad.getId())
                .name(ad.getName())
                .location(ad.getLocation())
                .coverPhoto(encodedString)
                .price(ad.getPriceList().getPricePerDay())
                .carManufacturer(ad.getCar().getCarManufacturer())
                .carModel(ad.getCar().getCarModel())
                .childrenSeatNum(ad.getCar().getChildrenSeatNum())
                .fuelType(ad.getCar().getFuelType())
                .mileage(ad.getCar().getMileage())
                .build();
    }

    public static AdDetailViewDTO toAdDetailViewDTOFromAd(Ad ad, String photoDir) {
        String encodedString = "";
        List<String> images = new ArrayList<>();
        try {
            byte[] fileContent = null;
            for (Image image : ad.getImages()) {
                fileContent = fileContent = FileUtils.readFileToByteArray(new File(photoDir + File.separator + image.getName()));
                encodedString = Base64.getEncoder().encodeToString(fileContent);
                images.add(encodedString);
            }
            fileContent = fileContent = FileUtils.readFileToByteArray(new File(photoDir + File.separator + ad.getCoverPhoto()));
            encodedString = Base64.getEncoder().encodeToString(fileContent);
        } catch (Exception e) {
            encodedString = "Nije uspjelo";
        }
        return AdDetailViewDTO.builder()
                .id(ad.getId())
                .name(ad.getName())
                .location(ad.getLocation())
                .coverPhoto(encodedString)
                .publishedDate(ad.getPublishedDate().toString())
                .ratingNum(ad.getRatingNum())
                .ratingCnt(ad.getRatingCnt())
                .rentCnt(ad.getRentCnt())
                .distanceLimitFlag(ad.getDistanceLimitFlag().toString())
                .year(ad.getCar().getYear().toString())
                .distanceLimit(ad.getDistanceLimit())
                .carManufacturer(ad.getCar().getCarManufacturer())
                .carModel(ad.getCar().getCarModel())
                .gearboxType(ad.getCar().getGearboxType())
                .fuelType(ad.getCar().getFuelType())
                .carType(ad.getCar().getCarType())
                .mileage(ad.getCar().getMileage())
                .childrenSeatNum(ad.getCar().getChildrenSeatNum())
                .cdw(ad.getCar().getCdw())
                .androidFlag(ad.getCar().getAndroidFlag())
                .pricePerKm(ad.getPriceList().getPricePerKm())
                .pricePerKmCDW(ad.getPriceList().getPricePerKmCDW())
                .priceId(ad.getPriceList().getId())
                .pricePerDay(ad.getPriceList().getPricePerDay())
                .publisherUserId(ad.getPublisherUser().getId())
                .publisherUserFirstName(ad.getPublisherUser().getFirstName())
                .publisherUserLastName(ad.getPublisherUser().getLastName())
                .images(images)
                .build();
    }


    public static AdStatisticsDTO toCreateAdStatisticsDTOFromAd(Ad ad) {
        return AdStatisticsDTO.builder()
                .id(ad.getId())
                .name(ad.getName())
                .location(ad.getLocation())
                .carManufacturer(ad.getCar().getCarManufacturer())
                .carModel(ad.getCar().getCarModel())
                .mileage(ad.getCar().getMileage())
                .averageGrade((float) (ad.getRatingNum() * 1.0 / ad.getRatingCnt()))
                .comment(ad.getComments().size())
                .build();
    }

    public static AdSyncDTO toAdSyncDTOFromAd(Ad ad){
        return AdSyncDTO.builder()
                .name(ad.getName())
                .location(ad.getLocation())
                .distanceLimit(ad.getDistanceLimit())
                .distanceLimitFlag(ad.getDistanceLimitFlag().name())
                .publishedDate(DateAPI.DateTimeToStringDateTime(ad.getPublishedDate()))
                .build();
    }
}
