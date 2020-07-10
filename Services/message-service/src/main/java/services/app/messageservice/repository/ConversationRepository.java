package services.app.messageservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import services.app.messageservice.model.Conversation;

import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    @Query("SELECT conv FROM Conversation conv where conv.participantEndUserId=(?1) or conv.participantPublisherUserId=(?1)")
    List<Conversation> findAllByParticipantId(Long participantId);

    boolean existsById(Long id);

}
