package agent.app.repository;

import agent.app.model.EndUser;
import agent.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndUserRepository extends JpaRepository<EndUser, Long> {
    EndUser findByEmail(String email);
    Boolean existsByEmail(String email);
}
