package services.app.adservice.dto.discountlist;

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
