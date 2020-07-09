package services.app.authenticationservice.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping(value = "/find-publish-user/ws/{email}", method = RequestMethod.GET)
    public Long findPublishUserByEmailWS(@PathVariable String email) {
        return userService.findByEmail(email).getId();
    }

}
