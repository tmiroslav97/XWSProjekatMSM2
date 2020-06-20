package services.app.adservice.service.impl;

import org.springframework.stereotype.Service;
import services.app.adservice.dto.car.StatisticCarDTO;
import services.app.adservice.dto.comment.CommentCreateDTO;
import services.app.adservice.model.Car;
import services.app.adservice.model.Comment;
import services.app.adservice.service.intf.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Override
    public Comment finById(Long id) {
        return null;
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public Car save(Comment comment) {
        return null;
    }

    @Override
    public void delete(Comment comment) {

    }

    @Override
    public Integer deleteById(Long id) {
        return null;
    }

    @Override
    public List<StatisticCarDTO> getCarsWithMostComments(Long publisherId) {
        return null;
    }

    @Override
    public Integer setApprove(Boolean status) {
        return null;
    }

    @Override
    public Integer createComment(CommentCreateDTO comment) {
        return null;
    }
}
