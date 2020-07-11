package agent.app.ws.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import services.app.adservice.model.*;

public class AdClient extends WebServiceGatewaySupport {

    public Long createAd(String publisherUserEmail, String identifier, AdSync newAd){
        CreateAdRequest request = new CreateAdRequest();
        request.setPublisherUserEmail(publisherUserEmail);
        request.setIdentifier(identifier);
        request.setNewAd(newAd);
        CreateAdResponse response = (CreateAdResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return response.getMainId();
    }

    public String reversePricelist(String publisherUserEmail, String identifier, Long mainIdAd, Long pricelistId){
        ReversePricelistRequest request = new ReversePricelistRequest();
        request.setPublisherUserEmail(publisherUserEmail);
        request.setIdentifier(identifier);
        request.setMainIdAd(mainIdAd);
        request.setPricelistId(pricelistId);
        ReversePricelistResponse response = (ReversePricelistResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return response.getResponse();
    }

    public String addCarCalendarTerm(String publisherUserEmail,String identifier,
                                     Long mainIdAd, String startDate, String endDate ){
        AddCarCalendarTermRequest request = new AddCarCalendarTermRequest();
        request.setPublisherUserEmail(publisherUserEmail);
        request.setIdentifier(identifier);
        request.setMainIdAd(mainIdAd);
        request.setStartDate(startDate);
        request.setEndDate(endDate);
        AddCarCalendarTermResponse response = (AddCarCalendarTermResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return response.getResponse();
    }

    public String addCarCalendarOccupation(String publisherUserEmail,String identifier,
                                     Long mainIdAd, String startDate, String endDate ){
        AddCarCalendarOccupationRequest request = new AddCarCalendarOccupationRequest();
        request.setPublisherUserEmail(publisherUserEmail);
        request.setIdentifier(identifier);
        request.setMainIdAd(mainIdAd);
        request.setStartDate(startDate);
        request.setEndDate(endDate);
        AddCarCalendarOccupationResponse response = (AddCarCalendarOccupationResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return response.getResponse();
    }



}
