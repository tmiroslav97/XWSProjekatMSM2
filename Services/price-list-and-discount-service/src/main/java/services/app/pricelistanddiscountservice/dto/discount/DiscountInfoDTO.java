package services.app.pricelistanddiscountservice.dto.discount;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiscountInfoDTO {
    private Float discount;
    private Integer dayNum;
}
