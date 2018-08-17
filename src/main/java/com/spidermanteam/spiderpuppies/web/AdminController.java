package com.spidermanteam.spiderpuppies.web;

import com.spidermanteam.spiderpuppies.models.Admin;
import com.spidermanteam.spiderpuppies.models.Client;
import com.spidermanteam.spiderpuppies.services.base.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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


}
