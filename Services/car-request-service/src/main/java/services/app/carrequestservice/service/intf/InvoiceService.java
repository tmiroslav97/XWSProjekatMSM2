package services.app.carrequestservice.service.intf;

import services.app.carrequestservice.model.Invoice;

import java.util.List;

public interface InvoiceService {

    Invoice findById(Long id);

    List<Invoice> findAll();

    Invoice save(Invoice invoice);

}
