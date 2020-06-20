package services.app.authenticationservice.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import services.app.authenticationservice.converter.PublisherUserConverter;
import services.app.authenticationservice.dto.PublisherUserDTO;
import services.app.authenticationservice.service.intf.UserService;

import java.security.Principal;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/find-publish-user", method = RequestMethod.POST)
    public Long findPublishUserByEmail(Principal principal) {
        return userService.findByEmail(principal.getName()).getId();
    }

    @RequestMapping(value = "/find-publish-user/ws", method = RequestMethod.POST)
    public Long findPublishUserByEmailWS(String email) {
        return userService.findByEmail(email).getId();
    }

    @RequestMapping(value = "/find-publish-user-by-id/{id}", method = RequestMethod.GET)
    public PublisherUserDTO findPublishUserById(@PathVariable Long id) {
        System.out.println("METODA FIND PUBLISHER NAME AND LAST NAME");
        return PublisherUserConverter.fromPublisherUserToPublisherUserDTO(userService.findById(id));
    }
}
