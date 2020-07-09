package services.app.carrequestservice.dto.ad;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdRequestDTO {
    private Long id;
    private String adName;
    private String startDate;
    private String endDate;
}
