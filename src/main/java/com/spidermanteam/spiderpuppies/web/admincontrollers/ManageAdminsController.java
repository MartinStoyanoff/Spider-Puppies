package com.spidermanteam.spiderpuppies.web.admincontrollers;

import com.spidermanteam.spiderpuppies.models.Admin;
import com.spidermanteam.spiderpuppies.services.base.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/api/admins")
public class ManageAdminsController {

  private AdminService adminService;

  @Autowired
  public ManageAdminsController(AdminService adminService) {
    this.adminService = adminService;
  }

  @PostMapping("/add")
  void addAdmin(@RequestBody Admin admin) {
    adminService.addAdmin(admin);
  }

  @GetMapping("/findById/{id}")
  public Admin findAdminById(@PathVariable int id) {
    return adminService.findAdminById(id);
  }

  @GetMapping("/listAll")
  List listAllAdmins() {
    return adminService.listAllAdmins();
  }

  @PutMapping("/update")
  void updateAdmin(@RequestBody Admin admin) {
    adminService.updateAdmin(admin);
  }

  @DeleteMapping("/delete/{id}")
  void deleteAdmin(@PathVariable int id) {
    adminService.deleteAdmin(id);
  }

  @PutMapping("/changePassword")
  void changeAdminPassword(@RequestBody List<String> passwordUpdateInfo) {
    adminService.changeAdminPassword(passwordUpdateInfo);
  }
  @GetMapping("/findByUsername/{username}")
  void findAdminByUserUsername(@PathVariable String username){
    adminService.findAdminByUserUsername(username);
  }
}
