package agent.app.repository;

import agent.app.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
    Agent findByEmail(String email);
}
