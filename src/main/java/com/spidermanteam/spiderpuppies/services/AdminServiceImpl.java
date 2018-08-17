package com.spidermanteam.spiderpuppies.services;

import com.spidermanteam.spiderpuppies.data.base.AdminRepository;
import com.spidermanteam.spiderpuppies.models.Admin;
import com.spidermanteam.spiderpuppies.services.base.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl (AdminRepository adminRepository){
        this.adminRepository=adminRepository;
    }

    @Override
    public void addAdmin(Admin admin) {
        adminRepository.addAdmin(admin);
    }

    @Override
    public List listAllAdmins() {
        return adminRepository.listAllAdmins();
    }
}
