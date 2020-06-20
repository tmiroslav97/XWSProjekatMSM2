package services.app.adsearchservice.service.intf;



import services.app.adsearchservice.dto.image.ImageDTO;
import services.app.adsearchservice.model.Image;

import java.util.List;

public interface ImageService {
    Image findById(Long id);
    List<Image> findAll();
    Image save(Image image);
    void delete(Image image);
    Image createImage(String imageName);
    Image editImage(Image image);
    Integer deleteById(Long id);
    Integer getImageSize();

    ImageDTO findImageLocationByName(String name, Long ad_id);

    String getImageName();
    Integer addImage(String imageName);

}
