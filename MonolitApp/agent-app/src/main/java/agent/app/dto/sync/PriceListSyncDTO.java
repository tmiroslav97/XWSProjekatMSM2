package agent.app.dto.sync;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PriceListSyncDTO {
    private String email;
    private String creationDate;
    private Float pricePerDay;
    private Float pricePerKm;
    private Float pricePerKmCDW;
}
