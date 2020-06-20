package services.app.carrequestservice.dto.carreq;

import lombok.*;
import services.app.carrequestservice.model.Ad;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubmitRequestDTO {
    Boolean bundle;
    String startDate;
    String endDate;
    List<Ad> ads;
}
