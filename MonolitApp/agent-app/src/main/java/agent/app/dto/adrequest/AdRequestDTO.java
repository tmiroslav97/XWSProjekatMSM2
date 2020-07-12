package agent.app.dto.adrequest;

import agent.app.model.Report;
import lombok.*;
import org.joda.time.DateTime;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdRequestDTO {
    private Long id;
    private String adName;
    private Long adId;
    private Long mainId;
    private Integer review;
    private String startDate;
    private String endDate;
    private String distanceLimitFlag;
    private Float distanceLimit;
    private Boolean cdw;
    private Float pricePerDay;
    private Float pricePerKm;
    private Float pricePerKmCDW;
    private String token;
    private Report report;
}
