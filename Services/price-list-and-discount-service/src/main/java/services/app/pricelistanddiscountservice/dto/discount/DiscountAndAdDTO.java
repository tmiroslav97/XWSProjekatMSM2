package services.app.pricelistanddiscountservice.dto.discount;

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
