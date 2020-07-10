package agent.app.service.impl;

import agent.app.exception.NotFoundException;
import agent.app.model.Conversation;
import agent.app.model.Message;
import agent.app.repository.MessageRepository;
import agent.app.service.intf.ConversationService;
import agent.app.service.intf.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Message findById(Long id) {
        return messageRepository.findById(id).orElseThrow(() -> new NotFoundException("Poruka ne postoji"));
    }

    @Override
    public List<Message> findAllByConversationId(Long conversationId) {
        return messageRepository.findByConversationId(conversationId);
    }

    @Override
    public void setAllConversationMessagesFromRecieverToSeen(Long conversationId, String recieverEmail) {
        messageRepository.setAllConversationMessagesFromRecieverToSeen(conversationId, recieverEmail);
    }


    @Override
    public Integer sendMessage(Message message, String email) {
       return null;
    }

    @Override
    public Integer unsSeenMessages(Long conversationId, String email) {
        return messageRepository.unsSeenMessages(conversationId, email);
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void deleteById(Long id) {
        Message message = this.findById(id);
        this.delete(message);
    }

    @Override
    public void delete(Message message) {
        messageRepository.delete(message);
    }
}
