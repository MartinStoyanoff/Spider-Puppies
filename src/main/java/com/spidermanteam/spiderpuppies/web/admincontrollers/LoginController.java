package com.spidermanteam.spiderpuppies.web.admincontrollers;

import com.spidermanteam.spiderpuppies.models.User;
import com.spidermanteam.spiderpuppies.security.models.JwtUser;
import com.spidermanteam.spiderpuppies.services.base.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(value = "/admin/login")
    @ResponseBody
    public JwtUser generateTokenOnLogin(@RequestBody List<String> userdetails, HttpServletResponse response) throws Exception {
       String userName = userdetails.get(0);
       String password = userdetails.get(1);
        User loggedUser = loginService.loginCheckUserByUserNameAndPassword(userName,password);
        String token = "Token " + loginService.generatorToken(loggedUser);
        String userNameForRole = loggedUser.getUsername();
        String role = loginService.getRoleByUsername(userNameForRole);
        return new JwtUser(loggedUser, token, role);
    }
}
