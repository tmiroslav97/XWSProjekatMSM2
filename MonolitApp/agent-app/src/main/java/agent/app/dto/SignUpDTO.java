package agent.app.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {
    private String email;
    private String password;
    private String password2;
    private String firstName;
    private String lastName;
}
