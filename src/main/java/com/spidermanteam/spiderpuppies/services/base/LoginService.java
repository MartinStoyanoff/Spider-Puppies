package com.spidermanteam.spiderpuppies.services.base;

import com.spidermanteam.spiderpuppies.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LoginService {

    public String authenticateClient(List<String> singInInfo);

}
