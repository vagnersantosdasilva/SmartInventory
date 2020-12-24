package com.SmartInventory.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    UserDetailsService userDetailsService;
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
                                  UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String token = request.getHeader(SecurityConstants.AUTHORIZATION_HEADER);
        if (token !=null && token.startsWith(SecurityConstants.TOKEN_PREFIX)){
            UsernamePasswordAuthenticationToken auth = getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        chain.doFilter(request,response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token)  {
        if (isTokenValid(token)){
            String username = getUsername(token);
            if (username!=null){
                UserDetails user = userDetailsService.loadUserByUsername(username);
                return new UsernamePasswordAuthenticationToken(
                        user,null, user.getAuthorities());
            }
        }
        return null;
    }
    private Claims getClaims(String token){
            return  Jwts.parser().setSigningKey(SecurityConstants.SECRET_KEY)
                .parseClaimsJws(token.replace("Bearer ",""))
                .getBody();
    }

    private String getUsername(String token){
        Claims claims = getClaims(token);
        if (claims!=null) return claims.getSubject();
        return null;
    }
    private boolean isTokenValid(String token) {
        Claims claims = getClaims(token);
        if (claims!=null){
           String username = claims.getSubject();
           Date expirationDate = claims.getExpiration();
           Date now = new Date(System.currentTimeMillis());
           if (now.after(expirationDate)) {
               throw new RuntimeException("Token expirado.");
           }
           if (expirationDate!=null && now.before(expirationDate)&& username!=null) return true;
        }
        return false;
    }
}
