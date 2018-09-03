package com.spidermanteam.spiderpuppies.services;

import com.spidermanteam.spiderpuppies.data.base.AdminRepository;
import com.spidermanteam.spiderpuppies.data.base.GenericRepository;
import com.spidermanteam.spiderpuppies.models.Admin;
import com.spidermanteam.spiderpuppies.services.base.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void addAdmin(Admin admin) {
        adminRepository.create(admin);
    }

    @Override
    public Admin findAdminById(int id) {
        return adminRepository.findById(id);
    }

    @Override
    public List listAllAdmins() {
        return adminRepository.listAll();
    }

    @Override
    public void updateAdmin(Admin admin) {
        adminRepository.update(admin);
    }

    @Override
    public void deleteAdmin(int id) {
        adminRepository.delete(id);
    }

    @Override
    public void changeAdminPassword(List<String> passwordUpdateInfo) {
        int adminId = Integer.parseInt(passwordUpdateInfo.get(0));
        String oldPassword = passwordUpdateInfo.get(1);
        String newPassword = passwordUpdateInfo.get(2);
        System.out.println(newPassword);

        Admin admin = adminRepository.findById(adminId);

        String adminPass = admin.getUser().getPassword();

        if(adminPass.equals(oldPassword)){
            admin.getUser().setPassword(newPassword);
            adminRepository.updateAdminPassword(admin);
            System.out.println(admin.getUser().getPassword());
        }

    }
}
