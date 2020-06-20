package agent.app.service.intf;

import agent.app.dto.car.CarCreateDTO;
import agent.app.dto.image.ImageDTO;
import agent.app.model.Car;
import agent.app.model.Image;
import org.springframework.web.multipart.MultipartFile;

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
