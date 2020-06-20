package agent.app.dto.ad;

import lombok.*;
import org.joda.time.DateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AdAdvancedSearchDTO {
    private DateTime startDate;
    private DateTime endDate;
    private String location;
    private String carManufacturer;
    private String carModel;
    private String fuelType;
    private String gearboxType;
    private String carType;
    private String priceFrom;
    private String priceTo;
    private Float mileage;
    private Boolean cdw;
    private Integer childrenSeatNum;
}
