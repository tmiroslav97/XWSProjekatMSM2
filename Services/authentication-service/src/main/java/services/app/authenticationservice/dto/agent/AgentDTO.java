package services.app.authenticationservice.dto.agent;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AgentDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Boolean local;
    private Boolean deleted;
}
