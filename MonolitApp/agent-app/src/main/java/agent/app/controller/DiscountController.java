package agent.app.controller;

import agent.app.converter.DiscountListConverter;
import agent.app.dto.discount.DiscountListCreateDTO;
import agent.app.dto.discount.DiscountListDTO;
import agent.app.service.intf.DiscountListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "/discount-list", produces = MediaType.APPLICATION_JSON_VALUE)
public class DiscountController {

    private final DiscountListService discountListService;

    public DiscountController(DiscountListService discountListService){
        this.discountListService = discountListService;
    }

    @PreAuthorize("hasAuthority('ROLE_AGENT')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAllDiscountList() {
        return new ResponseEntity<>(discountListService.findAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_AGENT')")
    @RequestMapping(value="/agent", method = RequestMethod.GET)
    public ResponseEntity<?> findAllDiscountListFromAgent(Principal p) {
        return new ResponseEntity<>(discountListService.findAllByAgentDTO(p.getName()), HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('ROLE_AGENT')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDiscountList(@RequestBody DiscountListCreateDTO discountListCreateDTO, Principal principal) {
        Integer flag = discountListService.createDiscount(discountListCreateDTO, principal.getName());
        if(flag == 1){
            return new ResponseEntity<>("Popust uspesno kreiran.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Desila se greska.", HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasAuthority('ROLE_AGENT')")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editDiscountList(@RequestBody DiscountListDTO discountListDTO) {
        Integer flag = discountListService.edit(DiscountListConverter.toDiscountListFromDiscountListDTO(discountListDTO));
        if (flag == 1) {
            return new ResponseEntity<>("Popust uspesno izmenjen.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Desila se nepoznata greska.", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_AGENT')")
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDiscountList(@RequestParam(value = "id") Long id) {
        Integer flag = discountListService.deleteById(id);
        if (flag == 1) {
            return new ResponseEntity<>("Popust uspesno obrisan.", HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("Desila se nepoznata greska.", HttpStatus.BAD_REQUEST);
        }
    }


}
