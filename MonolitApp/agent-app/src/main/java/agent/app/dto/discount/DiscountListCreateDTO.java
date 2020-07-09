package agent.app.dto.discount;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class DiscountListCreateDTO {
    private Integer dayNum;
    private Float discount;
    private String agentEmail;

}
