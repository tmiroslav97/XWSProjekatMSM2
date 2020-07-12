package services.app.messageservice.service.intf;

import services.app.messageservice.dto.message.MessageRequestDTO;
import services.app.messageservice.model.Message;

import java.util.List;

public interface MessageService {
    Message findById(Long id);

    List<Message> findAllByConversationId(Long convId);

    void setAllConversationMessagesFromRecieverToSeen(Long conversationId, String recieverEmail);

    String sendMessageSyncAgent(Message message, Long publisherUserId);

    Integer sendFirstMessage(MessageRequestDTO messageRequestDTO);

    Integer sendMessage(Message message, Long senderUserId);

    Integer unsSeenMessages(Long conversationId, Long participantId);

    Message save(Message message);

    void deleteById(Long id);

    void delete(Message message);

    Long authAgent(String email, String identifier);

}
