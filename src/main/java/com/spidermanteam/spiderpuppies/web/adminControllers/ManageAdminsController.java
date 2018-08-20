package com.spidermanteam.spiderpuppies.web.adminControllers;

import com.spidermanteam.spiderpuppies.models.Admin;
import com.spidermanteam.spiderpuppies.models.Client;
import com.spidermanteam.spiderpuppies.services.base.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class ManageAdminsController {

    private AdminService adminService;

    @Autowired
    public ManageAdminsController(AdminService adminService){
        this.adminService=adminService;
    }

    @PostMapping("/addAdmin")
    void addAdmin(@RequestBody Admin admin){
        adminService.addAdmin(admin);
    }

    @GetMapping("/admin/findAdminById/{id}")
    public Admin findAdminById(@PathVariable("id") String idString) {
        int id = Integer.parseInt(idString);
        return adminService.findAdminById(id);
    }

    @GetMapping("/admin/listAllAdmins")
    List listAllAdmins(){
        return adminService.listAllAdmins();
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
