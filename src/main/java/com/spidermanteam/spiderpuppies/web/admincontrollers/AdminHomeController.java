package com.spidermanteam.spiderpuppies.web.admincontrollers;

import com.spidermanteam.spiderpuppies.models.Invoice;
import com.spidermanteam.spiderpuppies.models.Subscriber;
import com.spidermanteam.spiderpuppies.models.reporting.InvoiceView;
import com.spidermanteam.spiderpuppies.models.reporting.SubscriberShortView;
import com.spidermanteam.spiderpuppies.services.base.ClientAccessService;
import com.spidermanteam.spiderpuppies.services.base.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/admin/home")
public class AdminHomeController {

    private InvoiceService invoiceService;
    private ClientAccessService clientAccessService;

    @Autowired
    public AdminHomeController(InvoiceService invoiceService, ClientAccessService clientAccessService) {
        this.invoiceService = invoiceService;
        this.clientAccessService = clientAccessService;
    }

    @GetMapping("/lastTenInvoices")
    List<InvoiceView> invoiceViewList() {
        List<Invoice> invoiceList = invoiceService.findLastTenPayments();
        List<InvoiceView> invoiceViewList = new ArrayList<>();
        for (Invoice inv : invoiceList) {
            invoiceViewList.add(new InvoiceView(inv));
        }
        return invoiceViewList;
    }

    @GetMapping("/topTenSubscribers")
    List<SubscriberShortView> getTenBestSubscribersByTurnover() {
        List<Subscriber> subscriberList = clientAccessService.getTenBestSubscribersByTurnover();
        List<SubscriberShortView> bestTenSubscribersList = new ArrayList<>();
        for (Subscriber sub : subscriberList) {
            String name = sub.getFirstName() + " " + sub.getLastName();
            BigDecimal avgPerMonth = clientAccessService.getAvgPriceBySubscriberId(sub.getId());
            SubscriberShortView subReport = new SubscriberShortView(sub.getPhone(), name, avgPerMonth, sub.getAllTimeTurnover());
            bestTenSubscribersList.add(subReport);
        }
        return bestTenSubscribersList;
    }
}
