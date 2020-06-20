package services.app.codebookservice.dto;


import lombok.*;
import services.app.codebookservice.model.FuelType;

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
