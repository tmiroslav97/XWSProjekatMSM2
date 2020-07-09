package services.app.messageservice.converter;

import services.app.messageservice.dto.MessageRequestDTO;
import services.app.messageservice.model.Message;

public class MessageConverter extends  AbstractConverter {

    public static Message toMessageFromMessageRequestDTO(MessageRequestDTO messageRequestDTO){
        return Message.builder()
                .content(messageRequestDTO.getContent())
                .sendDate(DateAPI.DateTimeNow())
                .senderEmail(messageRequestDTO.getEndUserEmail())
                .senderFirstName(messageRequestDTO.getEndUserFirstName())
                .senderLastName(messageRequestDTO.getEndUserLastName())
                .senderId(messageRequestDTO.getEndUserId())
                .recieverSeen(false)
                .build();
    }
}
