package com.spidermanteam.spiderpuppies.services.admin;

import com.spidermanteam.spiderpuppies.data.base.AdminRepository;
import com.spidermanteam.spiderpuppies.models.Admin;
import com.spidermanteam.spiderpuppies.services.AdminServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class AdminService_tests {

  @Mock
  AdminRepository adminRepository;

  private AdminServiceImpl adminService;
  private BCryptPasswordEncoder passwordEncoder;

  @Before
  public void beforeTest() {
    adminService = new AdminServiceImpl(adminRepository, passwordEncoder);
  }

  @Test
  public void addAdmin_whenSubscriberIsPresented_ShouldInvokeCreateRepositoryMethod() {
    Admin admin = new Admin();

    doNothing().when(adminRepository).create(isA(Admin.class));
    adminService.addAdmin(admin);

    verify(adminRepository, times(1)).create(admin);
  }

  @Test
  public void findAdminById_whenAdminIdIsPresented_ShouldReturnAdmin() {


    Admin admin = new Admin();
    admin.setId(1);

    when(adminRepository.findById(1)).thenReturn(admin);
    Admin actualAdmin = adminService.findAdminById(1);

    Assert.assertEquals(admin.getId(), actualAdmin.getId());
  }

  @Test
  public void listAllAdmins_whenAdminsListIsPresented_ShouldReturnListOfAdmins() {

    List<Admin> adminList = new ArrayList<>();
    Admin firstAdmin = new Admin();
    Admin secondAdmin = new Admin();
    Admin thirdAdmin = new Admin();

    adminList.add(firstAdmin);
    adminList.add(secondAdmin);
    adminList.add(thirdAdmin);

    when(adminRepository.listAll()).thenReturn(adminList);
    List<Admin> actualAdminList = adminService.listAllAdmins();

    Assert.assertEquals(adminList.size(), actualAdminList.size());
  }

  @Test
  public void updateAdmin_whenAdminIsPresented_ShouldInvokeUpdateRepositoryMethod() {

    Admin admin = new Admin();

    doNothing().when(adminRepository).update(isA(Admin.class));
    adminService.updateAdmin(admin);
    verify(adminRepository, times(1)).update(admin);
  }

  @Test
  public void deletSubscriber_whenSubscriberIsPresented_ShouldInvokeDeleteRepositoryMethod() {

    doNothing().when(adminRepository).delete(isA(Integer.class));
    adminService.deleteAdmin(1);

    verify(adminRepository, times(1)).delete(1);
  }

  @Test
  public void changeAdminPassword_whenListIsPassed_shouldInvokeUpdateRepositoryMethod() {
    Admin admin = new Admin();

    doNothing().when(adminRepository).update(isA(Admin.class));
    adminService.updateAdmin(admin);
    verify(adminRepository, times(1)).update(admin);
  }
}
