package com.spidermanteam.spiderpuppies.services.base;

import com.spidermanteam.spiderpuppies.models.User;

public interface LoginService {

    User loginCheckUserByUserNameAndPassword(String username, String password);

    String generatorToken(User user);

    String getRoleByUsername(String username);

}
