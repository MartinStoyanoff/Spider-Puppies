//package com.spidermanteam.spiderpuppies.services.login;
//
//import com.spidermanteam.spiderpuppies.security.JwtTokenProvider;
//import com.spidermanteam.spiderpuppies.services.LoginServiceImpl;
//import com.spidermanteam.spiderpuppies.services.base.LoginService;
//import org.junit.Before;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import java.util.List;
//
//public class LoginService_Tests {
//
//    @Mock
//    private AuthenticationManager authenticationManager;
//    @Mock
//    private JwtTokenProvider tokenProvider;
//    @MockBean
//    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;
//
//
//    private LoginService loginService;
//
//    @Before
//    public void beforeTest() {
//        loginService = new LoginServiceImpl(authenticationManager, tokenProvider);
//    }
//
//}