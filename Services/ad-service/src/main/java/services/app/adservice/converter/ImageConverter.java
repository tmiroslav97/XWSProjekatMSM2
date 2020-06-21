package services.app.adservice.converter;

import services.app.adservice.dto.image.ImagesSynchronizeDTO;
import services.app.adservice.model.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ImageConverter {

    public static List<ImagesSynchronizeDTO> toImagesSynchronizeDTOlistFromImages(Set<Image> images){

        List<ImagesSynchronizeDTO> imagesSynchronizeDTOS = new ArrayList<>();
        System.out.println("SLIKE ");
        for(Image im : images){
            System.out.println(im.getName());
            ImagesSynchronizeDTO imagesSynchronizeDTO = ImagesSynchronizeDTO.builder()
                    .id(im.getId())
                    .name(im.getName())
                    .adId(im.getAd().getId())
                    .build();
            imagesSynchronizeDTOS.add(imagesSynchronizeDTO);
        }

        return imagesSynchronizeDTOS;

    }

    public static ImagesSynchronizeDTO toImagesSynchronizeDTOFromImage(Image images){
        System.out.println("SLIKA ");

        System.out.println(images.getName());
        return ImagesSynchronizeDTO.builder()
                .id(images.getId())
                .name(images.getName())
                .adId(images.getAd().getId())
                .build();
    }
}
