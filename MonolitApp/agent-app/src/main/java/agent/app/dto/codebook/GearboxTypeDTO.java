package agent.app.dto.codebook;

import agent.app.model.GearboxType;
import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GearboxTypeDTO {
    private List<GearboxType> gearboxTypes;
    private Integer totalPageCnt;
}
