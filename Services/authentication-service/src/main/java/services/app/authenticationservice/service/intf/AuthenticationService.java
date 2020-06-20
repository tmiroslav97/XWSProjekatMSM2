package services.app.authenticationservice.service.intf;

import services.app.authenticationservice.authentication.JwtAuthenticationRequest;
import services.app.authenticationservice.dto.SignUpDTO;
import services.app.authenticationservice.dto.VerificationResponse;
import services.app.authenticationservice.model.UserTokenState;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationService {
    String login(JwtAuthenticationRequest jwtAuthenticationRequest);
    Integer signUp(SignUpDTO signUpDTO);
    UserTokenState refreshAuthenticationToken(HttpServletRequest request);
    VerificationResponse verify(String email);
}
