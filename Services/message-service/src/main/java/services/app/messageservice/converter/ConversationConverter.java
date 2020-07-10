package services.app.messageservice.converter;

import services.app.messageservice.dto.conversation.ConversationDTO;
import services.app.messageservice.dto.message.MessageRequestDTO;
import services.app.messageservice.model.Conversation;

import java.util.ArrayList;

public class ConversationConverter extends  AbstractConverter {

    public static ConversationDTO toCreateConversationDTOFromConversationAndUnseenNum(Conversation conversation, Integer unseenNum){
        return ConversationDTO.builder()
                .unseenNum(unseenNum)
                .conversation(conversation)
                .build();
    }
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
