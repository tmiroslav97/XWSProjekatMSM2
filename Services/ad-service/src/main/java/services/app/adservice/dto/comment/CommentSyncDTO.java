package services.app.adservice.dto.comment;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CommentSyncDTO {
    private String content;
    private Long mainId;
    private String creationDate;
    private String publisherUserEmail;
    private String publisherUserFirstName;
    private String publisherUserLastName;
}
