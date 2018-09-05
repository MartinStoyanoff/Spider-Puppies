package com.spidermanteam.spiderpuppies.data.base;

import com.spidermanteam.spiderpuppies.models.User;

public interface UserRepository  {

    User getUserByUserNameAndPassWord(String username, String password);

    User getUserByUserName(String username);

    User findUserById(Long id);
}
