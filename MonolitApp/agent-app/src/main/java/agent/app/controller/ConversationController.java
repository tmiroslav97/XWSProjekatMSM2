package agent.app.controller;

import agent.app.service.intf.ConversationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "/conv", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConversationController {

    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @PreAuthorize("hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_USER')")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<?> getMyConversations(Principal principal) {
        return new ResponseEntity<>(conversationService.findAllConversationDTOByParticipantId(principal.getName()), HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_USER')")
    @RequestMapping(value = "/{id}/msg", method = RequestMethod.GET)
    public ResponseEntity<?> getConversationMessages(@PathVariable("id") Long id, Principal principal) {
        String email = principal.getName();
        return new ResponseEntity<>(conversationService.findAllConversationMessages(id, email), HttpStatus.OK);
    }
}
