package agent.app.dto.enduser;

import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EndUserPageDTO {
    List<EndUserDTO> endUsers;
    Integer totalPageCnt;
}
