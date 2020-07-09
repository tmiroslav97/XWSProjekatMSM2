package agent.app.service.intf;


import agent.app.model.Invoice;

import java.util.List;

public interface InvoiceService {

    Invoice findById(Long id);

    List<Invoice> findAll();

    Invoice save(Invoice invoice);

}
