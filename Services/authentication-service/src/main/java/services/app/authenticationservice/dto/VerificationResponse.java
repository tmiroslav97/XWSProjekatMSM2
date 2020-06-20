package services.app.authenticationservice.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VerificationResponse {
    String userId;
    String email;
    String roles;
}
