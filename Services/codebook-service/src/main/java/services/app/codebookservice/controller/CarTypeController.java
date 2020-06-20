package services.app.codebookservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import services.app.codebookservice.model.CarType;
import services.app.codebookservice.service.intf.CarTypeService;

@RestController
@RequestMapping(value = "/car-type", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarTypeController {

    private final CarTypeService carTypeService;

    public CarTypeController(CarTypeService carTypeService) {
        this.carTypeService = carTypeService;
    }

    //@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestParam(value = "nextPage", required = false) Integer nextPage, @RequestParam(value = "size", required = false) Integer size) {
        if (nextPage != null) {
            return new ResponseEntity<>(carTypeService.findAll(nextPage, size), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(carTypeService.findAll(), HttpStatus.OK);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCarType(@PathVariable("id") Long id) {
        return new ResponseEntity<>(carTypeService.findById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> createCarType(@RequestBody String name) {
        Integer flag = carTypeService.createCarType(name);
        if (flag == 1) {
            return new ResponseEntity<>("Tip automobila uspjesno kreiran.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Desila se nepoznata greska.", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editCarType(@RequestBody CarType carType) {
        Integer flag = carTypeService.editCarType(carType);
        if (flag == 1) {
            return new ResponseEntity<>("Tip automobila uspjesno izmjenjen.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Desila se nepoznata greska.", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCarType(@RequestParam(value = "id") Long id) {
        Integer flag = carTypeService.deleteById(id);
        if (flag == 1) {
            return new ResponseEntity<>("Tip automobila uspjesno obrisan.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Desila se nepoznata greska.", HttpStatus.BAD_REQUEST);
        }
    }
}