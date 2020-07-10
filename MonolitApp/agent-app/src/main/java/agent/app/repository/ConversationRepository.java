package agent.app.repository;

import agent.app.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    @Query("SELECT conv FROM Conversation conv where conv.participantEndUserId=(?1) or conv.participantPublisherUserId=(?1)")
    List<Conversation> findAllByParticipantId(Long participantId);

    Conversation findByMainId(Long mainId);

    boolean existsById(Long id);
}
