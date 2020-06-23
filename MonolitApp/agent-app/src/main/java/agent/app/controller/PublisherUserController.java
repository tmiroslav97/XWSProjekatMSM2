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

import java.security.Principal;

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
    public ResponseEntity<?> findBestAverageGradeAd(Principal principal) {
        System.out.println("Best average grade");
//        System.out.println(principal);
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        Principal principal = (Principal) auth.getPrincipal();
        return new ResponseEntity<>(publisherUserService.findBestAverageGrade(principal.getName()), HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('ROLE_AGENT')")
    @RequestMapping(value = "/max-mileage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findMaxMileageAd(Principal principal) {
        System.out.println("Max mileage contoller");
        System.out.println(principal.getName());
        return new ResponseEntity<>(publisherUserService.findMaxMileage(principal.getName()), HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('ROLE_AGENT')")
    @RequestMapping(value = "/max-comments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findMaxCommentsAd(Principal principal) {
        System.out.println("Max comments contoller");
        System.out.println(principal.getName());
        return new ResponseEntity<>(publisherUserService.findMaxComment(principal.getName()), HttpStatus.OK);

    }


    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_AGENT')")
    @RequestMapping(value="/publisher",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllPageAdFromPublisher(@RequestParam(value = "nextPage", required = false) Integer nextPage,
                                                        @RequestParam(value = "size", required = false) Integer size,
                                                        Principal principal) {

        return new ResponseEntity<>(publisherUserService.findAll(nextPage, size, principal.getName()), HttpStatus.OK);
    }
}
