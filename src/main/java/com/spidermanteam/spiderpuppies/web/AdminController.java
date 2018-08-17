package com.spidermanteam.spiderpuppies.web;

import com.spidermanteam.spiderpuppies.models.Admin;
import com.spidermanteam.spiderpuppies.models.Client;
import com.spidermanteam.spiderpuppies.services.base.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController (AdminService adminService){
        this.adminService=adminService;
    }

    @PostMapping("/admin/addAdmin")
    void addAdmin(@RequestBody Admin admin){
        adminService.addAdmin(admin);
    }
    @GetMapping("/admin/listAllAdmins")
    List listAllAdmins(){
        return adminService.listAllAdmins();
    }

    @GetMapping("/admin/findAdminById/{id}")
    public Admin findAdminById(@PathVariable("id") String idString) {
        int id = Integer.parseInt(idString);
        return adminService.findAdminById(id);
    }

    @PutMapping("/admin/updateAdmin")
    void updateAdmin(@RequestBody Admin admin){
        adminService.updateAdmin(admin);
    }

    @DeleteMapping("/admin/deleteAdmin/{id}")
    void deleteAdmin(@PathVariable("id") String idString){
        int id = Integer.parseInt(idString);
        adminService.deleteAdmin(id);
    }




}
