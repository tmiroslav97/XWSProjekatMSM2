package services.app.adsearchservice.converter;

import services.app.adsearchservice.dto.image.ImagesSynchronizeDTO;
import services.app.adsearchservice.model.Image;

public class ImageConverter {

    public static Image toImageFromImageSyncDTO(ImagesSynchronizeDTO dto){
        return Image.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}
