package services.app.adservice.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.app.adservice.dto.ad.ReversePricelistDTO;
import services.app.adservice.model.*;
import services.app.adservice.service.intf.AdService;

@Endpoint
public class AdEndpoint {

    private static final String NAMESPACE_URI = "http://www.app.services/adservice/model";

    private final AdService adService;

    public AdEndpoint(AdService adService) {
        this.adService = adService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createAd")
    @ResponsePayload
    public CreateAdResponse createAd(@RequestPayload CreateAdRequest request) {
        CreateAdResponse response = new CreateAdResponse();
        Long publisherUser = adService.authAgent(request.getPublisherUserEmail(), request.getIdentifier());
        if (publisherUser == null) {
            response.setMainId(null);
            return response;
        }else{
            response.setMainId(adService.createAdFromAgent(request.getNewAd()));
            return response;
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "reversePricelist")
    @ResponsePayload
    public ReversePricelistResponse reversePricelist(@RequestPayload ReversePricelistRequest request) {
        ReversePricelistResponse response = new ReversePricelistResponse();
        Long publisherUser = adService.authAgent(request.getPublisherUserEmail(), request.getIdentifier());
        if (publisherUser == null) {
            response.setResponse(null);
            return response;
        }else{
            ReversePricelistDTO dto = new ReversePricelistDTO();
            dto.setAdId(request.getMainIdAd());
            dto.setPricelistId(request.getPricelistId());
            Integer i = adService.reversePricelist(dto);
            if(i==1){
                response.setResponse("Uspesno zamenjen cenovnik.");
            }else{
                response.setResponse(null);
            }
            return response;
        }
    }



}
