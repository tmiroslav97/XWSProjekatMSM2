package services.app.pricelistanddiscountservice.dto.sync;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiscountListSyncDTO {
    private String email;
    private String startDate;
    private String endDate;
    private Float discount;
}
