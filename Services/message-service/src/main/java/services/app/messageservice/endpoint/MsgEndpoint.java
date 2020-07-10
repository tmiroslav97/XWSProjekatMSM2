package services.app.messageservice.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.app.messageservice.model.SendMessageRequest;
import services.app.messageservice.model.SendMessageResponse;
import services.app.messageservice.service.intf.MessageService;

@Endpoint
public class MsgEndpoint {
    private static final String NAMESPACE_URI = "http://www.app.services/messageservice/model";

    private final MessageService messageService;

    public MsgEndpoint(MessageService messageService) {
        this.messageService = messageService;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendMessageRequest")
    @ResponsePayload
    public SendMessageResponse sendMessage(@RequestPayload SendMessageRequest request) {
        SendMessageResponse sendMessageResponse = new SendMessageResponse();
        Long publisherUser = messageService.authAgent(request.getPublisherUserEmail(), request.getIdentifier());
        if (publisherUser != null) {
            String response = messageService.sendMessageSyncAgent(request.getMessage(), publisherUser);
            sendMessageResponse.setMsg(response);
            return sendMessageResponse;
        } else {
            String response = "Niste registrovani na rent-a-car sistem";
            sendMessageResponse.setMsg(response);
            return sendMessageResponse;
        }
    }
}
