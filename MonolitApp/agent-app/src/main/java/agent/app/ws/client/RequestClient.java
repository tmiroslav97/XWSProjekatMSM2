package agent.app.ws.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import services.app.carrequestservice.model.GetPublisherRequestsByStatusRequest;
import services.app.carrequestservice.model.GetPublisherRequestsResponse;

public class RequestClient extends WebServiceGatewaySupport {

    public GetPublisherRequestsResponse getPublisherRequestsResponse(String email, String status) {
        GetPublisherRequestsByStatusRequest request = new GetPublisherRequestsByStatusRequest();
        request.setPublisherUser(email);
        request.setStatus(status);

        GetPublisherRequestsResponse response = (GetPublisherRequestsResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return response;
    }
}
