package services.app.pricelistanddiscountservice.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.app.pricelistanddiscountservice.model.*;
import services.app.pricelistanddiscountservice.service.intf.DiscountListService;

@Endpoint
public class DiscountListEndpoint {

    private static final String NAMESPACE_URI = "http://www.app.services/pricelistanddiscountservice/model";

    private final DiscountListService discountListService;

    public DiscountListEndpoint(DiscountListService discountListService) {
        this.discountListService = discountListService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addDiscountListRequest")
    @ResponsePayload
    public AddDiscountListResponse addDiscountListRequest(@RequestPayload AddDiscountListRequest request) {
        AddDiscountListResponse response = new AddDiscountListResponse();

        Long publisherUser = discountListService.authAgent(request.getPublisherUserEmail(), request.getIdentifier());
        if (publisherUser == null) {
            response.setMainId(null);
            return response;
        } else {
            response.setMainId(discountListService.addDiscountListFromAgent(publisherUser, request.getDayNum(), request.getDiscount()));
            return response;
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "editDiscountListRequest")
    @ResponsePayload
    public EditDiscountListResponse editDiscountListRequest(@RequestPayload EditDiscountListRequest request) {
        EditDiscountListResponse response = new EditDiscountListResponse();
        Long publisherUser = discountListService.authAgent(request.getPublisherUserEmail(), request.getIdentifier());
        if (publisherUser == null) {
            response.setMainId(null);
            return response;
        } else {
            response.setMainId(discountListService.editDiscountListFromAgent(publisherUser, request.getDayNum(), request.getDiscount(), request.getMainId()));
            return response;
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteDiscountListRequest")
    @ResponsePayload
    public DeleteDiscountListResponse deleteDiscountListRequest(@RequestPayload DeleteDiscountListRequest request) {
        DeleteDiscountListResponse response = new DeleteDiscountListResponse();
        Long publisherUser = discountListService.authAgent(request.getPublisherUserEmail(), request.getIdentifier());
        if (publisherUser == null) {
            response.setMainId(null);
            return response;
        } else {
            response.setMainId(discountListService.deleteDiscountListFromAgent(publisherUser, request.getMainId()));
            return response;
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addDiscountListToAdRequest")
    @ResponsePayload
    public AddDiscountListToAdResponse addDiscountListToAdRequest(@RequestPayload AddDiscountListToAdRequest request) {
        AddDiscountListToAdResponse response = new AddDiscountListToAdResponse();

        Long publisherUser = discountListService.authAgent(request.getPublisherUserEmail(), request.getIdentifier());
        if (publisherUser == null) {
            response.setMainId(null);
            return response;
        } else {
            response.setMainId(discountListService.addDiscountListToAdFromAgent(publisherUser, request.getMainIdDiscount(), request.getMainIdAd()));
            return response;
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "removeDiscountListFromAdRequest")
    @ResponsePayload
    public RemoveDiscountListFromAdResponse removeDiscountListFromAdRequest(@RequestPayload RemoveDiscountListFromAdRequest request) {
        RemoveDiscountListFromAdResponse response = new RemoveDiscountListFromAdResponse();
        Long publisherUser = discountListService.authAgent(request.getPublisherUserEmail(), request.getIdentifier());
        if (publisherUser == null) {
            response.setMainId(null);
            return response;
        } else {
            response.setMainId(discountListService.removeDiscountListFromAdFromAgent(publisherUser, request.getMainIdDiscount(), request.getMainIdAd()));
            return response;
        }
    }
}
