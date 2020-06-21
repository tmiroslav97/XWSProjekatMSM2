package services.app.adservice.dto.comment;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CommentDTO {
    private Long id;
    private String content;
    private String creationDate;
    private Long publisherUserId;
    private String publisherUserFirstName;
    private String publisherUserLastName;
    private Boolean approved;


}
