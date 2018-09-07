package com.spidermanteam.spiderpuppies.services.base;

import java.util.List;

public interface LoginService {

  String authenticateClient(List<String> singInInfo);
}
