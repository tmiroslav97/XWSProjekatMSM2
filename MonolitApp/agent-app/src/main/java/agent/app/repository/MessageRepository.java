package agent.app.repository;

import agent.app.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface MessageRepository  extends JpaRepository<Message, Long> {
    List<Message> findByConversationId(Long conversationId);

    @Transactional
    @Modifying
    @Query("UPDATE Message  msg set msg.recieverSeen=true where msg.recieverSeen=false and msg.conversationId=(?1) and msg.senderUser.email<>(?2)")
    void setAllConversationMessagesFromRecieverToSeen(Long conversationId, String recieverEmail);

    @Query("SELECT COUNT(msg) FROM Message msg where msg.conversationId=(?1) and msg.senderUser.email<>(?2) and msg.recieverSeen=false")
    Integer unsSeenMessages(Long conversationId, String email);

}
