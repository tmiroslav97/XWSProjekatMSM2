package agent.app.dto.codebook;

import agent.app.model.CarModel;
import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarModelDTO {
    private List<CarModel> carModels;
    private Integer totalPageCnt;
}
