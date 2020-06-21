package agent.app.ws.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import services.app.carrequestservice.model.AcceptRequest;
import services.app.carrequestservice.model.GetPublisherRequestsByStatusRequest;
import services.app.carrequestservice.model.GetPublisherRequestsResponse;
import services.app.carrequestservice.model.SubmitResponse;

public class RequestClient extends WebServiceGatewaySupport {

    public GetPublisherRequestsResponse getPublisherRequestsResponse(String email, String status) {
        GetPublisherRequestsByStatusRequest request = new GetPublisherRequestsByStatusRequest();
        request.setPublisherUser(email);
        request.setStatus(status);

        GetPublisherRequestsResponse response = (GetPublisherRequestsResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return response;
    }

    public String acceptRequest(String email, Long id, String action) {
        AcceptRequest request = new AcceptRequest();
        request.setPublisherUser(email);
        request.setId(id);
        request.setAction(action);

        SubmitResponse response = (SubmitResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return response.getMsg();
    }
}
