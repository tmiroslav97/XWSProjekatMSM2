package agent.app.dto.sync;

import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CarSyncDTO {
    private String year;
    private String carManufacturer;
    private String carModel;
    private String gearboxType;
    private String fuelType;
    private String carType;
    private Float mileage;
    private Integer childrenSeatNum;
    private String distanceLimitFlag;
    private Float distanceLimit;
    private Boolean cdw;
    private Boolean androidFlag;
    private String token;
}
