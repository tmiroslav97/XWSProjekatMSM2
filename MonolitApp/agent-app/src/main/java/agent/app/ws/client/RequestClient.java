package agent.app.ws.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import services.app.carrequestservice.model.AcceptRequest;
import services.app.carrequestservice.model.GetPublisherRequestsByStatusRequest;
import services.app.carrequestservice.model.GetPublisherRequestsResponse;

public class RequestClient extends WebServiceGatewaySupport {

    public GetPublisherRequestsResponse getPublisherRequestsResponse(String email, String identifier, String status) {
        GetPublisherRequestsByStatusRequest request = new GetPublisherRequestsByStatusRequest();
        request.setPublisherUserEmail(email);
        request.setIdentifier(identifier);
        request.setStatus(status);

        GetPublisherRequestsResponse response = (GetPublisherRequestsResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return response;
    }

    public String acceptRequest(String email, String identifier, Long id, String action) {
        AcceptRequest request = new AcceptRequest();
        request.setPublisherUserEmail(email);
        request.setIdentifier(identifier);
        request.setId(id);
        request.setAction(action);

        String response = (String) getWebServiceTemplate().marshalSendAndReceive(request);
        return response;
    }
}
