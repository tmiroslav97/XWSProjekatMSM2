package services.app.adservice.dto.user;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserFLNameDTO {
    private Long userId;
    private String userFirstName;
    private String userLastName;
}
