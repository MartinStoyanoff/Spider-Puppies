package com.spidermanteam.spiderpuppies.services;

import com.spidermanteam.spiderpuppies.data.base.AdminRepository;
import com.spidermanteam.spiderpuppies.models.Admin;
import com.spidermanteam.spiderpuppies.services.base.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, BCryptPasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
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
        String newPassword = passwordEncoder.encode(passwordUpdateInfo.get(2));


        Admin admin = adminRepository.findById(adminId);

        String adminPass = admin.getUser().getPassword();

        if(passwordEncoder.matches(oldPassword,adminPass)){
            admin.getUser().setPassword(newPassword);
            admin.setFirstLogin(0);
            adminRepository.updateAdminPassword(admin);
        }

    }

    @Override
    public Admin findAdminByUserUsername(String username) {
        return adminRepository.findAdminByUserUsername(username);
    }
}
