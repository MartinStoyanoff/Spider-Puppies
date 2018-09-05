package com.spidermanteam.spiderpuppies.security;

import com.spidermanteam.spiderpuppies.models.User;
import com.spidermanteam.spiderpuppies.security.models.JwtToken;
import com.spidermanteam.spiderpuppies.security.models.JwtUser;
import com.spidermanteam.spiderpuppies.security.models.JwtUserDetails;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    private static String jwtSecret = "BrightSuperSecret";


    private static String  jwtExpirationInMs = "604800000";

    public String generateToken(Authentication authentication){
        JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal();

        Date dateNow = new Date();

        Date expireDate = new Date(dateNow.getTime() + Integer.parseInt(jwtExpirationInMs));

        return Jwts.builder()
                .setSubject(Long.toString(userDetails.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

    }

    public Long getUserIdFromJwt(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token);
            return true;
        }catch (SignatureException | IllegalArgumentException | UnsupportedJwtException | ExpiredJwtException | MalformedJwtException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
}