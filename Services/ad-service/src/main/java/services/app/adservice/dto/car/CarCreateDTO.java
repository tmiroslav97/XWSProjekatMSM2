package services.app.adservice.dto.car;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CarCreateDTO {
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

}
