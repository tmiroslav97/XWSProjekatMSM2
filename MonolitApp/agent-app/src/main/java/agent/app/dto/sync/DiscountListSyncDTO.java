package agent.app.dto.sync;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiscountListSyncDTO {
    private String startDate;
    private String endDate;
    private Float discount;
}
