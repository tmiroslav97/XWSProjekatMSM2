package services.app.adservice.dto.ad;

import lombok.*;
import services.app.adservice.dto.discountlist.DiscountInfoDTO;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdCarInfoDTO {
    private String token;
    private String distanceLimitFlag;
    private Float distanceLimit;
    private Boolean cdw;
    private Float pricePerDay;
    private Float pricePerKm;
    private Float pricePerKmCDW;
    private Float mileage;
    private List<DiscountInfoDTO> discountInfoDTOS;
}
