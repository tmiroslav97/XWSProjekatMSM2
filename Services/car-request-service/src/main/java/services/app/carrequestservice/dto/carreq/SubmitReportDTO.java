package services.app.carrequestservice.dto.carreq;

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

}
