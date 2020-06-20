package services.app.carrequestservice.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.app.carrequestservice.model.GetPublisherRequestsByStatusRequest;
import services.app.carrequestservice.model.GetPublisherRequestsRequest;
import services.app.carrequestservice.model.GetPublisherRequestsResponse;
import services.app.carrequestservice.service.intf.RequestService;

@Endpoint
public class RequestEndpoint {
    private static final String NAMESPACE_URI = "http://www.app.services/carrequestservice/model";

    private final RequestService requestService;

    public RequestEndpoint(RequestService requestService) {
        this.requestService = requestService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPublisherRequestsRequest")
    @ResponsePayload
    public GetPublisherRequestsResponse getAllRequestsByPublisherEmail(@RequestPayload GetPublisherRequestsRequest request) {
        GetPublisherRequestsResponse response = new GetPublisherRequestsResponse();
        response.getRequests().addAll(requestService.findAllByPublisherUserEmail(request.getPublisherUser()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPublisherRequestsByStatusRequest")
    @ResponsePayload
    public GetPublisherRequestsResponse getAllRequestsByPublisherEmailAndStatus(@RequestPayload GetPublisherRequestsByStatusRequest request) {
        GetPublisherRequestsResponse response = new GetPublisherRequestsResponse();
        response.getRequests().addAll(requestService.findAllByPublisherUserEmailAndStatus(request.getPublisherUser(), request.getStatus()));
        return response;
    }
}
