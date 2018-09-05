package com.spidermanteam.spiderpuppies.web.admincontrollers;

import com.spidermanteam.spiderpuppies.services.base.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(value = "/ng/login")
    public String login(@RequestBody List<String> userdetails) throws Exception {
        System.out.println(userdetails.get(0));
        System.out.println(userdetails.get(1));
        String token =  loginService.authenticateClient(userdetails);

        System.out.println(token);
      return token;
    }
}
