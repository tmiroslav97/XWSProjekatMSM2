package services.app.authenticationservice.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VerificationResponse {
    private String userId;
    private String email;
    private String roles;
}
