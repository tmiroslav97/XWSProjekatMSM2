package services.app.messageservice.service.intf;

import services.app.messageservice.dto.conversation.ConversationDTO;
import services.app.messageservice.model.Conversation;
import services.app.messageservice.model.Message;

import java.util.List;

public interface ConversationService {
    Conversation findById(Long id);

    List<Conversation> findAllByParticipantId(Long participantId);

    List<ConversationDTO> findAllConversationDTOByParticipantId(Long participantId);

    List<Message> findAllConversationMessages(Long id);

    Conversation save(Conversation conversation);

    Boolean existsByid(Long id);

    void deleteById(Long id);

    void delete(Conversation conversation);
}
