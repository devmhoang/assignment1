package demo.backend.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import demo.backend.service.UserDetailsImpl;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

  @Value("${demo.app.jwtSecret}")
  private String jwtSecret;

  @Value("${demo.app.jwtAccessMs}")
  private int jwtAccessMs;

  @Value("${demo.app.jwtRefreshMs}")
  private int jwtRefreshMs;

  public String generateAccessJwt(Authentication auth) {
    UserDetailsImpl userPrinciple = (UserDetailsImpl) auth.getPrincipal();
    return Jwts.builder()
        .subject(userPrinciple.getUsername())
        .issuedAt(new Date())
        .expiration(new Date((new Date()).getTime() + jwtAccessMs))
        .signWith(key(), Jwts.SIG.HS256)
        .compact();
  }

  

  private SecretKey key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
  }

  public String getUsernameFromJwtToken(String token) {
    return Jwts.parser().verifyWith(key()).build()
        .parseSignedClaims(token).getPayload().getSubject();
  }

  public long getExpirationJwt(String token){
    Date exp = Jwts.parser().verifyWith(key()).build()
      .parseSignedClaims(token).getPayload().getExpiration();
    return (exp.getTime() - System.currentTimeMillis())/1000;
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().verifyWith(key()).build().parse(authToken);
      return true;
    } catch (MalformedJwtException e) {
      logger.error("INVALID JWT-01: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("EXPIRED JWT-02: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("UNSUPPORTED JWT-03: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("EMPTY JWT-04: {}", e.getMessage());
    }
    return false;
  }

  public void invalidateToken(String authToken) {

  }
}
