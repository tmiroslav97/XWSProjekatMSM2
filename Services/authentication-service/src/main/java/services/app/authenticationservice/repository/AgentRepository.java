package services.app.authenticationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import services.app.authenticationservice.model.Agent;

public interface AgentRepository extends JpaRepository<Agent, Long> {
}
