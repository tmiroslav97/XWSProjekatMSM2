package agent.app.service.intf;


import agent.app.dto.car.StatisticCarDTO;
import agent.app.dto.comment.CommentCreateDTO;
import agent.app.dto.comment.CommentDTO;
import agent.app.model.Comment;

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
    void syncComment(String msg);
    List<CommentDTO> findAllApprovedCommentFromAd(Long id);
    List<CommentDTO> findAllApprovedCommentAndUserCommentFromAd(Long id);
    List<CommentDTO> findAllUnapprovedCommentFromAd();

}
