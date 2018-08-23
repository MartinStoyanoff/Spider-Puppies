package com.spidermanteam.spiderpuppies.web.clientcontrollers;

import com.spidermanteam.spiderpuppies.models.Invoice;
import com.spidermanteam.spiderpuppies.models.reporting.InvoiceReport;
import com.spidermanteam.spiderpuppies.objectmapping.MappingHelper;
import com.spidermanteam.spiderpuppies.services.base.ClientAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/payInvoiceById/{id}")
    public void payInvoiceById(@PathVariable("id") int id) {
        clientAccessService.payInvoiceById(id);
    }

    @PutMapping("/payInvoicesByPhone")
    public void payInvoiceByPhone(@RequestBody HashMap<String, String> input) {
        String phone = input.get("phone");
        clientAccessService.payInvoicesByPhone(phone);
    }


    @PutMapping("/payInvoiceByIdList")
    void payInvoicesByIdList(@RequestBody List<Integer> idList) {
        clientAccessService.payInvoicesByIdList(idList);
    }

    @PutMapping("/payInvoicesByPhoneList")
    void payInvoicesByPhoneList(@RequestBody List<String> phonesList) {
        clientAccessService.payInvoicesByPhoneList(phonesList);
    }

    @GetMapping("/invoice/findAllPendingByClientId/{id}")
    List<InvoiceReport> findAllPendingInvoicesByClientId(@PathVariable("id") int id) {
        List<Invoice> invoiceList = clientAccessService.listAllPendingInvoicesByClientId(id);
        List<InvoiceReport> invoiceReportList = new ArrayList<>();
        for (Invoice inv : invoiceList
        ) {
            InvoiceReport invoiceReport = MappingHelper.mapInvoiceToInvoiceReport(inv);
            invoiceReportList.add(invoiceReport);


        }
        return invoiceReportList;
    }

    @GetMapping("/invoice/findAllByClientId/{id}")
    List<InvoiceReport> findAllInvoicesByClientId(@PathVariable("id") int id) {
        List<Invoice> invoiceList = clientAccessService.listAllInvoicesByClientId(id);
        List<InvoiceReport> invoiceReportList = new ArrayList<>();
        for (Invoice inv : invoiceList
        ) {
            InvoiceReport invoiceReport = MappingHelper.mapInvoiceToInvoiceReport(inv);
            invoiceReportList.add(invoiceReport);


        }
        return invoiceReportList;
    }

}



