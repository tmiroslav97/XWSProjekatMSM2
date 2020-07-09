package services.app.messageservice.service.intf;

import services.app.messageservice.dto.MessageRequestDTO;
import services.app.messageservice.model.Message;

import java.util.List;

public interface MessageService {
    Message findById(Long id);

    List<Message> findAllByConversationId(Long convId);

    Integer sendFirstMessage(MessageRequestDTO messageRequestDTO);

    Integer sendMessage(Message message);

    Message save(Message message);

    void deleteById(Long id);

    void delete(Message message);
}
