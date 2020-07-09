package agent.app.service.impl;

import agent.app.converter.CommentConverter;
import agent.app.converter.DateAPI;
import agent.app.dto.SignUpDTO;
import agent.app.dto.car.StatisticCarDTO;
import agent.app.dto.comment.CommentCreateDTO;
import agent.app.dto.comment.CommentDTO;
import agent.app.dto.comment.CommentSyncDTO;
import agent.app.exception.ExistsException;
import agent.app.exception.NotFoundException;
import agent.app.model.Ad;
import agent.app.model.Comment;
import agent.app.model.EndUser;
import agent.app.model.PublisherUser;
import agent.app.repository.CommentRepository;
import agent.app.service.intf.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
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
    private EndUserService endUserService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private PublisherUserService publisherUserService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Comment finById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Komentar ne postoji."));
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() != null) {
            if (commentRepository.existsById(comment.getId())) {
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
    @RabbitListener(queues = "#{autoDeleteComment.name}")
    public void syncComment(String msg) {
        try {
            CommentSyncDTO commentSyncDTO = objectMapper.readValue(msg, CommentSyncDTO.class);
            Ad ad = adService.findByMainId(commentSyncDTO.getMainId());
            EndUser endUser = endUserService.findByEmail(commentSyncDTO.getPublisherUserEmail());
            if (endUser == null) {
                SignUpDTO signUpDTO = SignUpDTO.builder()
                        .email(commentSyncDTO.getPublisherUserEmail())
                        .firstName(commentSyncDTO.getPublisherUserFirstName())
                        .lastName(commentSyncDTO.getPublisherUserLastName())
                        .password("12345")
                        .password2("12345")
                        .build();
                authenticationService.signUp(signUpDTO);
                endUser = endUserService.findByEmail(commentSyncDTO.getPublisherUserEmail());
            }
            Comment comment = Comment.builder()
                    .content(commentSyncDTO.getContent())
                    .approved(false)
                    .creationDate(DateAPI.DateTimeStringToDateTime(commentSyncDTO.getCreationDate()))
                    .ad(ad)
                    .publisherUser(endUser)
                    .build();
            this.save(comment);
        } catch (JsonProcessingException exception) {
            return;
        }
    }

    @Override
    public Integer createComment(CommentCreateDTO commentCreateDTO) {
        Comment comment = CommentConverter.toCommentFromCommentCreateDTO(commentCreateDTO);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Principal principal = (Principal) auth.getPrincipal();
        PublisherUser publisherUser = publisherUserService.findByEmail(principal.getName());
        comment.setPublisherUser(publisherUser);

        Ad ad = adService.findById(commentCreateDTO.getAdId());
        comment.setAd(ad);

        comment = this.save(comment);
        ad.getComments().add(comment);
        ad = adService.edit(ad);
        return 1;
    }

    @Override
    public List<CommentDTO> findAllApprovedCommentFromAd(Long id) {
        Ad ad = adService.findById(id);
        List<CommentDTO> list = new ArrayList<>();
        Set<Comment> commentSet = ad.getComments();
        for (Comment comment : commentSet) {
            if (comment.getApproved()) {
                CommentDTO commentDTO = CommentConverter.toCommentDTOFromComment(comment);
                PublisherUser publisherUser = publisherUserService.findByEmail(comment.getPublisherUser().getEmail());
                commentDTO.setPublisherUserFirstName(publisherUser.getFirstName());
                commentDTO.setPublisherUserLastName(publisherUser.getLastName());
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
        Principal principal = (Principal) auth.getPrincipal();
        PublisherUser publisherUser = publisherUserService.findByEmail(principal.getName());

        Set<Comment> commentSet = ad.getComments();
        for (Comment comment : commentSet) {
            if (comment.getApproved()) {
                CommentDTO commentDTO = CommentConverter.toCommentDTOFromComment(comment);
                PublisherUser pu = publisherUserService.findByEmail(comment.getPublisherUser().getEmail());
                commentDTO.setPublisherUserFirstName(pu.getFirstName());
                commentDTO.setPublisherUserLastName(pu.getLastName());
                list.add(commentDTO);
            } else if (comment.getPublisherUser().equals(publisherUser)) {
                CommentDTO commentDTO = CommentConverter.toCommentDTOFromComment(comment);
                PublisherUser pu = publisherUserService.findByEmail(comment.getPublisherUser().getEmail());
                commentDTO.setPublisherUserFirstName(pu.getFirstName());
                commentDTO.setPublisherUserLastName(pu.getLastName());
                list.add(commentDTO);
            }
        }
        return list;
    }

    @Override
    public List<CommentDTO> findAllUnapprovedCommentFromAd() {
        List<CommentDTO> list = new ArrayList<>();
        List<Comment> comments = this.findAll();
        for (Comment comment : comments) {
            if (!comment.getApproved()) {
                CommentDTO commentDTO = CommentConverter.toCommentDTOFromComment(comment);
                PublisherUser pu = publisherUserService.findByEmail(comment.getPublisherUser().getEmail());
                commentDTO.setPublisherUserFirstName(pu.getFirstName());
                commentDTO.setPublisherUserLastName(pu.getLastName());
                list.add(commentDTO);
            }
        }


        return list;
    }
}
