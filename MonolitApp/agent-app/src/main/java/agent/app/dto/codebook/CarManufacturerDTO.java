package agent.app.dto.codebook;

import agent.app.model.CarManufacturer;
import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarManufacturerDTO {
    private List<CarManufacturer> carManufacturers;
    private Integer totalPageCnt;
}
