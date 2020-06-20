package services.app.adsearchservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name="pad")
public interface PricelistAndDiscountClient {


    @PostMapping("/pricelist/find-pricelist")
    Long findPriceList(Long id);

}
