package services.app.messageservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import services.app.messageservice.model.CustomPrincipal;
import services.app.messageservice.service.intf.ConversationService;

@RestController
@RequestMapping(value = "/conv", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConversationController {

    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @PreAuthorize("hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_USER')")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<?> getMyConversations() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();
        return new ResponseEntity<>(conversationService.findAllByParticipantId(Long.valueOf(cp.getUserId())), HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_USER')")
    @RequestMapping(value = "/{id}/msg", method = RequestMethod.GET)
    public ResponseEntity<?> getConversationMessages(@PathVariable("id") Long id) {
        return new ResponseEntity<>(conversationService.findAllConversationMessages(id), HttpStatus.OK);
    }
}
