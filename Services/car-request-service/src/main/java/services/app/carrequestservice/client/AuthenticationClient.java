package services.app.carrequestservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="auth")
public interface AuthenticationClient {

    @GetMapping("/user/find-publish-user/ws/{email}")
    Long findPublishUserByEmailWS(@PathVariable String email);
}
