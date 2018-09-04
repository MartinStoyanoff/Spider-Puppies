package com.spidermanteam.spiderpuppies.services.admin;

import com.spidermanteam.spiderpuppies.data.base.AdminRepository;
import com.spidermanteam.spiderpuppies.models.Admin;
import com.spidermanteam.spiderpuppies.models.User;
import com.spidermanteam.spiderpuppies.services.AdminServiceImpl;
import com.spidermanteam.spiderpuppies.services.base.AdminService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class AdminService_tests {

    @Mock
    AdminRepository adminRepository;

    private AdminServiceImpl adminService;
    @Before
    public void beforeTests(){
        adminService = new AdminServiceImpl(adminRepository);

    }

    @Test
    public void changeAdminPassword_whenPasswordUpdateInfoIsPresented_ShouldChangeAdminPassword(){
        List<String> passwords = new ArrayList<>();

        String id = "1";
        String oldPassword = "oldPasswod";
        String newPassword = "newPasswod";
        passwords.add(id);
        passwords.add(oldPassword);
        passwords.add(newPassword);


        User user = new User();

        Admin admin = new Admin();
        admin.setUser(user);
        admin.setId(1);
        admin.getUser().setPassword(oldPassword);

        when(adminRepository.findById(1))
                .thenReturn(admin);

        adminService.changeAdminPassword(passwords);

        verify(adminRepository, times(1)).update(admin);

    }
}
