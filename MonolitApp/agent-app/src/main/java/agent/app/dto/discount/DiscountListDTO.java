package agent.app.dto.discount;

import agent.app.model.Agent;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    private List<Long> adsId = new ArrayList<>();
}
