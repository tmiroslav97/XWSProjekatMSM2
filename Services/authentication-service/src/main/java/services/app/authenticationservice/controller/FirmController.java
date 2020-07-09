package services.app.authenticationservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import services.app.authenticationservice.dto.firm.FirmRegDTO;
import services.app.authenticationservice.service.intf.FirmService;

@RestController
@RequestMapping(value = "/firm", produces = MediaType.APPLICATION_JSON_VALUE)
public class FirmController {

    private final FirmService firmService;

    public FirmController(FirmService firmService) {
        this.firmService = firmService;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestParam(value = "nextPage", required = false) Integer nextPage, @RequestParam(value = "size", required = false) Integer size) {
        if (nextPage != null) {
            return new ResponseEntity<>(firmService.findAll(nextPage, size), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(firmService.findAll(), HttpStatus.OK);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerFirm(@RequestBody FirmRegDTO firmRegDTO) {
        Integer flag = firmService.registerFirm(firmRegDTO);
        if (flag == 1) {
            return new ResponseEntity<>("Firma uspjesno registrovana.", HttpStatus.CREATED);
        } else if (flag == 2) {
            return new ResponseEntity<>("Greska pri registraciji firme.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Desila se nepoznata greska.", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delOrRevert(@RequestHeader("action") String action, @PathVariable("id") Long id, @RequestBody Boolean status) {
        if (action.equals("log-del")) {
            Integer flag = firmService.logicDeleteOrRevertById(id, status);
            if (flag == 1) {
                return new ResponseEntity<>("Firma uspjesno logicki obrisana.", HttpStatus.OK);
            } else if (flag == 2) {
                return new ResponseEntity<>("Firma uspjesno vracena.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Desila se nepoznata greska.", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Los zahtjev", HttpStatus.BAD_REQUEST);
        }
    }
}
