package agent.app.dto.pricelist;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PriceListDTO {
    private String creationDate;
    private Float pricePerKm;
    private Float pricePerKmCDW;
    private Float pricePerDay;
}
