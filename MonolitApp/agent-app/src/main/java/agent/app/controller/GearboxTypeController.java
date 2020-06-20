package agent.app.controller;

import agent.app.model.GearboxType;
import agent.app.service.intf.GearboxTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/gb-type", produces = MediaType.APPLICATION_JSON_VALUE)
public class GearboxTypeController {

    private final GearboxTypeService gearboxTypeService;

    public GearboxTypeController(GearboxTypeService gearboxTypeService) {
        this.gearboxTypeService = gearboxTypeService;
    }

//    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestParam(value = "nextPage", required = false) Integer nextPage, @RequestParam(value = "size", required = false) Integer size) {
        if (nextPage != null) {
            return new ResponseEntity<>(gearboxTypeService.findAll(nextPage, size), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(gearboxTypeService.findAll(), HttpStatus.OK);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getGearboxType(@PathVariable("id") Long id) {
        return new ResponseEntity<>(gearboxTypeService.findById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> createGearboxType(@RequestBody String name) {
        Integer flag = gearboxTypeService.createGearboxType(name);
        if (flag == 1) {
            return new ResponseEntity<>("Tip mjenjaca uspjesno kreiran.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Desila se nepoznata greska.", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editGearboxType(@RequestBody GearboxType gearboxType) {
        Integer flag = gearboxTypeService.editGearboxType(gearboxType);
        if (flag == 1) {
            return new ResponseEntity<>("Tip mjenjaca uspjesno izmjenjen.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Desila se nepoznata greska.", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteGearboxType(@RequestParam(value = "id") Long id) {
        Integer flag = gearboxTypeService.deleteById(id);
        if (flag == 1) {
            return new ResponseEntity<>("Tip mjenjaca uspjesno obrisan.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Desila se nepoznata greska.", HttpStatus.BAD_REQUEST);
        }
    }
}
