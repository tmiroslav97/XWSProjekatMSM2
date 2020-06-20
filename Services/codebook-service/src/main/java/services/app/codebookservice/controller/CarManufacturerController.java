package services.app.codebookservice.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import services.app.codebookservice.model.CarManufacturer;
import services.app.codebookservice.service.intf.CarManufacturerService;

@RestController
@RequestMapping(value = "/car-man", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarManufacturerController {

    private final CarManufacturerService carManufacturerService;

    public CarManufacturerController(CarManufacturerService carManufacturerService) {
        this.carManufacturerService = carManufacturerService;
    }

    //@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestParam(value = "nextPage", required = false) Integer nextPage, @RequestParam(value = "size", required = false) Integer size) {
        if (nextPage != null) {
            return new ResponseEntity<>(carManufacturerService.findAll(nextPage, size), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(carManufacturerService.findAll(), HttpStatus.OK);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCarManufacturer(@PathVariable("id") Long id) {
        return new ResponseEntity<>(carManufacturerService.findById(id), HttpStatus.OK);
    }

    //@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}/car-model", method = RequestMethod.GET)
    public ResponseEntity<?> getCarModelsByCarManufacturer(@PathVariable("id") Long id) {
        return new ResponseEntity<>(carManufacturerService.findCarModelsById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> createCarManufacturer(@RequestBody String name) {
        Integer flag = carManufacturerService.createCarManufacturer(name);
        if (flag == 1) {
            return new ResponseEntity<>("Proizvodjac automobila uspjesno kreiran.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Desila se nepoznata greska.", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editCarManufacturer(@RequestBody CarManufacturer carManufacturer) {
        Integer flag = carManufacturerService.editCarManufacturer(carManufacturer);
        if (flag == 1) {
            return new ResponseEntity<>("Proizvodjac automobila uspjesno izmjenjen.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Desila se nepoznata greska.", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCarManufacturer(@RequestParam(value = "id") Long id) {
        Integer flag = carManufacturerService.deleteById(id);
        if (flag == 1) {
            return new ResponseEntity<>("Proizvodjac automobila uspjesno obrisan.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Desila se nepoznata greska.", HttpStatus.BAD_REQUEST);
        }
    }
}
