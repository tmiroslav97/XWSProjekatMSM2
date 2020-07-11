package services.app.messageservice.dto.conversation;

import lombok.*;
import services.app.messageservice.model.Conversation;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ConversationDTO {
    Integer unseenNum;
    Conversation conversation;
}
