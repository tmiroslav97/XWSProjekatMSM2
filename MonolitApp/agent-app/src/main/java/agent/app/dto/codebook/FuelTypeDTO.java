package agent.app.dto.codebook;

import agent.app.model.FuelType;
import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FuelTypeDTO {
    private List<FuelType> fuelTypes;
    private Integer totalPageCnt;
}
