package services.app.authenticationservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import services.app.authenticationservice.dto.VerificationResponse;
import services.app.authenticationservice.service.intf.EndUserService;

import java.security.Principal;

@RestController
@RequestMapping(value = "/end-user", produces = MediaType.APPLICATION_JSON_VALUE)
public class EndUserController {

    private final EndUserService endUserService;

    public EndUserController(EndUserService endUserService) {
        this.endUserService = endUserService;
    }

    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestParam(value = "nextPage", required = false) Integer nextPage, @RequestParam(value = "size", required = false) Integer size) {
        if (nextPage != null) {
            return new ResponseEntity<>(endUserService.findAll(nextPage, size), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(endUserService.findAll(), HttpStatus.OK);
        }
    }

    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> blockOrUnblockById(@RequestHeader("action") String action, @PathVariable("id") Long id, @RequestBody Boolean status) {
        if (action.equals("block")) {
            return new ResponseEntity<>(endUserService.blockOrUnblockById(id, status), HttpStatus.OK);
        } else if (action.equals("obligate")) {
            return new ResponseEntity<>(endUserService.obligateOrUnobligateById(id, status), HttpStatus.OK);
        } else if (action.equals("log-del")) {
            return new ResponseEntity<>(endUserService.logicDeleteOrRevertById(id, status), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Los zahtjev", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/limit-num", method = RequestMethod.POST)
    public Integer getAdLimitNum(Principal principal) {

        return endUserService.getAdLimitNum(principal.getName());
    }

    @RequestMapping(value = "/reduce-limit-num", method = RequestMethod.POST)
    public Integer reduceLimitNum(Principal principal) {

        return endUserService.reduceAdLimitNum(principal.getName());
    }
}
