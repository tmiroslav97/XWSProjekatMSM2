package agent.app.ws.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import services.app.messageservice.model.Message;
import services.app.messageservice.model.SendMessageRequest;
import services.app.messageservice.model.SendMessageResponse;

public class MsgClient extends WebServiceGatewaySupport {

    public String sendMessage(Message message, String publisherUserEmail, String identifier) {
        SendMessageRequest request = new SendMessageRequest();
        request.setMessage(message);
        request.setPublisherUserEmail(publisherUserEmail);
        request.setIdentifier(identifier);
        SendMessageResponse sendMessageResponse = (SendMessageResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return sendMessageResponse.getMsg();
    }
}
