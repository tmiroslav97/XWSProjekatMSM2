package services.app.adservice.dto.ad;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DiscountAndAdDTO {
    Long mainIdDiscount;
    Long mainIdAd;
}
