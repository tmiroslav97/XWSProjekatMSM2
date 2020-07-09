package services.app.messageservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import services.app.messageservice.dto.MessageRequestDTO;
import services.app.messageservice.model.CustomPrincipal;
import services.app.messageservice.model.Message;
import services.app.messageservice.service.intf.MessageService;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PreAuthorize("hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_USER')")
    @RequestMapping(value = "/first", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendMessage(@RequestBody MessageRequestDTO messageRequestDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();
        Integer flag = messageService.sendFirstMessage(messageRequestDTO);
        if (flag == 1) {
            return new ResponseEntity<>("Uspjesno poslata poruka", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Poruka nije uspjesno poslata", HttpStatus.OK);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_USER')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> sendMessage(@RequestBody Message message) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();
        Integer flag = messageService.sendMessage(message);
        if (flag == 1) {
            return new ResponseEntity<>("Uspjesno poslata poruka", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Poruka nije uspjesno poslata", HttpStatus.OK);
        }
    }
}
