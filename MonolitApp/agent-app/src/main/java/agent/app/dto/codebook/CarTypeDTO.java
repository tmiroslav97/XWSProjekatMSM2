package agent.app.dto.codebook;

import agent.app.model.CarType;
import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarTypeDTO {
    private List<CarType> carTypes;
    private Integer totalPageCnt;
}
