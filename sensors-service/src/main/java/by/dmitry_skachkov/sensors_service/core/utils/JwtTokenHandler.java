package by.dmitry_skachkov.sensors_service.core.utils;

import by.dmitry_skachkov.sensors_service.config.properties.JWTProperty;
import by.dmitryskachkov.exception.exceptions.TokenException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtTokenHandler {

    private final JWTProperty property;

    private final ObjectMapper objectMapper;

    public JwtTokenHandler(JWTProperty property, ObjectMapper objectMapper) {
        this.property = property;
        this.objectMapper = objectMapper;
    }

    public String generateAccessToken(UserSecurity userSecurity) {
        try {
            String token = Jwts.builder()
                    .setSubject(objectMapper.writeValueAsString(userSecurity))
                    .setIssuer(property.getIssuer())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7))) // 1 week
                    .signWith(SignatureAlgorithm.HS512, property.getSecret())
                    .compact();
            addToContext(userSecurity);
            return token;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void addToContext(UserSecurity userSecurity) {
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userSecurity, null, userSecurity.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public UserSecurity getUser(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(property.getSecret())
                .parseClaimsJws(token)
                .getBody();
        try {
            return objectMapper.readValue(claims.getSubject(), UserSecurity.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(property.getSecret())
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(property.getSecret()).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            TokenException error = new TokenException("Invalid JWT signature");
            error.setHttpStatusCode(HttpStatus.UNAUTHORIZED);
            throw error;
        } catch (MalformedJwtException ex) {
            TokenException error = new TokenException("Invalid JWT token");
            error.setHttpStatusCode(HttpStatus.UNAUTHORIZED);
            throw error;
        } catch (ExpiredJwtException ex) {
            TokenException error = new TokenException("Expired JWT token");
            error.setHttpStatusCode(HttpStatus.FORBIDDEN);
            throw error;
        } catch (UnsupportedJwtException ex) {
            TokenException error = new TokenException("Unsupported JWT token");
            error.setHttpStatusCode(HttpStatus.UNAUTHORIZED);
            throw error;
        } catch (IllegalArgumentException ex) {
            TokenException error = new TokenException("JWT claims string is empty");
            error.setHttpStatusCode(HttpStatus.BAD_REQUEST);
            throw error;
        }
    }
}