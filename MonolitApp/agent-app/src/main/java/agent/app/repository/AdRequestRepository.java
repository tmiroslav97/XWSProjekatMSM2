package agent.app.repository;

import agent.app.model.AdRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRequestRepository extends JpaRepository<AdRequest, Long> {
}
