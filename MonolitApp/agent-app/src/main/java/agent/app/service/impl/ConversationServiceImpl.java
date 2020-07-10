package agent.app.service.impl;

import agent.app.converter.ConversationConverter;
import agent.app.dto.conversation.ConversationDTO;
import agent.app.exception.NotFoundException;
import agent.app.model.Conversation;
import agent.app.model.Message;
import agent.app.repository.ConversationRepository;
import agent.app.service.intf.ConversationService;
import agent.app.service.intf.MessageService;
import agent.app.service.intf.PublisherUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConversationServiceImpl implements ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private MessageService messageService;

    @Autowired
    private PublisherUserService publisherUserService;

    @Override
    public Conversation findById(Long id) {
        return conversationRepository.findById(id).orElseThrow(() -> new NotFoundException("Konverzacija ne postoji"));
    }


    @Override
    public List<ConversationDTO> findAllConversationDTOByParticipantId(String email) {
        List<Conversation> conversations = new ArrayList<>(publisherUserService.findByEmail(email).getConversations());
        List<ConversationDTO> conversationDTOS = new ArrayList<>();
        conversations.forEach(conversation -> {
            Integer unseenNum = messageService.unsSeenMessages(conversation.getId(), email);
            ConversationDTO conversationDTO = ConversationConverter.toCreateConversationDTOFromConversationAndUnseenNum(conversation, unseenNum);
            conversationDTOS.add(conversationDTO);
        });
        return conversationDTOS;
    }

    @Override
    public List<Message> findAllConversationMessages(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Principal cp = (Principal) auth.getPrincipal();
        messageService.setAllConversationMessagesFromRecieverToSeen(id, cp.getName());
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
