package services.app.adservice.dto.comment;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CommentCreateDTO {
    private String content;
    private Long adId;
}
