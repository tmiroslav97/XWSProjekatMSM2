package services.app.adservice.dto.pricelist;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PriceListUpdateASDTO {
    private Long adId;
    private Float pricePerDay;
}
