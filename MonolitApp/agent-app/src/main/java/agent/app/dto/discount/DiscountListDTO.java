package agent.app.dto.discount;

import agent.app.model.Agent;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class DiscountListDTO {
    private Long id;
    private Integer dayNum;
    private Float discount;
    private Long agentId;
}
