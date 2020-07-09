package services.app.messageservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.app.messageservice.exception.NotFoundException;
import services.app.messageservice.model.Conversation;
import services.app.messageservice.repository.ConversationRepository;
import services.app.messageservice.service.intf.ConversationService;
import services.app.messageservice.service.intf.MessageService;

import java.util.List;

@Service
public class ConversationServiceImpl implements ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private MessageService messageService;

    @Override
    public Conversation findById(Long id) {
        return conversationRepository.findById(id).orElseThrow(() -> new NotFoundException("Konverzacija ne postoji"));
    }

    @Override
    public List<Conversation> findAllByParticipantId(Long participantId) {
        return null;
    }

    @Override
    public Conversation save(Conversation conversation) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Conversation conversation) {

    }
}
