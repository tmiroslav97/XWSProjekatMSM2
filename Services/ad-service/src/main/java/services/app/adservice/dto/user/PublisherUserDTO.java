package services.app.adservice.dto.user;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class PublisherUserDTO {
    private Long publisherUserId;
    private String publisherUserFirstName;
    private String publisherUserLastName;
}
