package agent.app.dto.enduser;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EndUserDTO {

    Long id;
    String email;
    String firstName;
    String lastName;
    Integer canceledCnt;
    Boolean enabled;
    Boolean obligated;
    Boolean deleted;
}
