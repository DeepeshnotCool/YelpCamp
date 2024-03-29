package me.deepesh.springyelpcamp.service.impl;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.deepesh.springyelpcamp.model.UserDetailsImpl;
import me.deepesh.springyelpcamp.config.YelpcampConfig;
import me.deepesh.springyelpcamp.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class JwtServiceImpl implements JwtService {

  private final YelpcampConfig yelpcampConfig;

  @Override
  public String generateJwtToken(Authentication authentication) {

    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

    return Jwts.builder()
        .setSubject((userPrincipal.getUsername()))
        .setIssuedAt(new Date())
        .setExpiration(Date.from(Instant.now().plusMillis(yelpcampConfig.getJwtExpirationMs())))
        .signWith(SignatureAlgorithm.HS512, yelpcampConfig.getJwtSecret())
        .compact();
  }

  @Override
  public String getUserNameFromJwtToken(String token) {

    return Jwts.parser()
        .setSigningKey(yelpcampConfig.getJwtSecret())
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  @Override
  public boolean validateJwtToken(String authToken) {

    try {
      Jwts.parser().setSigningKey(yelpcampConfig.getJwtSecret()).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException e) {
      log.error("Invalid JWT signature: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      log.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      log.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      log.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      log.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }
}
