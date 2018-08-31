package com.spidermanteam.spiderpuppies.web.clientcontrollers;

import com.spidermanteam.spiderpuppies.models.Invoice;
import com.spidermanteam.spiderpuppies.models.Subscriber;
import com.spidermanteam.spiderpuppies.models.reporting.InvoiceView;
import com.spidermanteam.spiderpuppies.models.reporting.PaymentReport;
import com.spidermanteam.spiderpuppies.models.reporting.SubscriberShortView;
import com.spidermanteam.spiderpuppies.models.reporting.SubscriberView;
import com.spidermanteam.spiderpuppies.services.base.ClientAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/aclient")
public class ClientController {

    private ClientAccessService clientAccessService;

    @Autowired
    public ClientController(ClientAccessService clientAccessService) {
        this.clientAccessService = clientAccessService;
    }

    @PutMapping("/payInvoiceById/{id}/{clientId}")
    public PaymentReport payInvoiceById(@PathVariable("id") int id, @PathVariable("clientId") int clientId) {
        return clientAccessService.payInvoiceByIdAndClientId(id, clientId);
    }

    @PutMapping("/payInvoicesByPhone")
    public List<PaymentReport> payInvoiceByPhone(@RequestBody HashMap<String, String> input) {
        String phone = input.get("phone");
        int clientId = 2;//HAVE TO CHECK HOW TO GET THE CLIENT ID
        return clientAccessService.payInvoicesByPhoneAndClientId(phone, clientId);
    }


    @PutMapping("/payInvoiceByIdList")
    public List<PaymentReport> payInvoicesByIdList(@RequestBody List<Integer> idList) {
        int clientId = 9;//HAVE TO CHECK HOW TO GET THE CLIENT ID
        return clientAccessService.payInvoicesByIdListAndClientId(idList, clientId);
    }

    @PutMapping("/payInvoicesByPhoneList")
    public List<PaymentReport> payInvoicesByPhoneList(@RequestBody List<String> phonesList) {
        int clientId = 9;//HAVE TO CHECK HOW TO GET THE CLIENT ID
        return clientAccessService.payInvoicesByPhoneListAndClientId(phonesList, clientId);
    }

    @GetMapping("/invoice/findAllPendingByClientId/{id}")
    List<InvoiceView> findAllPendingInvoicesByClientId(@PathVariable("id") int id) {
        List<Invoice> invoiceList = clientAccessService.listAllPendingInvoicesByClientId(id);
        List<InvoiceView> invoiceViewList = new ArrayList<>();
        for (Invoice inv : invoiceList
        ) {
            InvoiceView invoiceView = new InvoiceView(inv);
            invoiceViewList.add(invoiceView);


        }
        return invoiceViewList;
    }

    @GetMapping("/invoice/findAllByClientId/{id}")
    List<InvoiceView> findAllInvoicesByClientId(@PathVariable int id) {
        List<Invoice> invoiceList = clientAccessService.listAllInvoicesByClientId(id);
        List<InvoiceView> invoiceViewList = new ArrayList<>();
        for (Invoice inv : invoiceList
        ) {
            InvoiceView invoiceView = new InvoiceView(inv);
            invoiceViewList.add(invoiceView);


        }
        return invoiceViewList;
    }

    @GetMapping("/subscribers/getTenBest/{id}")
    List<SubscriberShortView> getTenBestSubscribersByTurnoverAndClientId(@PathVariable("id") int id) {
        List<Subscriber> subscriberList = clientAccessService.getTenBestSubscribersByTurnoverAndClientId(id);
        List<SubscriberShortView> bestTenSubscribersList = new ArrayList<>();
        for (Subscriber sub : subscriberList
        ) {
            String name = sub.getFirstName()+" "+sub.getLastName();
            BigDecimal avgPerMonth = clientAccessService.getAvgPriceBySubscriberId(sub.getId());
            SubscriberShortView subReport = new SubscriberShortView(sub.getPhone(),name, avgPerMonth,sub.getAllTimeTurnover());
            bestTenSubscribersList.add(subReport);
        }
        return bestTenSubscribersList;
    }

}



