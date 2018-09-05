package com.spidermanteam.spiderpuppies.security.service;

import com.spidermanteam.spiderpuppies.data.base.AuthoritiesRepository;
import com.spidermanteam.spiderpuppies.data.base.UserRepository;
import com.spidermanteam.spiderpuppies.models.Authorities;
import com.spidermanteam.spiderpuppies.models.User;
import com.spidermanteam.spiderpuppies.security.models.JwtUser;
import com.spidermanteam.spiderpuppies.security.models.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MyJWTUserDetailsService implements UserDetailsService {
    private static final String INVALID_USER = "Invalid user";


    UserRepository userRepository;

    @Autowired
    public MyJWTUserDetailsService(UserRepository userRepository, AuthoritiesRepository authoritiesRepository) {
        this.userRepository = userRepository;
        this.authoritiesRepository = authoritiesRepository;
    }

    AuthoritiesRepository authoritiesRepository;


    public MyJWTUserDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUserName(username);
        Authorities authorities = authoritiesRepository.getAuthoritiesByUserName(username);
        String role = authorities.getAuthority();

        JwtUser jwtUser = new JwtUser(user, role);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");

        }

        return JwtUserDetails.create(jwtUser);

    }

    public UserDetails loadUserById(Long id) {
        User user = userRepository.findUserById(id);
        Authorities authorities = authoritiesRepository.getAuthoritiesByUserName(user.getUsername());
        String role = authorities.getAuthority();
        JwtUser jwtUser = new JwtUser(user, role);

        if (user == null) {
            throw new IllegalArgumentException("User not found by id");
        }

        return JwtUserDetails.create(jwtUser);
    }
}

