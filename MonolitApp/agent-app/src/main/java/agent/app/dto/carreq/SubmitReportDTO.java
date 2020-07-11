package agent.app.dto.carreq;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubmitReportDTO {
    private Float distanceTraveled;
    private String description;
    private Long adId;
    private String email; //email od korisnika koji treba da plati

}
