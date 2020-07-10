package services.app.pricelistanddiscountservice.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.app.pricelistanddiscountservice.model.*;
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

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "editPriceListRequest")
    @ResponsePayload
    public EditPriceListResponse editPriceListRequest(@RequestPayload EditPriceListRequest request) {
        EditPriceListResponse response = new EditPriceListResponse();
        Long publisherUser = priceListService.authAgent(request.getPublisherUserEmail(), request.getIdentifier());
        if (publisherUser == null) {
            response.setMainId(null);
            return response;
        }else{
            response.setMainId(priceListService.editPriceListFromAgent(request.getPricePerDay(),
                    request.getPricePerKm(), request.getPricePerKmCDW(), request.getMainId()));
            return response;
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePriceListRequest")
    @ResponsePayload
    public DeletePriceListResponse deletePriceListRequest(@RequestPayload DeletePriceListRequest request) {
        DeletePriceListResponse response = new DeletePriceListResponse();
        Long publisherUser = priceListService.authAgent(request.getPublisherUserEmail(), request.getIdentifier());
        if (publisherUser == null) {
            response.setMainId(null);
            return response;
        }else{
            response.setMainId(priceListService.deletePriceListFromAgent(request.getMainId()));
            return response;
        }
    }


}
