package services.app.messageservice.converter;

import services.app.messageservice.dto.MessageRequestDTO;
import services.app.messageservice.model.Conversation;

import java.util.ArrayList;

public class ConversationConverter extends  AbstractConverter {

    public static Conversation toConversationFromMessageRequestDTO(MessageRequestDTO messageRequestDTO){
        return Conversation.builder()
                .convName(messageRequestDTO.getConvName())
                .requestId(messageRequestDTO.getRequestId())
                .participantEndUserId(messageRequestDTO.getEndUserId())
                .participantPublisherUserId(messageRequestDTO.getPublisherUserId())
                .message(new ArrayList<>())
                .build();
    }
}
