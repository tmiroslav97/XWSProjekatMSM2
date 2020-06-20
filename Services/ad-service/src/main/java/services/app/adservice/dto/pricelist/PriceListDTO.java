package services.app.adservice.dto.pricelist;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PriceListDTO {
    private Long priceListId;
    private String creationDate;
    private Float pricePerKm;
    private Float pricePerKmCDW;
    private Float pricePerDay;
}
