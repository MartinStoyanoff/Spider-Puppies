package com.spidermanteam.spiderpuppies.security;

import com.spidermanteam.spiderpuppies.security.models.JwtUserDetails;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration}")
  private String jwtExpirationInMs;

  public String generateToken(Authentication authentication) {
    JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal();

    Date dateNow = new Date();

    Date expireDate = new Date(dateNow.getTime() + Integer.parseInt(jwtExpirationInMs));

    return Jwts.builder()
        .setSubject(Long.toString(userDetails.getId()))
        .setIssuedAt(dateNow)
        .setExpiration(expireDate)
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();
  }

  boolean validateToken(String token) {
    try {
      Jwts.parser()
          .setSigningKey(secret)
          .parseClaimsJws(token);
      return true;
    } catch (SignatureException | IllegalArgumentException | UnsupportedJwtException | ExpiredJwtException | MalformedJwtException ex) {
      System.out.println(ex.getMessage());
    }
    return false;
  }

}