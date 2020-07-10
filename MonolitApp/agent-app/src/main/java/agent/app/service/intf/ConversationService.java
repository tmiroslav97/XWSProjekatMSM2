package agent.app.service.intf;

import agent.app.dto.conversation.ConversationDTO;
import agent.app.model.Conversation;
import agent.app.model.Message;

import java.util.List;

public interface ConversationService {
    Conversation findById(Long id);

    List<ConversationDTO> findAllConversationDTOByParticipantId(String email);

    List<Message> findAllConversationMessages(Long id);

    Conversation save(Conversation conversation);

    Boolean existsByid(Long id);

    void deleteById(Long id);

    void delete(Conversation conversation);
}
