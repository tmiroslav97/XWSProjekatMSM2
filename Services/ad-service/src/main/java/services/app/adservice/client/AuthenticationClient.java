package services.app.adservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import services.app.adservice.dto.user.PublisherUserDTO;

@FeignClient(name="auth")
public interface AuthenticationClient {

    @PostMapping("/end-user/limit-num")
    Integer getAdLimitNum(@RequestHeader("Authorization")String token);

    @PostMapping("/end-user/reduce-limit-num")
    Integer reduceLimitNum(@RequestHeader("Authorization")String token);

    @PostMapping("/user/find-publish-user")
    Long findPublishUserByEmail(@RequestHeader("Authorization")String token);

    @GetMapping("/user/find-publish-user-by-id/{id}")
    PublisherUserDTO findPublishUserById(@PathVariable Long id);
}
