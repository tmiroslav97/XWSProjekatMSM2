package services.app.codebookservice.dto;


import lombok.*;
import services.app.codebookservice.model.CarModel;

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
