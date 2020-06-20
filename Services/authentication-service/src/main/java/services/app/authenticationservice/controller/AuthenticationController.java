package services.app.authenticationservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import services.app.authenticationservice.authentication.JwtAuthenticationRequest;
import services.app.authenticationservice.dto.SignUpDTO;
import services.app.authenticationservice.dto.VerificationResponse;
import services.app.authenticationservice.model.UserTokenState;
import services.app.authenticationservice.service.intf.AuthenticationService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody JwtAuthenticationRequest authenticationRequest) {
        System.out.println("login");
        return new ResponseEntity<>(authenticationService.login(authenticationRequest), HttpStatus.OK);
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> signUp(@RequestBody SignUpDTO signUpDTO) {
        Integer retVal = authenticationService.signUp(signUpDTO);
        if (retVal == 1) {
            return new ResponseEntity<>("Email je zauzet.", HttpStatus.BAD_REQUEST);
        } else if (retVal == 2) {
            return new ResponseEntity<>("Lozinke se ne poklapaju.", HttpStatus.BAD_REQUEST);
        } else if (retVal == 3) {
            return new ResponseEntity<>("Uspjesna registracija!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Nepoznat zahtjev", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAuthenticationToken(HttpServletRequest request) {
        UserTokenState userTokenState = authenticationService.refreshAuthenticationToken(request);
        if (userTokenState != null) {
            return new ResponseEntity<>(userTokenState, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Nije moguce refresovati token", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public VerificationResponse verify(@RequestBody String token) {
        return authenticationService.verify(token);
    }



}
