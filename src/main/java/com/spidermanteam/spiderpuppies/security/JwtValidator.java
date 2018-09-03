package com.spidermanteam.spiderpuppies.security;


import com.spidermanteam.spiderpuppies.models.User;
import com.spidermanteam.spiderpuppies.security.models.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

@Component
public class JwtValidator {
    private String secret = "BrightConsulting";
    private byte[] encodedBytes = Base64.getEncoder().encode(secret.getBytes());

    public JwtUser validate(String token) {

        JwtUser jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(new String(encodedBytes))
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new JwtUser();

            jwtUser.setUsername(body.getSubject());
            jwtUser.setId(Integer.parseInt((String) body.get("userId")));
            jwtUser.setRole((String)body.get("role"));
            System.out.println((String)body.get("role"));
        } catch (Exception e) {
            System.out.println(e);
        }

        return jwtUser;
    }

    public int getUserIdFromToken(HttpServletRequest request) {
        int id = 0;
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Token ")) {
            throw new RuntimeException("JWT Token is missing");
        }
        String token = header.substring(6);
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(new String(encodedBytes))
                    .parseClaimsJws(token)
                    .getBody();

            id = Integer.parseInt((String) body.get("userId"));
        } catch (Exception e) {
            System.out.println(e);
        }
        return id;
    }
}