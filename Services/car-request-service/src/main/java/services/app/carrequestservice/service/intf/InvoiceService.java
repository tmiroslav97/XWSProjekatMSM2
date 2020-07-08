package services.app.carrequestservice.service.intf;

import services.app.carrequestservice.model.Invoice;

public interface InvoiceService {

    Invoice findById(Long id);

    Invoice save(Invoice invoice);

}
