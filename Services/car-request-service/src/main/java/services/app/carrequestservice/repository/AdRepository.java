package services.app.carrequestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import services.app.carrequestservice.model.Ad;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
    boolean existsById(Long id);
}
