package services.app.codebookservice.dto;


import lombok.*;
import services.app.codebookservice.model.CarType;

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
