package services.app.authenticationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import services.app.authenticationservice.model.EndUser;

@Repository
public interface EndUserRepository extends JpaRepository<EndUser, Long> {
    EndUser findByEmail(String email);
    Boolean existsByEmail(String email);
}
