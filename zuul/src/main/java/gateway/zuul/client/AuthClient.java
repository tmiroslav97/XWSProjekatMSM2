package gateway.zuul.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="auth")
public interface AuthClient {

    @PostMapping("/verify")
    VerificationResponse verify(String token);
}
