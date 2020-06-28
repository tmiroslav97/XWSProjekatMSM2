package services.app.authenticationservice.dto.agent;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AgentDTO {
    Long id;
    String email;
    String firstName;
    String lastName;
    Boolean local;
    Boolean deleted;
}
