package com.spidermanteam.spiderpuppies.data.base;

import com.spidermanteam.spiderpuppies.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  {

    User getUserByUserNameAndPassWord(String username, String password);

    User getUserByUserName(String username);

    User findUserById(Long id);
}
