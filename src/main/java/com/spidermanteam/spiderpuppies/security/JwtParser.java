package com.spidermanteam.spiderpuppies.security;

import com.spidermanteam.spiderpuppies.models.Admin;
import com.spidermanteam.spiderpuppies.models.Client;
import com.spidermanteam.spiderpuppies.models.User;
import com.spidermanteam.spiderpuppies.services.base.AdminService;
import com.spidermanteam.spiderpuppies.services.base.ClientService;
import com.spidermanteam.spiderpuppies.services.base.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class JwtParser {

  private UserService userService;
  private AdminService adminService;
  private ClientService clientService;

  @Autowired
  public JwtParser(UserService userService, AdminService adminService, ClientService clientService) {
    this.userService = userService;
    this.adminService = adminService;
    this.clientService = clientService;
  }

  @Value("${jwt.secret}")
  private String secret;

  public String getUsernameFromToken(HttpServletRequest request) {

    String header = request.getHeader("Authorization");

    return Jwts.parser()
        .setSigningKey(secret)
        .parseClaimsJws(header.replace("Bearer ",""))
        .getBody()
        .getSubject();
  }

  public Long getUserIdFromToken(String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(secret)
        .parseClaimsJws(token)
        .getBody();

    return Long.parseLong(claims.getSubject());
  }
  public int getAdminIdByUsernameFromToken(HttpServletRequest request) {
    long userId = Long.parseLong(getUsernameFromToken(request));
    User user = userService.findById(userId);
    Admin admin = adminService.findAdminByUserUsername(user.getUsername());

    return admin.getId();
  }
  public int getClientIdByUsernameFromToken(HttpServletRequest request) {
    long userId = Long.parseLong(getUsernameFromToken(request));
    User user = userService.findById(userId);
    Client client = clientService.findClientByUserUsername(user.getUsername());

    return client.getId();
  }

}
