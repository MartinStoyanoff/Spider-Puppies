package com.spidermanteam.spiderpuppies.data.base;


import com.spidermanteam.spiderpuppies.models.Client;

public interface ClientRepository extends GenericRepository<Client> {

  Client findClientByUserUserName(String username);

}
