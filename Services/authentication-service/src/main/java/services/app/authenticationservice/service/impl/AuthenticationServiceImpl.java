package services.app.authenticationservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import services.app.authenticationservice.authentication.JwtAuthenticationRequest;
import services.app.authenticationservice.dto.SignUpDTO;
import services.app.authenticationservice.dto.VerificationResponse;
import services.app.authenticationservice.exception.NotFoundException;
import services.app.authenticationservice.model.Authority;
import services.app.authenticationservice.model.EndUser;
import services.app.authenticationservice.model.User;
import services.app.authenticationservice.model.UserTokenState;
import services.app.authenticationservice.security.TokenUtils;
import services.app.authenticationservice.service.intf.AuthenticationService;
import services.app.authenticationservice.service.intf.AuthorityService;
import services.app.authenticationservice.service.intf.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private UserService userService;

    private UserDetailsService userDetailsService;

    @Override
    public String login(JwtAuthenticationRequest jwtAuthenticationRequest) {
        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(jwtAuthenticationRequest.getUsername(),
                        jwtAuthenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        List<String> roles = user.getAuthorities().stream().map(authority -> authority.getName()).collect(Collectors.toList());
        String jwt = tokenUtils.generateToken(user.getEmail(), roles);
        return jwt;
    }

    @Override
    public Integer signUp(SignUpDTO signUpDTO) {
        if (userService.existsByEmail(signUpDTO.getEmail())) {
            return 1;
        } else if (!signUpDTO.getPassword().equals(signUpDTO.getPassword2())) {
            return 2;
        } else {
            List<Authority> auths = authorityService.findByName("ROLE_USER");
            EndUser endUser = EndUser.endUserBuilder()
                    .email(signUpDTO.getEmail())
                    .firstName(signUpDTO.getFirstName())
                    .lastName(signUpDTO.getLastName())
                    .deleted(false)
                    .enabled(true)
                    .password(passwordEncoder.encode(signUpDTO.getPassword()))
                    .canceledCnt(0)
                    .adLimitNum(3)
                    .obliged(false)
                    .authorities(auths)
                    .build();
            userService.save(endUser);
            return 3;
        }
    }

    @Override
    public UserTokenState refreshAuthenticationToken(HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(token);
        User user = (User) userDetailsService.loadUserByUsername(username);

        if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = tokenUtils.refreshToken(token);
            int expiresIn = tokenUtils.getExpiredIn();

            return new UserTokenState(refreshedToken, Long.valueOf(expiresIn));
        } else {
            return null;
        }
    }

    @Override
    public VerificationResponse verify(String token) {
        String authToken = token.substring(7);
        String email = tokenUtils.getUsernameFromToken(authToken);
        if (!userService.existsByEmail(email)) {
            throw new NotFoundException("Korisnik ne postoji u sistemu!");
        }
        User user = userService.findByEmail(email);
        String userId = user.getId().toString();
        String roles = "";
        if (tokenUtils.validateToken(authToken, user)) {
            for (Authority authority : user.getAuthorities()) {
                roles += authority.getName() + "|";
            }
            roles = roles.substring(0, roles.length() - 1);
            VerificationResponse vr = new VerificationResponse(userId, email, roles);
            return vr;
        } else {
            return null;
        }
    }
}
