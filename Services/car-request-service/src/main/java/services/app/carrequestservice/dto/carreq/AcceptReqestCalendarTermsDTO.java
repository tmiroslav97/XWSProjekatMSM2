package services.app.carrequestservice.dto.carreq;

import lombok.*;
import org.joda.time.DateTime;
import services.app.carrequestservice.dto.ad.AdRequestDTO;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AcceptReqestCalendarTermsDTO {
    Boolean bundle;
    List<AdRequestDTO> adRequestDTOS;
}
