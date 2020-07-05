package agent.app.dto.sync;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiscountListSyncDTO {
    private String email;
    private Integer dayNum;
    private Float discount;
}
