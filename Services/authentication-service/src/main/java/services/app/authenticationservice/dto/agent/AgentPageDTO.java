package services.app.authenticationservice.dto.agent;

import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AgentPageDTO {
    private List<AgentDTO> agents;
    private Integer totalPageCnt;
}
