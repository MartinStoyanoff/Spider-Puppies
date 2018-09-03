package com.spidermanteam.spiderpuppies.services;

import com.spidermanteam.spiderpuppies.data.base.AuthoritiesRepository;
import com.spidermanteam.spiderpuppies.data.base.UserRepository;
import com.spidermanteam.spiderpuppies.models.Authorities;
import com.spidermanteam.spiderpuppies.models.User;
import com.spidermanteam.spiderpuppies.security.JwtGenerator;
import com.spidermanteam.spiderpuppies.services.base.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {


    private JwtGenerator jwtGenerator;
    private UserRepository userRepository;
    private AuthoritiesRepository authoritiesRepository;

    public LoginServiceImpl(JwtGenerator jwtGenerator, UserRepository userRepository, AuthoritiesRepository authoritiesRepository) {
        this.jwtGenerator = jwtGenerator;
        this.userRepository = userRepository;
        this.authoritiesRepository = authoritiesRepository;
    }

    @Override
    public User loginCheckUserByUserNameAndPassword(String username,String password) {
       return userRepository.getUserByUserNameAndPassWord(username,password);
    }

    @Override
    public String generatorToken(User user) {
        Authorities authorities = authoritiesRepository.getAuthoritiesByUserName(user.getUsername());
        String role = authorities.getAuthority();
        return jwtGenerator.generate(user,role);
    }

    @Override
    public String getRoleByUsername(String username) {
      Authorities  authorities = authoritiesRepository.getAuthoritiesByUserName(username);
      String role = authorities.getAuthority();
        return role;
    }
}
