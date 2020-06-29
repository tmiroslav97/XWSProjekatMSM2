package services.app.authenticationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import services.app.authenticationservice.model.Firm;

@Repository
public interface FirmRepository extends JpaRepository<Firm, Long> {
}
