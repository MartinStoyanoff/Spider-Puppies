package com.spidermanteam.spiderpuppies.services.base;

import com.spidermanteam.spiderpuppies.models.User;

public interface UserService {

  User findById(Long id);

  String findUserRoleByUserId(Long id);
}
