package com.spidermanteam.spiderpuppies.services.base;

import com.spidermanteam.spiderpuppies.models.Admin;

import java.util.List;

public interface AdminService {

    void addAdmin(Admin admin);

    Admin findAdminById(int id);

    List listAllAdmins();

    void updateAdmin(Admin admin);

    void deleteAdmin(int id);

    void changeAdminPassword(List<String> passwordUpdateInfo);

}
