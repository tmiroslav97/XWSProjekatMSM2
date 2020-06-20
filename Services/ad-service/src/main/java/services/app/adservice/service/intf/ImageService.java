package services.app.adservice.service.intf;


import org.springframework.web.multipart.MultipartFile;
import services.app.adservice.dto.image.ImageDTO;
import services.app.adservice.model.Image;

import java.util.List;

public interface ImageService {
    Image findById(Long id);
    Image findByName(String name);
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
    String uploadImage(MultipartFile photo);

}
