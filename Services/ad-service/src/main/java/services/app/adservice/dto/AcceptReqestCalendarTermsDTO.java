package services.app.adservice.dto;

import lombok.*;
import org.joda.time.DateTime;
import services.app.adservice.dto.ad.AdRequestDTO;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AcceptReqestCalendarTermsDTO {
    Long publisherUserId;
    Boolean bundle;
    List<AdRequestDTO> adRequestDTOS;
}
