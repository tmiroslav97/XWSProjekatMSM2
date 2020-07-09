package services.app.messageservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import services.app.messageservice.model.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByConversationId(Long id);
}
