package agent.app.repository;

import agent.app.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    boolean existsById(Long id);
}
