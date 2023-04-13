package com.example.restuserservice.security;

import com.example.restuserservice.domain.UserDTO;
import com.example.restuserservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UserService userService;
    private Environment env;

    public AuthenticationFilter(AuthenticationManager authenticationManager, UserService userService, Environment env) {
        super.setAuthenticationManager(authenticationManager);
        this.userService = userService;
        this.env = env;
        //setFilterProcessesUrl("/login");
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            System.out.println("로그인");
            UserDTO user = new ObjectMapper().readValue(request.getInputStream(), UserDTO.class);
            System.out.println("로그인22");

            if(user==null) {
                throw new RuntimeException("UserDTO is null");
            }
            System.out.println(user);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getId(),
                            user.getPassword(),
                            new ArrayList<>()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to read user data from request", e);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Failed to authenticate user", e);
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String userName = ((User) authResult.getPrincipal()).getUsername();
        System.out.println("userName(ID): " + userName);

        UserDTO userDetail = userService.getUserById(userName);
        System.out.println("UserDetail: " + userDetail);

        String token = Jwts.builder()
                .setSubject(userDetail.getId())
                .setExpiration(new Date(System.currentTimeMillis() +
                                            Long.parseLong(env.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
                .compact();

        response.addHeader("token", token);
        response.addHeader("auth", userDetail.getAuth());
        response.addHeader("ID", userDetail.getId());
    }
}
