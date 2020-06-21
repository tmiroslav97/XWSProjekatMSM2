package agent.app.dto.carreq;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReqAcceptDTO {
    Long id;
    String action;
}
