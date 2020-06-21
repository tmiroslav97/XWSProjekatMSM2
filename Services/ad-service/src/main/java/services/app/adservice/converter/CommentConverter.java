package services.app.adservice.converter;

import services.app.adservice.dto.comment.CommentCreateDTO;
import services.app.adservice.dto.comment.CommentDTO;
import services.app.adservice.model.Comment;

public class CommentConverter {

    public static Comment toCommentFromCommentCreateDTO(CommentCreateDTO commentCreateDTO){
        return Comment.builder()
                .content(commentCreateDTO.getContent())
                .creationDate(DateAPI.DateTimeNow())
                .approved(false)
                .build();
    }

    public static CommentDTO toCommentDTOFromComment(Comment comment){
        return CommentDTO.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .creationDate(comment.getCreationDate().toString())
                .publisherUserId(comment.getPublisherUser())
                .approved(comment.getApproved())
                .build();
    }

}
