package com.spidermanteam.spiderpuppies.services;

import com.spidermanteam.spiderpuppies.data.base.AuthoritiesRepository;
import com.spidermanteam.spiderpuppies.data.base.UserRepository;
import com.spidermanteam.spiderpuppies.models.Authorities;
import com.spidermanteam.spiderpuppies.models.User;
import com.spidermanteam.spiderpuppies.security.JwtTokenProvider;
import com.spidermanteam.spiderpuppies.services.base.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {


    private AuthenticationManager authenticationManager;
    private JwtTokenProvider tokenProvider;


    @Autowired
    public LoginServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public String authenticateClient(List<String> singInInfo) {


        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                singInInfo.get(0), singInInfo.get(1));


        Authentication authentication = authenticationManager.authenticate(authRequest);


        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return jwt;

    }
}