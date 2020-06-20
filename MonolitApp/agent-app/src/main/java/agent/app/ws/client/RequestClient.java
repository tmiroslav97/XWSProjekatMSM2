package agent.app.ws.client;

import agent.app.gen.GetPublisherRequestsByStatusRequest;
import agent.app.gen.GetPublisherRequestsResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class RequestClient extends WebServiceGatewaySupport {

    public GetPublisherRequestsResponse getPublisherRequestsResponse(String email, String status) {
        GetPublisherRequestsByStatusRequest request = new GetPublisherRequestsByStatusRequest();
        request.setPublisherUser(email);
        request.setStatus(status);

        GetPublisherRequestsResponse response = (GetPublisherRequestsResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return response;
    }
}
