package services.app.adservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import services.app.adservice.dto.pricelist.PriceListCreateDTO;


@FeignClient(name="pad")
public interface PricelistAndDiscountClient {

    @PostMapping("/pricelist/create-pricelist")
    Long createPricelist(@RequestBody PriceListCreateDTO priceListCreateDTO,
                         @RequestHeader("userId")String userId,
                         @RequestHeader("email")String email,
                         @RequestHeader("roles")String roles,
                         @RequestHeader("Auth")String token);

    @GetMapping("/pricelist/find-pricelist/{id}")
    Long findPriceList(@PathVariable Long id,
                       @RequestHeader("userId")String userId,
                       @RequestHeader("email")String email,
                       @RequestHeader("roles")String roles,
                       @RequestHeader("Auth")String token);

    @GetMapping("/pricelist/find-price-per-day/{id}")
    Float findPricePerDay(@PathVariable Long id);

}
