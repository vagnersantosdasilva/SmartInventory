package com.SmartInventory.security;

import com.SmartInventory.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
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
            User user =objectMapper.readValue(request.getInputStream(), User.class);
            UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(
                        user.getUserName(),
                        user.getPassword());
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
                        .signWith(SignatureAlgorithm.ES512,SecurityConstants.SECRET_KEY)
                        .compact();

        response.addHeader(
                SecurityConstants.AUTHORIZATION_HEADER,
                SecurityConstants.TOKEN_PREFIX+jwt
        );
    }
}
