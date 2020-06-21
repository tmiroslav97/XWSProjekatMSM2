package services.app.carrequestservice.dto.carreq;

import com.sun.org.apache.xpath.internal.operations.Bool;
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
    DateTime startDate;
    DateTime endDate;
    List<Long> ads;
}
