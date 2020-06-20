package services.app.adservice.service.intf;

import services.app.adservice.dto.comment.CommentCreateDTO;
import services.app.adservice.dto.car.StatisticCarDTO;
import services.app.adservice.dto.comment.CommentDTO;
import services.app.adservice.model.Car;
import services.app.adservice.model.Comment;

import java.util.List;

public interface CommentService {

    Comment finById(Long id);
    List<Comment> findAll();
    Comment save(Comment comment);
    Comment edit(Comment comment);
    void delete(Comment comment);
    Integer deleteById(Long id);
    List<StatisticCarDTO> getCarsWithMostComments(Long publisherId);
    Integer setApprove(Boolean status, Long id);
    Integer createComment(CommentCreateDTO comment);

    List<CommentDTO> findAllApprovedCommentFromAd(Long id);
    List<CommentDTO> findAllApprovedCommentAndUserCommentFromAd(Long id);

}
