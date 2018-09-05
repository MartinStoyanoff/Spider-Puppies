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

    @PostMapping(value = "/login")
    public String login(@RequestBody List<String> userdetails) throws Exception {
      String token =  loginService.authenticateClient(userdetails);
        return token;
    }
}
