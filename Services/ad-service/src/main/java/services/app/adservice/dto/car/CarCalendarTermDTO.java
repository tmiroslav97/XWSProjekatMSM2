package services.app.adservice.dto.car;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CarCalendarTermDTO {
    private Long adId;
    private String startDate;
    private String endDate;
}
