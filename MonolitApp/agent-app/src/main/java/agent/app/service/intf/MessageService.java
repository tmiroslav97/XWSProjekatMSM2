package agent.app.service.intf;


import agent.app.model.Message;

import java.util.List;

public interface MessageService {
    Message findById(Long id);

    List<Message> findAllByConversationId(Long convId);

    void setAllConversationMessagesFromRecieverToSeen(Long conversationId, String recieverEmail);

    Integer sendMessage(Message message, String email);

    Integer unsSeenMessages(Long conversationId, String email);

    Message save(Message message);

    void deleteById(Long id);

    void delete(Message message);

    void firstMsgSync(String msg);

    void msgSync(String msg);
}
