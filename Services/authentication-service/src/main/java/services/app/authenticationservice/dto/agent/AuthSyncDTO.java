package services.app.authenticationservice.dto.agent;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthSyncDTO {
    private String email;
    private String identifier;
}
