package services.app.authenticationservice.dto.firm;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FirmDTO {
    private Long id;
    private String email;
    private String firmName;
    private String address;
    private Long pmb;
    private Boolean deleted;
}
