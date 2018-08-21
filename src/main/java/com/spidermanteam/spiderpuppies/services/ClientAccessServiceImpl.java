package com.spidermanteam.spiderpuppies.services;

import com.spidermanteam.spiderpuppies.data.base.ClientAccessRepository;
import com.spidermanteam.spiderpuppies.models.Invoice;
import com.spidermanteam.spiderpuppies.models.Subscriber;
import com.spidermanteam.spiderpuppies.services.base.ClientAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Service
public class ClientAccessServiceImpl implements ClientAccessService {

    private ClientAccessRepository clientAccessRepository;

    @Autowired
    public ClientAccessServiceImpl(ClientAccessRepository clientAccessRepository) {
        this.clientAccessRepository = clientAccessRepository;
    }

    @Override
    public void payInvoiceById(int invoiceId) {
        clientAccessRepository.payInvoiceById(invoiceId);
    }

    @Override
    public void payInvoicesByPhone(HashMap<String,String> input) {
        String phone = input.get("phone");
        clientAccessRepository.payInvoicesByPhone(phone);
    }

    @Override
    public void payInvoicesByIdList(List<Integer> invoiceIdList) {
        clientAccessRepository.payInvoicesByIdList(invoiceIdList);
    }

    @Override
    public void payInvoicesByPhoneList(List<String> phonesList) {
        clientAccessRepository.payInvoicesByPhoneList(phonesList);

    }

    @Override
    public void payAllUnpaidInvoiceByClient(int clientId) {

    }

    @Override
    public List<Invoice> listAllUnpaidInvoiceById(int clientId) {
        return null;
    }

    @Override
    public List<Invoice> listAllPaidInvoicesById(int clientId) {
        return null;
    }

    @Override
    public Subscriber getSubscriberByPhone(String phone) {
        return null;
    }

    @Override
    public BigDecimal getMaxPriceBySubscriberPhone(String phone) {
        return null;
    }

    @Override
    public BigDecimal getAvgPriceBySubscriberPhone(String phone) {
        return null;
    }

    @Override
    public List<Invoice> getLastTenPaidInvoiceBySubscriberPhone(String phone) {
        return null;
    }

    @Override
    public BigDecimal getMaxPriceFromAllSubscribers(int clientId) {
        return null;
    }

    @Override
    public BigDecimal getAvgPriceFromSubscribers(int clientId) {
        return null;
    }

    @Override
    public List<Invoice> getLastTenPaidInvoiceByClient(int clientId) {
        return null;
    }
}
