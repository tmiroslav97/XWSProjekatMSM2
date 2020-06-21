package services.app.adservice.dto.car;

import lombok.*;
import org.joda.time.DateTime;

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
