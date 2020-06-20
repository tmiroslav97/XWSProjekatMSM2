package services.app.codebookservice.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import services.app.codebookservice.model.FuelType;
import services.app.codebookservice.service.intf.FuelTypeService;

@RestController
@RequestMapping(value = "/fuel-type", produces = MediaType.APPLICATION_JSON_VALUE)
public class FuelTypeController {

    private final FuelTypeService fuelTypeService;

    public FuelTypeController(FuelTypeService fuelTypeService) {
        this.fuelTypeService = fuelTypeService;
    }

    //@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestParam(value = "nextPage", required = false) Integer nextPage, @RequestParam(value = "size", required = false) Integer size) {
        if (nextPage != null) {
            return new ResponseEntity<>(fuelTypeService.findAll(nextPage, size), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(fuelTypeService.findAll(), HttpStatus.OK);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getFuelType(@PathVariable("id") Long id) {
        return new ResponseEntity<>(fuelTypeService.findById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> createFuelType(@RequestBody String name) {
        Integer flag = fuelTypeService.createFuelType(name);
        if (flag == 1) {
            return new ResponseEntity<>("Tip pogonskog goriva uspjesno kreiran.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Desila se nepoznata greska.", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editFuelType(@RequestBody FuelType fuelType) {
        Integer flag = fuelTypeService.editFuelType(fuelType);
        if (flag == 1) {
            return new ResponseEntity<>("Tip pogonskog goriva uspjesno izmjenjen.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Desila se nepoznata greska.", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteFuelType(@RequestParam(value = "id") Long id) {
        Integer flag = fuelTypeService.deleteById(id);
        if (flag == 1) {
            return new ResponseEntity<>("Tip pogonskog goriva uspjesno obrisan.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Desila se nepoznata greska.", HttpStatus.BAD_REQUEST);
        }
    }
}
