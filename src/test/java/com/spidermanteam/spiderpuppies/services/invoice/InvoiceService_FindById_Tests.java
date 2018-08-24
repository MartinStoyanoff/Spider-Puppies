package com.spidermanteam.spiderpuppies.services.invoice;

import com.spidermanteam.spiderpuppies.SpiderPuppiesApplicationTests;
import com.spidermanteam.spiderpuppies.data.base.InvoiceRepository;
import com.spidermanteam.spiderpuppies.data.base.SubscriberRepository;
import com.spidermanteam.spiderpuppies.models.*;
import com.spidermanteam.spiderpuppies.services.InvoiceServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.rmi.NoSuchObjectException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceService_FindById_Tests {

    @Mock
    InvoiceRepository mockRepository;
    @Mock
    SubscriberRepository subscriberRepository;

    private InvoiceServiceImpl invoiceService;
    private Invoice invoice;

    @Before
    public void beforeTest() {
        Client client = new Client(new User("UserName", "Password", (byte) 1), "ClientFullName", "EIK");
        TelecomService telecomService = new TelecomService("Type", "SubsPlan", BigDecimal.valueOf(0.00), new ArrayList<>());
        LocalDate date = LocalDate.now();
        Subscriber subscriber = new Subscriber("SubscPhone", "FirstName", "LastName", "PIN", "Address", new ArrayList<>(), new ArrayList<>(), date, date, client, BigDecimal.valueOf(0.00));
        invoice = new Invoice(subscriber, telecomService, "BGN");
        invoice.setId(1);

        when(mockRepository.findById(1))
                .thenReturn(invoice);

        invoiceService = new InvoiceServiceImpl(mockRepository, subscriberRepository);
    }

    @Test
    public void getById_whenInvoiceIsPresent_ShouldReturnInvoice() throws Exception {
        //Arrange
        Invoice actualInvoice = invoiceService.findInvoiceById(1);
        //Act
        verify(mockRepository)
                .findById(1);
        //Assert
        Assert.assertEquals(invoice.getId(), actualInvoice.getId());
    }

}



