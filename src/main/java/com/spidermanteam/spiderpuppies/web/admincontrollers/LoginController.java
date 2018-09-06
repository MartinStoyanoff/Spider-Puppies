package com.spidermanteam.spiderpuppies.web.admincontrollers;

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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class LoginController {

    private LoginService loginService;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;
    private AdminService adminService;
    private ClientService clientService;

    @Autowired
    public LoginController(LoginService loginService, JwtTokenProvider jwtTokenProvider, UserService userService, AdminService adminService, ClientService clientService) {
        this.loginService = loginService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.adminService = adminService;
        this.clientService = clientService;
    }

    @PostMapping(value = "/login/admin")
    public UserResponse adminLogin(@RequestBody List<String> userDetails) throws Exception {
        Admin admin = adminService.findAdminByUserUsername(userDetails.get(0));
        String token = loginService.authenticateClient(userDetails);
        Long userId = jwtTokenProvider.getUserIdFromJwt(token);
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

    @PostMapping(value = "/login/client")
    public UserResponse clientLogin(@RequestBody List<String> userDetails) throws Exception {
        Client client = clientService.findClientByUserUsername(userDetails.get(0));
        String token = loginService.authenticateClient(userDetails);
        Long userId = jwtTokenProvider.getUserIdFromJwt(token);
        User user = userService.findById(userId);
        int firstLogin = user.getEnabled();
        String role =    role = userService.findUserRoleByUserId(userId);
        return new UserResponse(client.getId(), user.getUsername(), role, token);
    }
}




