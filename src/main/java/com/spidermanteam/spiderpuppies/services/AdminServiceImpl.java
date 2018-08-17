package com.spidermanteam.spiderpuppies.services;

import com.spidermanteam.spiderpuppies.data.base.GenericRepository;
import com.spidermanteam.spiderpuppies.models.Admin;
import com.spidermanteam.spiderpuppies.services.base.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private GenericRepository<Admin> adminRepository;

    @Autowired
    public AdminServiceImpl (GenericRepository<Admin> adminRepository){
        this.adminRepository=adminRepository;
    }

    @Override
    public void addAdmin(Admin admin) {
        adminRepository.create(admin);
    }

    @Override
    public List listAllAdmins() {
        return adminRepository.listAll();
    }

    @Override
    public Admin findAdminById(int id) {
        return adminRepository.findById(id);
    }

    @Override
    public void updateAdmin(Admin admin) {
        adminRepository.update(admin);
    }

    @Override
    public void deleteAdmin(int id) {
        adminRepository.delete(id);
    }
}
