package services.app.adservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import services.app.adservice.dto.comment.CommentCreateDTO;
import services.app.adservice.service.intf.CommentService;

@RestController
@RequestMapping(value = "/comment", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllApprovedCommentFromAd(@PathVariable("id") Long id) {
        return new ResponseEntity<>(commentService.findAllApprovedCommentFromAd(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/all-unapproved", method = RequestMethod.GET)
    public ResponseEntity<?> getAllUnapprovedCommentFromAd() {
        return new ResponseEntity<>(commentService.findAllUnapprovedCommentFromAd(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping(value = "/from-user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllApprovedCommentAndAllUserCommentFromAd(@PathVariable("id") Long id) {
        return new ResponseEntity<>(commentService.findAllApprovedCommentAndUserCommentFromAd(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addCommentForAd(@RequestBody CommentCreateDTO commentCreateDTO) {
        Integer rez = commentService.createComment(commentCreateDTO);
        if (rez == 1) {
            return new ResponseEntity<>("Dodali ste komentar.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Desila se nepoznata greska.", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/approved/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> approvedComment(@PathVariable("id") Long id) {
        Integer rez = commentService.setApprove(true, id);
        if (rez == 1) {
            return new ResponseEntity<>("Odobrili ste komentar.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Desila se nepoznata greska.", HttpStatus.BAD_REQUEST);
        }
    }


}
