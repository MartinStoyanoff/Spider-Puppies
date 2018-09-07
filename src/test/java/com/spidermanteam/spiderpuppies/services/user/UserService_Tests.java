package com.spidermanteam.spiderpuppies.services.user;

import com.spidermanteam.spiderpuppies.data.base.AuthoritiesRepository;
import com.spidermanteam.spiderpuppies.data.base.UserRepository;
import com.spidermanteam.spiderpuppies.models.Authorities;
import com.spidermanteam.spiderpuppies.models.User;
import com.spidermanteam.spiderpuppies.services.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserService_Tests {

  @Mock
  private UserRepository userRepository;
  @Mock
  private AuthoritiesRepository authoritiesRepository;

  private UserServiceImpl userService;

  @Before
  public void beforeTest() {
    userService = new UserServiceImpl(userRepository, authoritiesRepository);
  }

  @Test
  public void findUserById_whenUserIsPresented_ShouldReturnUser() {
    //Arrange
    User user = new User();
    user.setId(1L);

    //Act
    when(userRepository.findUserById(1L)).thenReturn(user);
    User actualTelecomService = userService.findById(1L);

    //Assert
    Assert.assertEquals(user.getId(), actualTelecomService.getId());
  }

  @Test
  public void findUserRoleByUserId_whenUserIsPresented_ShouldReturnUserRole() {
    //Arrange
    User user = new User();
    user.setId(1L);
    Authorities authorities = new Authorities();
    authorities.setAuthority("ROLE_ADMIN");

    //Act
    when(userRepository.findUserById(1L)).thenReturn(user);
    when(authoritiesRepository.getAuthoritiesByUserName(user.getUsername())).thenReturn(authorities);
    String actualRole = userService.findUserRoleByUserId(1L);

    //Assert
    Assert.assertEquals("ROLE_ADMIN", actualRole);
  }
}