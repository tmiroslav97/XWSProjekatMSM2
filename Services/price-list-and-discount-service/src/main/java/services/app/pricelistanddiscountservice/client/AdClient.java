package services.app.pricelistanddiscountservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name="ad")
public interface AdClient {

    @GetMapping("/ad/pricelists")
    List<Long> getPricelistFromAds(@RequestHeader("userId") String userId,
                                   @RequestHeader("email") String email,
                                   @RequestHeader("roles") String roles,
                                   @RequestHeader("Authorization") String token);
}
