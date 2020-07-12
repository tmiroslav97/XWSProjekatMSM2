package services.app.adservice.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.app.adservice.model.AddCarCalendarOccupationRequest;
import services.app.adservice.model.AddCarCalendarOccupationResponse;
import services.app.adservice.model.AddCarCalendarTermRequest;
import services.app.adservice.model.AddCarCalendarTermResponse;
import services.app.adservice.service.intf.CarCalendarTermService;

@Endpoint
public class CarCalendarTermEndpoint {
    private static final String NAMESPACE_URI = "http://www.app.services/adservice/model";

    private final CarCalendarTermService carCalendarTermService;

    public CarCalendarTermEndpoint(CarCalendarTermService carCalendarTermService) {
        this.carCalendarTermService = carCalendarTermService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addCarCalendarTermRequest")
    @ResponsePayload
    public AddCarCalendarTermResponse addCarCalendarTermRequest(@RequestPayload AddCarCalendarTermRequest request) {
        AddCarCalendarTermResponse response = new AddCarCalendarTermResponse();
        Long publisherUser = carCalendarTermService.authAgent(request.getPublisherUserEmail(), request.getIdentifier());
        if (publisherUser == null) {
            response.setResponse(null);
            return response;
        } else {
            Integer i = carCalendarTermService.addCarCalendarTermEndpoint(request.getMainIdAd(), request.getStartDate(), request.getEndDate());
            if (i == 1) {
                response.setResponse("Uspesno dodat slobodan termin.");
            } else {
                response.setResponse(null);
            }
            return response;
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addCarCalendarOccupationRequest")
    @ResponsePayload
    public AddCarCalendarOccupationResponse addCarCalendarOccupationRequest(@RequestPayload AddCarCalendarOccupationRequest request) {
        AddCarCalendarOccupationResponse response = new AddCarCalendarOccupationResponse();
        Long publisherUser = carCalendarTermService.authAgent(request.getPublisherUserEmail(), request.getIdentifier());
        if (publisherUser == null) {
            response.setResponse(null);
            return response;
        } else {
            Integer i = carCalendarTermService.addCarCalendarTermOccupationEndpoint(request.getMainIdAd(), request.getStartDate(), request.getEndDate());
            if (i == 1) {
                response.setResponse("Uspesno dodata zauzetost.");
            } else {
                response.setResponse(null);
            }
            return response;
        }
    }


}
