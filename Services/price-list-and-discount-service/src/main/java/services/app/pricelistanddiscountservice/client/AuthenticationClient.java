package services.app.pricelistanddiscountservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="auth")
public interface AuthenticationClient {

    @PostMapping("/end-user/limit-num")
    Integer getAdLimitNum(@RequestHeader("Authorization")String token);

    @PostMapping("/user/find-publish-user")
    Long findPublishUserByEmail(@RequestHeader("Authorization")String token);

}
