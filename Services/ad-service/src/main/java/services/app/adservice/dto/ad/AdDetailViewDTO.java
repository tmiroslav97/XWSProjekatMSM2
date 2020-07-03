package services.app.adservice.dto.ad;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AdDetailViewDTO {
    private Long id;
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
    private Long priceId;
    private Float pricePerDay;
    private Float pricePerKm;
    private Float pricePerKmCDW;
    private Long publisherUserId;
    private String publisherUserFirstName;
    private String publisherUserLastName;
    private List<String> images;
}
