package services.app.codebookservice.dto;


import lombok.*;
import services.app.codebookservice.model.GearboxType;

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
