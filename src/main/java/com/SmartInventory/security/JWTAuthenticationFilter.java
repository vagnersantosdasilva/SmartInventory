package com.SmartInventory.security;

import com.SmartInventory.security.DTO.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //TODO:Pegar informações que vem do JSON e transforma-las em user
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            UserDTO user =objectMapper.readValue(request.getInputStream(), UserDTO.class);
            UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword(),
                        new ArrayList<>()
            );
            return authenticationManager.authenticate(upat);

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        String jwt = Jwts.builder()
                        .setSubject(userDetails.getUsername())
                        .setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
                        .claim("displayName",userDetails.getDisplayName())
                        .signWith(SignatureAlgorithm.HS512,SecurityConstants.SECRET_KEY)
                        .compact();

        response.addHeader(
                SecurityConstants.AUTHORIZATION_HEADER,
                SecurityConstants.TOKEN_PREFIX+jwt
        );
    }
}
