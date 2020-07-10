package agent.app.converter;


import agent.app.dto.conversation.ConversationDTO;
import agent.app.model.Conversation;

public class ConversationConverter extends  AbstractConverter {

    public static ConversationDTO toCreateConversationDTOFromConversationAndUnseenNum(Conversation conversation, Integer unseenNum){
        return ConversationDTO.builder()
                .unseenNum(unseenNum)
                .conversation(conversation)
                .build();
    }

}
