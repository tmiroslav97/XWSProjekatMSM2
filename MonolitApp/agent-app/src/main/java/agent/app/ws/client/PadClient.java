package agent.app.ws.client;

import org.joda.time.DateTime;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import services.app.pricelistanddiscountservice.model.*;

public class PadClient extends WebServiceGatewaySupport {

    public Long addPriceListRequest(String email, String identifier, DateTime creationDate,
                                      Float pricePerDay, Float pricePerKm, Float pricePerKmCDW) {
        AddPriceListRequest request = new AddPriceListRequest();
        request.setPublisherUserEmail(email);
        request.setIdentifier(identifier);
        request.setCreationDate(creationDate);
        request.setPricePerDay(pricePerDay);
        request.setPricePerKm(pricePerKm);
        request.setPricePerKmCDW(pricePerKmCDW);
        AddPriceListResponse response = (AddPriceListResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return response.getMainId();
    }

    public Long editPriceListRequest(String email, String identifier, Float pricePerDay,
                                     Float pricePerKm, Float pricePerKmCDW, Long mainId){
        EditPriceListRequest request = new EditPriceListRequest();
        request.setPublisherUserEmail(email);
        request.setIdentifier(identifier);
        request.setMainId(mainId);
        request.setPricePerDay(pricePerDay);
        request.setPricePerKm(pricePerKm);
        request.setPricePerKmCDW(pricePerKmCDW);

        EditPriceListResponse response = (EditPriceListResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return (Long) response.getMainId();
    }

    public Long deletePriceListRequest(String publisherUserEmail, String identifier, Long mainId){
        DeletePriceListRequest request = new DeletePriceListRequest();
        request.setPublisherUserEmail(publisherUserEmail);
        request.setIdentifier(identifier);
        request.setMainId(mainId);
        DeletePriceListResponse response = (DeletePriceListResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return response.getMainId();
    }

    public Long addDiscountListRequest(String publisherUserEmail, String identifier, Integer dayNum,
                                       Float discount){
        AddDiscountListRequest request = new AddDiscountListRequest();
        request.setPublisherUserEmail(publisherUserEmail);
        request.setIdentifier(identifier);
        request.setDayNum(dayNum);
        request.setDiscount(discount);
        AddPriceListResponse response = (AddPriceListResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return response.getMainId();
    }

    public Long editDiscountListRequest(String publisherUserEmail, String identifier, Integer dayNum,
                                        Float discount, Long mainId ){
        EditDiscountListRequest request = new EditDiscountListRequest();
        request.setPublisherUserEmail(publisherUserEmail);
        request.setIdentifier(identifier);
        request.setDayNum(dayNum);
        request.setDiscount(discount);
        request.setMainId(mainId);
        EditDiscountListResponse response = (EditDiscountListResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return response.getMainId();
    }

    public Long deleteDiscountListRequest(String publisherUserEmail, String identifier, Long mainId ){
        DeleteDiscountListRequest request = new DeleteDiscountListRequest();
        request.setPublisherUserEmail(publisherUserEmail);
        request.setIdentifier(identifier);
        request.setMainId(mainId);
        DeleteDiscountListResponse response = (DeleteDiscountListResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return response.getMainId();
    }

    public Long addDiscountListToAdRequest(String publisherUserEmail, String identifier, Long mainIdDiscount,
                                           Long mainIdAd){
        AddDiscountListToAdRequest request = new AddDiscountListToAdRequest();
        request.setPublisherUserEmail(publisherUserEmail);
        request.setIdentifier(identifier);
        request.setMainIdAd(mainIdAd);
        request.setMainIdDiscount(mainIdDiscount);
        AddDiscountListToAdResponse response = (AddDiscountListToAdResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return response.getMainId();
    }
}
