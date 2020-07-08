package agent.app.dto.cct;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CarCalendarTermSynchronizeDTO {
    private Long id;
    private String startDate;
    private String endDate;
    private Long adId;
}
