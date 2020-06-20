package agent.app.dto.ad;

import agent.app.dto.car.CarCalendarTermCreateDTO;
import agent.app.dto.car.CarCreateDTO;
import agent.app.model.Image;
import lombok.*;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AdDetailViewDTO {
    private String name;
    private String coverPhoto;
    private String location;
    private String distanceLimitFlag;
    private Float distanceLimit;
    private String publishedDate;
    private Long ratingNum;
    private Long ratingCnt;
    private Long rentCnt;
    private String year;
    private String carManufacturer;
    private String carModel;
    private String gearboxType;
    private String fuelType;
    private String carType;
    private Float mileage;
    private Integer childrenSeatNum;
    private Boolean cdw;
    private Boolean androidFlag;
    private Float pricePerKm;
    private Float pricePerKmCDW;
    private Float pricePerDay;
    private Long publisherUserId;
    private String publisherUserFirstName;
    private String publisherUserLastName;
}
