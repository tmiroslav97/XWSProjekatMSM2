package services.app.adservice.dto.sync;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CarCalendarTermSyncDTO {
    private String startDate;
    private String endDate;
}
