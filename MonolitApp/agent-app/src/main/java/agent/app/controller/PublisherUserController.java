package agent.app.controller;

import agent.app.config.AppConfig;
import agent.app.service.intf.PublisherUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/publisher", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublisherUserController {

    private final PublisherUserService publisherUserService;
    private final AppConfig appConfig;

    public PublisherUserController(PublisherUserService publisherUserService, AppConfig appConfig) {
        this.publisherUserService = publisherUserService;
        this.appConfig = appConfig;
    }


    @PreAuthorize("hasAuthority('ROLE_AGENT')")
    @RequestMapping(value = "/best-average-grade", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findBestAverageGradeAd(@RequestParam(value = "email") String email) {
        System.out.println("Best average grade");
//        System.out.println(principal);
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        Principal principal = (Principal) auth.getPrincipal();
        System.out.println(email);
        return new ResponseEntity<>(publisherUserService.findBestAverageGrade(email), HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('ROLE_AGENT')")
    @RequestMapping(value = "/max-mileage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findMaxMileageAd(@RequestParam(value = "email") String email) {
        System.out.println("Max mileage contoller");
        System.out.println(email);
        return new ResponseEntity<>(publisherUserService.findMaxMileage(email), HttpStatus.OK);

    }

}
