package services.app.adservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import services.app.adservice.client.AuthenticationClient;
import services.app.adservice.converter.CommentConverter;
import services.app.adservice.dto.car.StatisticCarDTO;
import services.app.adservice.dto.comment.CommentCreateDTO;
import services.app.adservice.dto.comment.CommentDTO;
import services.app.adservice.dto.user.PublisherUserDTO;
import services.app.adservice.exception.ExistsException;
import services.app.adservice.exception.NotFoundException;
import services.app.adservice.model.Ad;
import services.app.adservice.model.Car;
import services.app.adservice.model.Comment;
import services.app.adservice.model.CustomPrincipal;
import services.app.adservice.repository.CommentRepository;
import services.app.adservice.service.intf.AdService;
import services.app.adservice.service.intf.CommentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AdService adService;

    @Autowired
    private AuthenticationClient authenticationClient;

    @Override
    public Comment finById(Long id) {
        return commentRepository.findById(id).orElseThrow(()-> new NotFoundException("Komentar ne postoji."));
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment save(Comment comment) {
        if(comment.getId() != null){
            if(commentRepository.existsById(comment.getId())){
                throw new ExistsException(String.format("Komentar vec postoji."));
            }
        }
        return commentRepository.save(comment);
    }

    @Override
    public Comment edit(Comment comment) {
        this.finById(comment.getId());
        return commentRepository.save(comment);
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public Integer deleteById(Long id) {
        Comment comment = this.finById(id);
        this.delete(comment);
        return 1;
    }

    @Override
    public List<StatisticCarDTO> getCarsWithMostComments(Long publisherId) {
        return null;
    }

    @Override
    public Integer setApprove(Boolean status, Long id) {
        Comment comment = this.finById(id);
        comment.setApproved(status);
        comment = this.edit(comment);
        return 1;
    }

    @Override
    public Integer createComment(CommentCreateDTO commentCreateDTO) {
        System.out.println("----------------------------------------");
        System.out.println("KREIRANJE KOMENTARA");
        Comment comment = CommentConverter.toCommentFromCommentCreateDTO(commentCreateDTO);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();
        Long publisherUser = authenticationClient.findPublishUserByEmail(principal.getToken());
        comment.setPublisherUser(publisherUser);

        Ad ad = adService.findById(commentCreateDTO.getAdId());
        comment.setAd(ad);

        comment = this.save(comment);
        ad.getComments().add(comment);
        ad = adService.edit(ad);
        System.out.println("----------------------------------------");
        return 1;
    }

    @Override
    public List<CommentDTO> findAllApprovedCommentFromAd(Long id) {
        Ad ad = adService.findById(id);
        List<CommentDTO> list = new ArrayList<>();
        Set<Comment> commentSet = ad.getComments();
        for(Comment comment: commentSet){
            if(comment.getApproved()){
                CommentDTO commentDTO = CommentConverter.toCommentDTOFromComment(comment);
                PublisherUserDTO publishUserDTO = authenticationClient.findPublishUserById(comment.getId());
                commentDTO.setPublisherUserFirstName(publishUserDTO.getPublisherUserFirstName());
                commentDTO.setPublisherUserLastName(publishUserDTO.getPublisherUserLastName());
                list.add(commentDTO);
            }
        }


        return list;
    }

    @Override
    public List<CommentDTO> findAllApprovedCommentAndUserCommentFromAd(Long id) {
        Ad ad = adService.findById(id);
        List<CommentDTO> list = new ArrayList<>();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();
        Long publisherUser = authenticationClient.findPublishUserByEmail(principal.getToken());

        Set<Comment> commentSet = ad.getComments();
        for(Comment comment: commentSet){
            if(comment.getApproved()){
                CommentDTO commentDTO = CommentConverter.toCommentDTOFromComment(comment);
                PublisherUserDTO publishUserDTO = authenticationClient.findPublishUserById(comment.getId());
                commentDTO.setPublisherUserFirstName(publishUserDTO.getPublisherUserFirstName());
                commentDTO.setPublisherUserLastName(publishUserDTO.getPublisherUserLastName());
                list.add(commentDTO);
            }else if(comment.getPublisherUser().equals(publisherUser)){
                CommentDTO commentDTO = CommentConverter.toCommentDTOFromComment(comment);
                PublisherUserDTO publishUserDTO = authenticationClient.findPublishUserById(comment.getId());
                commentDTO.setPublisherUserFirstName(publishUserDTO.getPublisherUserFirstName());
                commentDTO.setPublisherUserLastName(publishUserDTO.getPublisherUserLastName());
                list.add(commentDTO);
            }
        }
        return list;
    }
}
