package services.app.authenticationservice.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AgentRegDTO {
    private String email;
    private String firstName;
    private String lastName;
}
