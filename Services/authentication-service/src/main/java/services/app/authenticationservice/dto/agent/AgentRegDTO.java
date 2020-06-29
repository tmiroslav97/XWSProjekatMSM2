package services.app.authenticationservice.dto.agent;


import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AgentRegDTO {
    private String email;
    private String firstName;
    private String lastName;
}
