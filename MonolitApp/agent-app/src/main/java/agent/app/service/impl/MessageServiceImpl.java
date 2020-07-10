package agent.app.service.impl;

import agent.app.converter.DateAPI;
import agent.app.dto.SignUpDTO;
import agent.app.dto.conversation.ConvMsgSyncDTO;
import agent.app.exception.NotFoundException;
import agent.app.model.Conversation;
import agent.app.model.EndUser;
import agent.app.model.Message;
import agent.app.model.PublisherUser;
import agent.app.repository.MessageRepository;
import agent.app.service.intf.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private EndUserService endUserService;

    @Autowired
    private PublisherUserService publisherUserService;

    @Autowired
    private AuthenticationService authenticationService;

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
//        Conversation conversation = conversationService.findById(message.getConversationId());
//        if(conversation.getMainId()!=null){
//            
//        }
        return 2;
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

    @Override
    @RabbitListener(queues = "#{autoDeleteFirstMsg.name}")
    public void firstMsgSync(String msg) {
        try {
            ConvMsgSyncDTO convMsgSyncDTO = objectMapper.readValue(msg, ConvMsgSyncDTO.class);
            PublisherUser publisherUser = publisherUserService.findByEmail(convMsgSyncDTO.getRecieverEmail());
            EndUser endUser = endUserService.findByEmail(convMsgSyncDTO.getSenderEmail());
            if (endUser == null) {
                SignUpDTO signUpDTO = SignUpDTO.builder()
                        .email(convMsgSyncDTO.getSenderEmail())
                        .firstName(convMsgSyncDTO.getSenderFristName())
                        .lastName(convMsgSyncDTO.getSenderLastName())
                        .password("12345")
                        .password2("12345")
                        .build();
                authenticationService.signUp(signUpDTO);
                endUser = endUserService.findByEmail(convMsgSyncDTO.getSenderEmail());
            }
            Conversation conversation = new Conversation();
            conversation.setMessage(new ArrayList<>());
            conversation.setMainId(convMsgSyncDTO.getConvMainId());
            conversation.setConvName(convMsgSyncDTO.getConvName());
            conversation.setRequestId(convMsgSyncDTO.getRequestMainId());
            conversation.setParticipantPublisherUserId(publisherUser.getId());
            conversation.setParticipantEndUserId(endUser.getId());
            Message message = new Message();
            message.setContent(convMsgSyncDTO.getContent());
            message.setRecieverSeen(false);
            message.setSendDate(DateAPI.DateTimeNow());
            message.setSenderId(endUser.getId());
            message.setSenderEmail(endUser.getEmail());
            message.setSenderFirstName(endUser.getFirstName());
            message.setSenderLastName(endUser.getLastName());
            conversation = conversationService.save(conversation);
            message.setConversationId(conversation.getId());
            message = this.save(message);
            conversation.getMessage().add(message);
            conversation = conversationService.save(conversation);
        } catch (JsonProcessingException exception) {
        }
    }

    @Override
    @RabbitListener(queues = "#{autoDeleteMsg.name}")
    public void msgSync(String msg) {

    }
}
