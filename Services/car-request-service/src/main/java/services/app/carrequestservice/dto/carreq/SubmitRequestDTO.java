package services.app.carrequestservice.dto.carreq;

import lombok.*;
import services.app.carrequestservice.dto.ad.AdRequestDTO;
import services.app.carrequestservice.model.Ad;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubmitRequestDTO {
    String user;
    Boolean bundle;
    List<AdRequestDTO> ads;
}
