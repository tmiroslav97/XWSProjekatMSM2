package agent.app.ws.client;

import org.joda.time.DateTime;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import services.app.pricelistanddiscountservice.model.AddPriceListRequest;
import services.app.pricelistanddiscountservice.model.AddPriceListResponse;

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
//        getWebServiceTemplate().setCheckConnectionForFault(true);
//        getWebServiceTemplate().setCheckConnectionForError(true);
        AddPriceListResponse response = (AddPriceListResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return response.getMainId();
    }
}
