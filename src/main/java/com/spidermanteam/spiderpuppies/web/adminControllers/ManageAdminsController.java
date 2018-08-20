package com.spidermanteam.spiderpuppies.web.adminControllers;

import com.spidermanteam.spiderpuppies.models.Admin;
import com.spidermanteam.spiderpuppies.models.Client;
import com.spidermanteam.spiderpuppies.services.base.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/manageAdmins")
public class ManageAdminsController {

    private AdminService adminService;

    @Autowired
    public ManageAdminsController(AdminService adminService){
        this.adminService=adminService;
    }

    @PostMapping("/add")
    void addAdmin(@RequestBody Admin admin){
        adminService.addAdmin(admin);
    }

    @GetMapping("/findById/{id}")
    public Admin findAdminById(@PathVariable int id) {
        return adminService.findAdminById(id);
    }

    @GetMapping("/listAll")
    List listAllAdmins(){
        return adminService.listAllAdmins();
    }

    @PutMapping("/update")
    void updateAdmin(@RequestBody Admin admin){
        adminService.updateAdmin(admin);
        //TODO:Update password to be solved - through cascade or update in both tables
    }

    @DeleteMapping("/delete/{id}")
    void deleteAdmin(@PathVariable int id){
        adminService.deleteAdmin(id);
        //TODO:Delete user to be solved - through cascade or update in both tables

    }




}
