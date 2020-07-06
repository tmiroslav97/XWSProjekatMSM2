package services.app.carrequestservice.dto.ad;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdCarInfoDTO {
    private String distanceLimitFlag;
    private Float distanceLimit;
    private Boolean cdw;
    private Float pricePerDay;
    private Float pricePerKm;
    private Float pricePerKmCDW;
}
