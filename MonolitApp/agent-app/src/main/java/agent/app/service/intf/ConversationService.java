package agent.app.service.intf;

import agent.app.dto.conversation.ConversationDTO;
import agent.app.model.Conversation;
import agent.app.model.Message;

import java.util.List;

public interface ConversationService {
    Conversation findById(Long id);

    Conversation findByMainId(Long mainId);

    List<ConversationDTO> findAllConversationDTOByParticipantId(String email);

    List<Message> findAllConversationMessages(Long id, String email);

    Conversation save(Conversation conversation);

    Boolean existsByid(Long id);

    void deleteById(Long id);

    void delete(Conversation conversation);
}
