package services.app.adsearchservice.dto.ad;

import lombok.*;
import org.joda.time.DateTime;
import services.app.adsearchservice.dto.car.CarCalendarTermSynchronizeDTO;
import services.app.adsearchservice.dto.car.CarSynchronizeDTO;
import services.app.adsearchservice.dto.image.ImagesSynchronizeDTO;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AdSynchronizeDTO {
    private Long id;
    private String name;
    private String location;
    private String coverPhoto;
    private String distanceLimitFlag;
    private Float distanceLimit;
    private String publishedDate;
    private List<ImagesSynchronizeDTO> imagesSynchronizeDTOS;
    private Long ratingNum;
    private Long ratingCnt;
    private Boolean deleted;
    private Boolean enabled;
    private Long rentCnt;
    private Float pricePerDay;
    private Long publisherUser;
    private CarSynchronizeDTO carSynchronizeDTO;
    private List<CarCalendarTermSynchronizeDTO> carCalendarTermSynchronizeDTOS;

}
