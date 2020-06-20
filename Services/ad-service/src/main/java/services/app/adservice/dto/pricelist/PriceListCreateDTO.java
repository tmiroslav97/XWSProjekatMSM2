package services.app.adservice.dto.pricelist;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class PriceListCreateDTO {
    private String creationDate;
    private Float pricePerKm;
    private Float pricePerKmCDW;
    private Float pricePerDay;
    private Long id;
    private String publisherUsername;
}
