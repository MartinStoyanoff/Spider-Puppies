package com.spidermanteam.spiderpuppies.web.admincontrollers;

import com.spidermanteam.spiderpuppies.models.User;
import com.spidermanteam.spiderpuppies.models.reporting.UserResponse;
import com.spidermanteam.spiderpuppies.security.JwtTokenProvider;
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

    @Autowired
    public LoginController(LoginService loginService, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.loginService = loginService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping(value = "/login/admin")
    public UserResponse adminLogin(@RequestBody List<String> userDetails) throws Exception {
//        if(userDetails.size()!=2||userDetails.get(0)==null||userDetails.get(1)==null){
//            return  new ResponseEntity<String>("Missing credentials", HttpStatus.BAD_REQUEST);
//        }
        return login(userDetails);
    }

    @PostMapping(value = "/login/client")
    public UserResponse clientLogin(@RequestBody List<String> userDetails) throws Exception {
        return login(userDetails);
    }

    private UserResponse login(List<String> userDetails) {
        String token = loginService.authenticateClient(userDetails);
        Long userId = jwtTokenProvider.getUserIdFromJwt(token);
        User user = userService.findById(userId);
        int firstLogin = user.getEnabled();
        String role = null;
        if (firstLogin == 1) {
            role = userService.findUserRoleByUserId(userId) + ":FirstLogin";
        } else {
            role = userService.findUserRoleByUserId(userId);
        }
        return new UserResponse(userId, user.getUsername(), role, token);
    }

}
