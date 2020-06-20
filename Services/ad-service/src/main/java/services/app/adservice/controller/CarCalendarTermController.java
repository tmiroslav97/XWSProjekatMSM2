package services.app.adservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import services.app.adservice.converter.AdConverter;
import services.app.adservice.dto.ad.AdCreateDTO;
import services.app.adservice.dto.car.CarCalendarTermCreateDTO;
import services.app.adservice.dto.car.CarCalendarTermDTO;
import services.app.adservice.service.intf.AdService;
import services.app.adservice.service.intf.CarCalendarTermService;

import java.util.List;

@RestController
@RequestMapping(value = "/calendar", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarCalendarTermController {

    private final CarCalendarTermService carCalendarTermService;

    public CarCalendarTermController(CarCalendarTermService carCalendarTermService) {
        this.carCalendarTermService = carCalendarTermService;
    }

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_AGENT')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAvailabilityFromAd(@PathVariable("id") Long id){
        System.out.println("Availability from ad " + id);
        List<CarCalendarTermCreateDTO> list = carCalendarTermService.findByAdId(id);
        if(list.isEmpty()){
            return new ResponseEntity<>("NEUSPESNO", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_AGENT')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addTermInCalendar(@RequestBody CarCalendarTermDTO carCalendarTermDTO) {
        System.out.println(carCalendarTermDTO);

        Integer flag = carCalendarTermService.addCarCalendarTerm(carCalendarTermDTO);
        if (flag == 1) {
            return new ResponseEntity<>(carCalendarTermDTO, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("Desila se greska.", HttpStatus.BAD_REQUEST);
        }
    }
}
