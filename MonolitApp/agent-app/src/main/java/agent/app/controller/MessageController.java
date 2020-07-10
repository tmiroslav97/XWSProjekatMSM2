package agent.app.controller;

import agent.app.model.Message;
import agent.app.service.intf.MessageService;
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

import java.security.Principal;

@RestController
@RequestMapping(value = "msg", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }


    @PreAuthorize("hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_USER')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> sendMessage(@RequestBody Message message, Principal principal) {

        Integer flag = messageService.sendMessage(message,principal.getName());
        if (flag == 1) {
            return new ResponseEntity<>("Uspjesno poslata poruka", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Desila se nepoznata greska", HttpStatus.BAD_REQUEST);
        }
    }
}
