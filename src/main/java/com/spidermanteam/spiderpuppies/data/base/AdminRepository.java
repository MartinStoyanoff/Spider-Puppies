package com.spidermanteam.spiderpuppies.data.base;

import com.spidermanteam.spiderpuppies.models.Admin;

public interface AdminRepository extends GenericRepository<Admin> {
  void updateAdminPassword(Admin admin);

  Admin findAdminByUserUsername(String username);
}
