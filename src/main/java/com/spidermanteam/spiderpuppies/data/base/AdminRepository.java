package com.spidermanteam.spiderpuppies.data.base;

import com.spidermanteam.spiderpuppies.models.Admin;

import java.util.List;

public interface AdminRepository {

    void addAdmin(Admin admin);

    List listAllAdmins();

    //TODO other methods to be created
}
