package agent.app.service.intf;

import agent.app.authentication.JwtAuthenticationRequest;
import agent.app.dto.SignUpDTO;
import agent.app.model.UserTokenState;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationService {
    String login(JwtAuthenticationRequest jwtAuthenticationRequest);
    Integer signUp(SignUpDTO signUpDTO);
    UserTokenState refreshAuthenticationToken(HttpServletRequest request);
}
