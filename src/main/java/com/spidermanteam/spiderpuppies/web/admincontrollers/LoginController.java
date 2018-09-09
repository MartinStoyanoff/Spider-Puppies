package com.spidermanteam.spiderpuppies.web.admincontrollers;

import com.spidermanteam.spiderpuppies.exceptions.InvalidInputException;
import com.spidermanteam.spiderpuppies.models.Admin;
import com.spidermanteam.spiderpuppies.models.Client;
import com.spidermanteam.spiderpuppies.models.User;
import com.spidermanteam.spiderpuppies.models.reporting.UserResponse;
import com.spidermanteam.spiderpuppies.security.JwtTokenProvider;
import com.spidermanteam.spiderpuppies.services.base.AdminService;
import com.spidermanteam.spiderpuppies.services.base.ClientService;
import com.spidermanteam.spiderpuppies.services.base.LoginService;
import com.spidermanteam.spiderpuppies.services.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/login")
public class LoginController {

  private LoginService loginService;
  private UserService userService;
  private AdminService adminService;
  private ClientService clientService;

  @Autowired
  public LoginController(LoginService loginService, UserService userService, AdminService adminService, ClientService clientService) {
    this.loginService = loginService;
    this.userService = userService;
    this.adminService = adminService;
    this.clientService = clientService;
  }

  @PostMapping(value = "/admin")
  public UserResponse adminLogin(@RequestBody List<String> userDetails) throws Exception {
    if (userDetails.size() < 2) {
      throw new InvalidInputException("Invalid credentials");
    }

    Admin admin = adminService.findAdminByUserUsername(userDetails.get(0));
    String token = loginService.authenticateClient(userDetails);
    if (token == null) {
      throw new InvalidInputException("Bad credentials");
    }
    Long userId = admin.getUser().getId();
    User user = userService.findById(userId);
    int firstLogin = admin.getFirstLogin();
    String role = null;
    if (firstLogin == 1) {
      role = userService.findUserRoleByUserId(userId) + ":FirstLogin";
    } else {
      role = userService.findUserRoleByUserId(userId);
    }
    return new UserResponse(admin.getId(), user.getUsername(), role, token);

  }

  @PostMapping(value = "/client")
  public UserResponse clientLogin(@RequestBody List<String> userDetails) throws Exception {
    if (userDetails.size() < 2) {
      throw new InvalidInputException("Invalid credentials");
    }
    Client client = clientService.findClientByUserUsername(userDetails.get(0));
    String token = loginService.authenticateClient(userDetails);
    if (token == null) {
      throw new InvalidInputException("Bad credentials");
    }
    Long userId = client.getUser().getId();
    User user = userService.findById(userId);
    int firstLogin = user.getEnabled();
    String role = role = userService.findUserRoleByUserId(userId);
    return new UserResponse(client.getId(), user.getUsername(), role, token);
  }


  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  public ResponseEntity handleInvalidExtensionSpecException(MethodArgumentNotValidException e) {
    e.printStackTrace();
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(e.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(x -> x.getDefaultMessage())
            .toArray());
  }

  @ExceptionHandler
  ResponseEntity handleExtensionNotFoundException(InvalidInputException e) {
    e.printStackTrace();
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(e.getMessage());
  }
}




