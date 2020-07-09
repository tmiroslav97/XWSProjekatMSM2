package services.app.pricelistanddiscountservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="ad")
public interface AdClient {

    @GetMapping("/ad/pricelists")
    List<Long> getPricelistFromAds(@RequestHeader("userId") String userId,
                                   @RequestHeader("email") String email,
                                   @RequestHeader("roles") String roles,
                                   @RequestHeader("Authorization") String token);

    @GetMapping("/ad/ads-from-discount")
    List<Long> getAdsFromDiscount(@RequestParam("discountId") Long discountId,
                                   @RequestHeader("userId") String userId,
                                   @RequestHeader("email") String email,
                                   @RequestHeader("roles") String roles,
                                   @RequestHeader("Authorization") String token);

    @GetMapping("/ad/add-discount")
    Integer addDiscount(@RequestParam(value = "discountId") Long discountId,
                            @RequestHeader("userId") String userId,
                            @RequestHeader("email") String email,
                            @RequestHeader("roles") String roles,
                            @RequestHeader("Authorization") String token);

    @GetMapping("/ad/add-discount-to-ad")
    Integer addDiscountToAd(@RequestParam(value = "discountId") Long discountId,
                           @RequestParam(value = "adId") Long adId,
                           @RequestHeader("userId") String userId,
                           @RequestHeader("email") String email,
                           @RequestHeader("roles") String roles,
                           @RequestHeader("Authorization") String token);

    @GetMapping("/ad/remove-discount-from-ad")
    Integer removeDiscountToAd(@RequestParam(value = "discountId") Long discountId,
                              @RequestParam(value = "adId") Long adId,
                              @RequestHeader("userId") String userId,
                              @RequestHeader("email") String email,
                              @RequestHeader("roles") String roles,
                              @RequestHeader("Authorization") String token);


}
