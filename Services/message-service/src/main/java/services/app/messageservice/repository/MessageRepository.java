package services.app.messageservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import services.app.messageservice.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
