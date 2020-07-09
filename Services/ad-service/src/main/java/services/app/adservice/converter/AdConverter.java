package services.app.adservice.converter;

import org.apache.commons.io.FileUtils;
import services.app.adservice.dto.ad.AdCreateDTO;
import services.app.adservice.dto.ad.AdDetailViewDTO;
import services.app.adservice.dto.ad.AdPageDTO;
import services.app.adservice.dto.ad.AdSynchronizeDTO;
import services.app.adservice.dto.sync.AdSyncDTO;
import services.app.adservice.model.Ad;
import services.app.adservice.model.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;

public class AdConverter extends AbstractConverter {

    public static Ad toCreateAdFromRequest(AdCreateDTO adCreateDTO) {
        return Ad.builder()
                .name(adCreateDTO.getName())
                .location(adCreateDTO.getLocation())
                .coverPhoto(adCreateDTO.getCoverPhoto())
                .publishedDate(DateAPI.DateTimeNow())
                .deleted(false)
                .enabled(true)
                .discountLists(new HashSet<>())
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
            byte[] fileContent = fileContent = FileUtils.readFileToByteArray(new File(photoDir + File.separator + ad.getCoverPhoto()));
            encodedString = Base64.getEncoder().encodeToString(fileContent);
        } catch (Exception e) {
            encodedString = "Nije uspjelo";
        }
        return AdPageDTO.builder()
                .id(ad.getId())
                .name(ad.getName())
                .location(ad.getLocation())
                .coverPhoto(encodedString)
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
                .priceId(ad.getPriceList())
                .distanceLimitFlag(ad.getCar().getDistanceLimitFlag().toString())
                .year(ad.getCar().getYear().toString())
                .distanceLimit(ad.getCar().getDistanceLimit())
                .carManufacturer(ad.getCar().getCarManufacturer())
                .carModel(ad.getCar().getCarModel())
                .gearboxType(ad.getCar().getGearboxType())
                .fuelType(ad.getCar().getFuelType())
                .carType(ad.getCar().getCarType())
                .mileage(ad.getCar().getMileage())
                .childrenSeatNum(ad.getCar().getChildrenSeatNum())
                .cdw(ad.getCar().getCdw())
                .androidFlag(ad.getCar().getAndroidFlag())
                .publisherUserId(ad.getPublisherUser())
                .images(images)
                .build();
    }

    public static AdSynchronizeDTO toAdSynchronizeDTOFromAd(Ad ad) {

        return AdSynchronizeDTO.builder()
                .id(ad.getId())
                .name(ad.getName())
                .location(ad.getLocation())
                .coverPhoto(ad.getCoverPhoto())
                .publishedDate(DateAPI.DateTimeToStringDateTime(ad.getPublishedDate()))
                .ratingNum(ad.getRatingNum())
                .ratingCnt(ad.getRatingCnt())
                .deleted(ad.getDeleted())
                .enabled(ad.getEnabled())
                .rentCnt(ad.getRentCnt())
                .publisherUser(ad.getPublisherUser())
                .carSynchronizeDTO(CarConverter.toCarSynchronizeDTOFromCar(ad.getCar()))
                .carCalendarTermSynchronizeDTOS(CarCalendarTermConverter.toListCarCalendarTermSyncDTOFromListCarCalendarTerm(ad.getCarCalendarTerms()))
                .build();

    }

    public static Ad toAdFromAdSyncDTO(AdSyncDTO adSyncDTO) {
        return Ad.builder()
                .name(adSyncDTO.getName())
                .location(adSyncDTO.getLocation())
                .publishedDate(DateAPI.DateTimeStringToDateTime(adSyncDTO.getPublishedDate()))
                .priceList(adSyncDTO.getPriceList())
                .deleted(false)
                .enabled(true)
                .rentCnt(adSyncDTO.getRentCnt())
                .ratingCnt(adSyncDTO.getRatingCnt())
                .ratingNum(adSyncDTO.getRatingNum())
                .comments(new HashSet<>())
                .build();
    }
}
