package com.spidermanteam.spiderpuppies.services.base;

import com.spidermanteam.spiderpuppies.models.Admin;

import java.util.List;

public interface AdminService {
    void addAdmin(Admin admin);

    List listAllAdmins();
}
