package agent.app.dto.conversation;

import agent.app.model.Conversation;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ConversationDTO {
    Integer unseenNum;
    Conversation conversation;
}
