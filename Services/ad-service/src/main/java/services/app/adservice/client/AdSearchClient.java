package services.app.adservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import services.app.adservice.dto.ad.AdSynchronizeDTO;
import services.app.adservice.model.Ad;

@FeignClient(name="ad-search")
public interface AdSearchClient {

    @PostMapping("/ad/synchronize")
    Integer synchronizeDatabase(@RequestBody AdSynchronizeDTO adSynchronizeDTO,
                                @RequestHeader("userId")String userId,
                                @RequestHeader("email")String email,
                                @RequestHeader("roles")String roles,
                                @RequestHeader("Auth")String token);



}
