package services.app.authenticationservice.dto.firm;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FirmRegDTO {
    private String email;
    private String firmName;
    private Long pmb;
    private String address;
}
