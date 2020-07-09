package services.app.carrequestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import services.app.carrequestservice.model.Report;

public interface ReportRepository  extends JpaRepository<Report, Long> {
}
