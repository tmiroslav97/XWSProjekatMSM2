package agent.app.config;

import agent.app.authentication.RestAuthenticationEntryPoint;
import agent.app.authentication.TokenAuthenticationFilter;
import agent.app.common.Constants;
import agent.app.security.TokenUtils;
import agent.app.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserServiceImpl jwtUserDetailsService;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Autowired
    TokenUtils tokenUtils;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers(Constants.REST_PATH + "/auth/**").permitAll()
                .antMatchers(Constants.REST_PATH + "/ad/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .cors()
                .and()
                .addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService),
                        BasicAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(HttpMethod.POST, Constants.REST_PATH + "/auth/login");
        web.ignoring().antMatchers(HttpMethod.POST, Constants.REST_PATH + "/auth/sign-up");
        web.ignoring().antMatchers(HttpMethod.GET, Constants.REST_PATH + "/ad/**");
        web.ignoring().antMatchers(HttpMethod.GET, Constants.REST_PATH + "/ad/search");
        web.ignoring().antMatchers(HttpMethod.GET, Constants.REST_PATH + "/car-man");
        web.ignoring().antMatchers(HttpMethod.GET, Constants.REST_PATH + "/car-type");
        web.ignoring().antMatchers(HttpMethod.GET, Constants.REST_PATH + "/gb-type");
        web.ignoring().antMatchers(HttpMethod.GET, Constants.REST_PATH + "/fuel-type");
        web.ignoring().antMatchers(HttpMethod.GET, Constants.REST_PATH + "/car-model");
        web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/images/**", "/imgs/**", "/img/**", "/*.html", "/favicon.ico", "/**/*.html",
                "/**/*.css", "/**/*.js", "/**/assets/**");
    }
}