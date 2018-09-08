package com.spidermanteam.spiderpuppies.services.telecomservice;

import com.spidermanteam.spiderpuppies.data.base.GenericRepository;
import com.spidermanteam.spiderpuppies.models.TelecomService;
import com.spidermanteam.spiderpuppies.services.TelecomServiceServiceImpl;
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
public class TelecomServiceAllTests {

  @Mock
  GenericRepository<TelecomService> telecomServiceGenericRepository;

  private TelecomServiceServiceImpl telecomServiceService;

  @Before
  public void beforeTest() {
    telecomServiceService = new TelecomServiceServiceImpl(telecomServiceGenericRepository);
  }

  @Test
  public void addTelecomService_whenTelecomServiceIsPresented_ShouldInvokeCreateRepositoryMethod() {
    //Arrange
    TelecomService telecomService = new TelecomService();

    //Act
    doNothing().when(telecomServiceGenericRepository).create(isA(TelecomService.class));
    telecomServiceService.addTelecomService(telecomService);

    //Assert
    verify(telecomServiceGenericRepository, times(1)).create(telecomService);
  }

  @Test
  public void findTelecomServiceById_whenTelecomServiceIsPresented_ShouldReturnTelecomService() {
    //Arrange
    TelecomService telecomService = new TelecomService();
    telecomService.setId(1);

    //Act
    when(telecomServiceGenericRepository.findById(1)).thenReturn(telecomService);
    TelecomService actualTelecomService = telecomServiceService.findTelecomServiceById(1);

    //Assert
    Assert.assertEquals(telecomService.getId(), actualTelecomService.getId());
  }

  @Test
  public void listAllTelecomServices_whenTelecomServiceListIsPresent_ShouldReturnTelecomServiceList() {

    //Arrange
    TelecomService firstTelecomService = new TelecomService();
    TelecomService secondTelecomService = new TelecomService();
    TelecomService thirdTelecomService = new TelecomService();

    List<TelecomService> telecomServiceList = new ArrayList<>();
    telecomServiceList.add(firstTelecomService);
    telecomServiceList.add(secondTelecomService);
    telecomServiceList.add(thirdTelecomService);

    //Act
    when(telecomServiceGenericRepository.listAll()).thenReturn(telecomServiceList);
    List<TelecomService> actualTelecomServiceList = telecomServiceService.listAllTelecomServices();

    //Assert
    Assert.assertEquals(telecomServiceList, actualTelecomServiceList);
  }

  @Test
  public void updateTelecomService_whenTelecomServiceIsPresented_ShouldInvokeDeleteRepositoryMethod() {

    //Act
    TelecomService telecomService = new TelecomService();

    //Arrange
    doNothing().when(telecomServiceGenericRepository).update(isA(TelecomService.class));
    telecomServiceService.updateTelecomService(telecomService);

    //Assert
    verify(telecomServiceGenericRepository, times(1)).update(telecomService);
  }

  @Test
  public void deleteTelecomService_whenTelecomServiceIsPresented_ShouldInvokeDeleteRepositoryMethod() {

    doNothing().when(telecomServiceGenericRepository).delete(isA(Integer.class));
    telecomServiceService.deleteTelecomService(1);

    //Assert
    verify(telecomServiceGenericRepository, times(1)).delete(1);
  }
}



