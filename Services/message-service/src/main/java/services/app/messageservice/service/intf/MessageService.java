package services.app.messageservice.service.intf;

import services.app.messageservice.dto.MessageDTO;
import services.app.messageservice.model.Message;

import java.util.List;

public interface MessageService {
    Message findById(Long id);

    List<MessageDTO> findAllByRequestId(Long requestId);

    Integer sendMessage(Message message);

    Message save(Message message);
}
