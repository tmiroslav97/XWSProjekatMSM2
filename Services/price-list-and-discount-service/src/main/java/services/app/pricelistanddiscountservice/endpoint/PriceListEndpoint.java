package services.app.pricelistanddiscountservice.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.app.pricelistanddiscountservice.model.AddPriceListRequest;
import services.app.pricelistanddiscountservice.model.AddPriceListResponse;
import services.app.pricelistanddiscountservice.service.intf.PriceListService;

@Endpoint
public class PriceListEndpoint {

    private static final String NAMESPACE_URI = "http://www.app.services/carrequestservice/model";

    private final PriceListService priceListService;

    public PriceListEndpoint(PriceListService priceListService) {
        this.priceListService = priceListService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addPriceListRequest")
    @ResponsePayload
    public AddPriceListResponse addPriceListRequest(@RequestPayload AddPriceListRequest request) {
        AddPriceListResponse response = new AddPriceListResponse();
        Long publisherUser = priceListService.authAgent(request.getPublisherUserEmail(), request.getIdentifier());
        if (publisherUser == null) {
            response.setMainId(null);
            return response;
        }else{
            response.setMainId(priceListService.createPriceListFromAgent(publisherUser, request.getCreationDate(),
                    request.getPricePerDay(), request.getPricePerKm(), request.getPricePerKmCDW()));
            return response;
        }
    }

}
