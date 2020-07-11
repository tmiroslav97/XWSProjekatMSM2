package agent.app.service.impl;

import agent.app.exception.NotFoundException;
import agent.app.model.Invoice;
import agent.app.repository.InvoiceRepository;
import agent.app.service.intf.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    protected InvoiceRepository invoiceRepository;

    @Override
    public Invoice findById(Long id) {
        return invoiceRepository.findById(id).orElseThrow(() -> new NotFoundException("Racun ne postoji"));
    }

    @Override
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();    }

    @Override
    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }
}
