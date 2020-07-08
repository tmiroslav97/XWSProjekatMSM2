package services.app.carrequestservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.app.carrequestservice.model.Invoice;
import services.app.carrequestservice.repository.InvoiceRepository;
import services.app.carrequestservice.service.intf.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    protected InvoiceRepository invoiceRepository;

    @Override
    public Invoice findById(Long id) {
        return null;
    }

    @Override
    public Invoice save(Invoice invoice) {
        return null;
    }
}
