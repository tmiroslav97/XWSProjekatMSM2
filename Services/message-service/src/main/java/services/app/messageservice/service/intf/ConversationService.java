package services.app.messageservice.service.intf;

import services.app.messageservice.model.Conversation;
import services.app.messageservice.model.Message;

import java.util.List;

public interface ConversationService {
    Conversation findById(Long id);

    List<Conversation> findAllByParticipantId(Long participantId);

    Conversation save(Conversation conversation);

    void deleteById(Long id);

    void delete(Conversation conversation);
}
