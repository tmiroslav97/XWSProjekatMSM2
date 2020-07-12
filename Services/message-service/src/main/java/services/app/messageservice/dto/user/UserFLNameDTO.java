package services.app.messageservice.dto.user;

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
    private String userEmail;
    private Boolean local;
}
