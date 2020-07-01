package services.app.authenticationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import services.app.authenticationservice.model.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

    Boolean existsByIdentifier(String identifier);

    Agent findByEmail(String email);
}
