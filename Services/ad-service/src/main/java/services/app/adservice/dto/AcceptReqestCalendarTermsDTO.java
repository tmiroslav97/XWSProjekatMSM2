package services.app.adservice.dto;

import lombok.*;
import org.joda.time.DateTime;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AcceptReqestCalendarTermsDTO {
    Boolean bundle;
    String startDate;
    String endDate;
    List<Long> ads;
}
