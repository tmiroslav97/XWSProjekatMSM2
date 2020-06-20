package services.app.carrequestservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomPrincipal {
    private String userId;
    private String email;
    private String roles;
    private String token;
}
