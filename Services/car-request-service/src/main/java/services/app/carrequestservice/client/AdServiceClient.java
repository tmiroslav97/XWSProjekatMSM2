package services.app.carrequestservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import services.app.carrequestservice.dto.carreq.AcceptReqestCalendarTermsDTO;

@FeignClient(name = "ad")
public interface AdServiceClient {

    @PostMapping("/ad/accept")
    Boolean acceptCarCalendar(@RequestBody AcceptReqestCalendarTermsDTO acceptReqestCalendarTermsDTO);
}
