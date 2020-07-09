package services.app.authenticationservice.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EndUserDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Integer canceledCnt;
    private Boolean enabled;
    private Boolean obligated;
    private Boolean deleted;
}
