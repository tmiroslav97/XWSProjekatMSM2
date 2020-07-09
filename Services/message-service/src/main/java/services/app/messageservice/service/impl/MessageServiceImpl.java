package services.app.messageservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.app.messageservice.exception.NotFoundException;
import services.app.messageservice.model.Message;
import services.app.messageservice.repository.MessageRepository;
import services.app.messageservice.service.intf.MessageService;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message findById(Long id) {
        return messageRepository.findById(id).orElseThrow(() -> new NotFoundException("Poruka ne postoji"));
    }

    @Override
    public List<Message> findAllByConversationId(Long convId) {
        return messageRepository.findByConversationId(convId);
    }

    @Override
    public Integer sendMessage(Message message) {
        return null;
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
