package services.app.pricelistanddiscountservice.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import services.app.pricelistanddiscountservice.service.intf.DiscountListService;

@Endpoint
public class DiscountListEndpoint {

    private static final String NAMESPACE_URI = "http://www.app.services/carrequestservice/model";

    private final DiscountListService discountListService;

    public DiscountListEndpoint(DiscountListService discountListService) {
        this.discountListService = discountListService;
    }

}
