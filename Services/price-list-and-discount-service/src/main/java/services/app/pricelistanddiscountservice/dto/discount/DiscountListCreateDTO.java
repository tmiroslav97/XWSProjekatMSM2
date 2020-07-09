package services.app.pricelistanddiscountservice.dto.discount;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DiscountListCreateDTO {
    private Integer dayNum;
    private Float discount;
    private String agentEmail;

}
