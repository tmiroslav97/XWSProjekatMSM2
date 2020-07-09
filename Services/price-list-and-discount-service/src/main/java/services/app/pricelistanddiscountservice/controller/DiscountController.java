package services.app.pricelistanddiscountservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import services.app.pricelistanddiscountservice.converter.DiscountListConverter;
import services.app.pricelistanddiscountservice.dto.discount.DiscountListCreateDTO;
import services.app.pricelistanddiscountservice.dto.discount.DiscountListDTO;
import services.app.pricelistanddiscountservice.model.CustomPrincipal;
import services.app.pricelistanddiscountservice.service.intf.DiscountListService;

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
    public ResponseEntity<?> findAllDiscountListFromAgent() {
        return new ResponseEntity<>(discountListService.findAllByAgentDTO(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_AGENT')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDiscountList(@RequestBody DiscountListCreateDTO discountListCreateDTO) {
        Integer flag = discountListService.createDiscount(discountListCreateDTO);
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

    @PreAuthorize("hasAuthority('ROLE_AGENT')")
    @RequestMapping(value="/add-discount-to-ad/{discountId}/{adId}", method = RequestMethod.POST)
    public ResponseEntity<?> addDiscountListToAd(@PathVariable(value = "discountId") Long discountId,
                                                 @PathVariable(value = "adId") Long adId) {
        Integer flag = discountListService.addDiscountToAd(discountId, adId);
        if (flag == 1) {
            return new ResponseEntity<>("Popust uspesno dodat.", HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("Desila se greska.", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_AGENT')")
    @RequestMapping(value="/remove-discount-from-ad/{discountId}/{adId}", method = RequestMethod.POST)
    public ResponseEntity<?> removeDiscountFromAd(@PathVariable(value = "discountId") Long discountId,
                                                  @PathVariable(value = "adId") Long adId) {
        Integer flag = discountListService.deleteDiscountFromAd(discountId, adId);
        if (flag == 1) {
            return new ResponseEntity<>("Popust uklonjen.", HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("Desila se greska.", HttpStatus.BAD_REQUEST);
        }
    }
}
