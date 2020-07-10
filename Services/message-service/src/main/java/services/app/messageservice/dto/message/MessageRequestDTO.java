package services.app.messageservice.dto.message;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MessageRequestDTO {
    private String convName;
    private String content;
    private Long requestId;
    private Long publisherUserId;
    private String publisherUserFirstName;
    private String publisherUserLastName;
    private String publisherUserEmail;
    private Long endUserId;
    private String endUserFirstName;
    private String endUserLastName;
    private String endUserEmail;
}
