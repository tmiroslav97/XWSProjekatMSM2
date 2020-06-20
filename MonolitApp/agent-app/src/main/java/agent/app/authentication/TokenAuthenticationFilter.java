package agent.app.authentication;

import agent.app.security.TokenUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private TokenUtils tokenUtils;

    private UserDetailsService userDetailsService;

    public TokenAuthenticationFilter(TokenUtils tokenHelper, UserDetailsService userDetailsService) {
        this.tokenUtils = tokenHelper;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String email;
        String authToken = tokenUtils.getToken(request);

        if (authToken != null) {
            //username iz tokena
            email = tokenUtils.getUsernameFromToken(authToken);

            if (email != null) {
                //user na osnovu username-a
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                //provjera da li je prosljedjeni token validan
                if (tokenUtils.validateToken(authToken, userDetails)) {
                    //kreiraj autentifikaciju
                    TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                    authentication.setToken(authToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        //proslijedi request dalje u sledeci filter
        chain.doFilter(request, response);
    }
}