package com.spidermanteam.spiderpuppies.data.base;

import com.spidermanteam.spiderpuppies.models.Authorities;

public interface AuthoritiesRepository {

  Authorities getAuthoritiesByUserName(String username);
}
