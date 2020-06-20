package services.app.authenticationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import services.app.authenticationservice.model.Firm;

public interface FirmRepository extends JpaRepository<Firm, Long> {
}
