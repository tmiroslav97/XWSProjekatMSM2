package agent.app.dto.ad;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AdStatisticsDTO {
    private Long id;
    private String name;
    private String location;
    private String carManufacturer;
    private String carModel;
    private Float mileage;
    private Float averageGrade;
    private Integer comment;
}
