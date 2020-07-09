package services.app.carrequestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import services.app.carrequestservice.model.Invoice;

public interface InvoiceRepository  extends JpaRepository<Invoice, Long> {
}
