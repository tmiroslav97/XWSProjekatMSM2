package agent.app.ws.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import services.app.carrequestservice.model.AcceptRequest;
import services.app.carrequestservice.model.AcceptResponse;

public class RequestClient extends WebServiceGatewaySupport {



    public String acceptRequest(String email, String identifier, Long id, String action) {
        AcceptRequest request = new AcceptRequest();
        request.setPublisherUserEmail(email);
        request.setIdentifier(identifier);
        request.setId(id);
        request.setAction(action);

        AcceptResponse response = (AcceptResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return response.getMsg();
    }


}
