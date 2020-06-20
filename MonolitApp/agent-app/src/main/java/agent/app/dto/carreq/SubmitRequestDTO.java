package agent.app.dto.carreq;

import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubmitRequestDTO {
    //id objavljivaca
    Long userId;
    Boolean bundle;
    String startDate;
    String endDate;
    List<Long> adIds;
}
