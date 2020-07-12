package services.app.pricelistanddiscountservice.dto.pricelist;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EditedPriceListDTO {
    private Long priceListId;
    private Float pricePerDay;
}
