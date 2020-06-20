package agent.app.dto.car;

import lombok.*;
import org.joda.time.DateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CarCalendarTermCreateDTO {

    private String startDate;
    private String endDate;

}
