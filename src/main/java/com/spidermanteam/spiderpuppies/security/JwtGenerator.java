package com.spidermanteam.spiderpuppies.security;

import com.spidermanteam.spiderpuppies.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtGenerator {

    private String secret = "BrightConsulting";
    private int jwtExpirationInMs = 3600;
    private byte[] encodedBytes = Base64.getEncoder().encode(secret.getBytes());

    public String generate(User user ,String role) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        Claims claims = Jwts.claims()
                .setSubject(user.getUsername());
        claims.put("userId", String.valueOf(user.getId()));
        claims.put("role", role);
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, new String(encodedBytes))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .compact();
    }

}
