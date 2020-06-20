package services.app.codebookservice.dto;


import lombok.*;
import services.app.codebookservice.model.CarManufacturer;

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
