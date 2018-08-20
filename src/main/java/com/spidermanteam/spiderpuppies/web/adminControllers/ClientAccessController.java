package com.spidermanteam.spiderpuppies.web.adminControllers;

import com.spidermanteam.spiderpuppies.services.base.ClientAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aclient")
public class ClientAccessController {

    private ClientAccessService clientAccessService;

    @Autowired
    public ClientAccessController(ClientAccessService clientAccessService) {
        this.clientAccessService = clientAccessService;
    }

    @PutMapping("/payInvoiceById/{id}")
    public void payInvoiceById(@PathVariable("id") int id){
        clientAccessService.payInvoiceById(id);
    }

    @PutMapping("/payInvoiceByPhone/{phone}")
   public void payInvoiceByPhone(@PathVariable("phone") String phone){
        clientAccessService.payInvoiceByPhone(phone);
    }

    @PutMapping("/payInvoiceByIdList")
    void payInvoicesByIdList(@RequestBody List<Integer> idList){
        clientAccessService.payInvoicesByIdList(idList);
    }

    @PutMapping("/payInvoicesByPhoneList")
    void payInvoicesByPhoneList(@RequestBody List<String> phonesList){
        clientAccessService.payInvoicesByPhoneList(phonesList);
    }


}
