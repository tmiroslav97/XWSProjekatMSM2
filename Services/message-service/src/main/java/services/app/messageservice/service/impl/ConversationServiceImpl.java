package services.app.messageservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import services.app.messageservice.exception.NotFoundException;
import services.app.messageservice.model.Conversation;
import services.app.messageservice.model.CustomPrincipal;
import services.app.messageservice.model.Message;
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
        return conversationRepository.findAllByParticipantId(participantId);
    }

    @Override
    public List<Message> findAllConversationMessages(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();
        messageService.setAllConversationMessagesFromRecieverToSeen(id, cp.getEmail());
        return this.findById(id).getMessage();
    }

    @Override
    public Conversation save(Conversation conversation) {
        return conversationRepository.save(conversation);
    }

    @Override
    public Boolean existsByid(Long id) {
        return conversationRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Conversation conversation) {

    }
}
