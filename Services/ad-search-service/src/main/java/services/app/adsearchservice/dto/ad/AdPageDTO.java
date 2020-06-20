package services.app.adsearchservice.dto.ad;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AdPageDTO {
    private Long id;
    private String name;
    private String coverPhoto;
    private String location;
    private Float price;
    private String carManufacturer;
    private String carModel;
    private String fuelType;
    private Integer childrenSeatNum;
    private Float mileage;
    private Long publisherUserId;
}
