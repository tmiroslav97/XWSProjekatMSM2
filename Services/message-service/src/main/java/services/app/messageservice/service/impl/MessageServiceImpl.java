package services.app.messageservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.app.messageservice.config.RabbitMQConfiguration;
import services.app.messageservice.converter.ConversationConverter;
import services.app.messageservice.converter.DateAPI;
import services.app.messageservice.converter.MessageConverter;
import services.app.messageservice.dto.message.MessageRequestDTO;
import services.app.messageservice.dto.user.UserFLNameDTO;
import services.app.messageservice.exception.NotFoundException;
import services.app.messageservice.model.Conversation;
import services.app.messageservice.model.Message;
import services.app.messageservice.repository.MessageRepository;
import services.app.messageservice.service.intf.ConversationService;
import services.app.messageservice.service.intf.MessageService;

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
    public Integer sendFirstMessage(MessageRequestDTO messageRequestDTO) {
        Boolean convExists = conversationService.existsByid(messageRequestDTO.getRequestId());
        if (!convExists) {
            Message message = MessageConverter.toMessageFromMessageRequestDTO(messageRequestDTO);
            Conversation conversation = ConversationConverter.toConversationFromMessageRequestDTO(messageRequestDTO);
            conversation = conversationService.save(conversation);
            message.setConversationId(conversation.getId());
            message = this.save(message);
            conversation.getMessage().add(message);
            conversation = conversationService.save(conversation);
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public Integer sendMessage(Message message, Long senderUserId) {
        Conversation conversation = conversationService.findById(message.getConversationId());
        Long recieverUserId;
        if (conversation.getParticipantEndUserId() == senderUserId) {
            recieverUserId = conversation.getParticipantPublisherUserId();
        } else {
            recieverUserId = conversation.getParticipantEndUserId();
        }
        try {
            String senderUserStr = (String) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.USER_FL_NAME_QUEUE_NAME, senderUserId);
            String recieverUserStr = (String) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.USER_FL_NAME_QUEUE_NAME, recieverUserId);
            UserFLNameDTO senderUser = objectMapper.readValue(senderUserStr, UserFLNameDTO.class);
            UserFLNameDTO recieverUser = objectMapper.readValue(recieverUserStr, UserFLNameDTO.class);
            message.setRecieverSeen(false);
            message.setSendDate(DateAPI.DateTimeNow());
            message.setSenderId(senderUser.getUserId());
            message.setSenderEmail(senderUser.getUserEmail());
            message.setSenderFirstName(senderUser.getUserFirstName());
            message.setSenderLastName(senderUser.getUserLastName());
            message = this.save(message);
            conversation.getMessage().add(message);
            conversationService.save(conversation);
            return 1;
        } catch (JsonProcessingException exception) {
            return 2;
        }
    }

    @Override
    public Integer unsSeenMessages(Long conversationId, Long participantId) {
        return messageRepository.unsSeenMessages(conversationId, participantId);
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
