package com.spidermanteam.spiderpuppies.services.client;

import com.spidermanteam.spiderpuppies.data.base.ClientRepository;
import com.spidermanteam.spiderpuppies.models.Client;
import com.spidermanteam.spiderpuppies.services.ClientServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientService_tests {

  @Mock
  ClientRepository clientRepository;

  private ClientServiceImpl clientService;

  @Before
  public void beforeTest() {
    clientService = new ClientServiceImpl(clientRepository);
  }

  @Test
  public void addClient_whenClientIsPresented_ShouldInvokeCreateRepositoryMethod() {
    Client client = new Client();

    doNothing().when(clientRepository).create(isA(Client.class));
    clientService.addClient(client);

    verify(clientRepository, times(1)).create(client);
  }

  @Test
  public void findClientById_whenClientIdIsPresented_ShouldReturnClient() {

    Client client = new Client();
    client.setId(1);

    when(clientRepository.findById(1)).thenReturn(client);
    Client actualClient = clientService.findClientById(1);

    Assert.assertEquals(client.getId(), actualClient.getId());
  }

  @Test
  public void listAllClients_whenClientsListIsPresented_ShouldReturnListOfClients() {

    List<Client> clientList = new ArrayList<>();
    Client firstClient = new Client();
    Client secondClient = new Client();
    Client thirdClient = new Client();

    clientList.add(firstClient);
    clientList.add(secondClient);
    clientList.add(thirdClient);

    when(clientRepository.listAll()).thenReturn(clientList);
    List<Client> actualClientList = clientService.listAllClients();

    Assert.assertEquals(clientList.size(), actualClientList.size());
  }

  @Test
  public void updateClient_whenClientIsPresented_ShouldInvokeUpdateRepositoryMethod() {

    Client client = new Client();

    doNothing().when(clientRepository).update(isA(Client.class));
    clientService.updateClient(client);
    verify(clientRepository, times(1)).update(client);
  }

  @Test
  public void deletSubscriber_whenSubscriberIsPresented_ShouldInvokeDeleteRepositoryMethod() {

    doNothing().when(clientRepository).delete(isA(Integer.class));
    clientService.deleteClient(1);

    verify(clientRepository, times(1)).delete(1);
  }
}
