package services.app.pricelistanddiscountservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import services.app.pricelistanddiscountservice.converter.PriceListConverter;
import services.app.pricelistanddiscountservice.dto.pricelist.PriceListCreateDTO;
import services.app.pricelistanddiscountservice.model.CustomPrincipal;
import services.app.pricelistanddiscountservice.model.PriceList;
import services.app.pricelistanddiscountservice.service.intf.PriceListService;


@RestController
@RequestMapping(value = "/pricelist", produces = MediaType.APPLICATION_JSON_VALUE)
public class PriceListController {

    private final PriceListService priceListService;

    public PriceListController(PriceListService priceListService){
        this.priceListService = priceListService;
    }

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_AGENT')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAllPriceList() {
        return new ResponseEntity<>(priceListService.findAllListDTO(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_AGENT')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPriceList(@PathVariable("id") Long id) {
        return new ResponseEntity<>(PriceListConverter.toCreatePriceListCreateDTOFromPriceList(priceListService.findById(id)),
                HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_AGENT')")
    @RequestMapping(value = "/publisher", method = RequestMethod.GET)
    public ResponseEntity<?> getPriceListsFromPublishUser() {
        return new ResponseEntity<>(priceListService.findAllListDTOFromPublisher(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_AGENT')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createPriceList(@RequestBody PriceListCreateDTO priceListCreateDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();
        System.out.println(principal.getEmail());
//        priceListCreateDTO.setPublisherUsername(principal.getName());
        PriceList priceList = priceListService.createPriceList(priceListCreateDTO);
        return new ResponseEntity<>("Cenovnik uspesno kreiran.", HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_AGENT')")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editPriceList(@RequestBody PriceListCreateDTO priceListCreateDTO) {
        Integer flag = priceListService.editPriceList(PriceListConverter.toCreatePriceListFromRequest(priceListCreateDTO));
        if (flag == 1) {
            return new ResponseEntity<>("Cenovnik uspesno izmenjen.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Desila se nepoznata greska.", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_AGENT')")
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePriceList(@RequestParam(value = "id") Long id) {
        Integer flag = priceListService.deleteById(id);
        if (flag == 1) {
            return new ResponseEntity<>("Cenovnik uspesno obrisan.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Desila se nepoznata greska.", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/create-pricelist", method = RequestMethod.POST)
    public Long createPricelist(@RequestBody PriceListCreateDTO priceListCreateDTO) {
        return priceListService.createPriceList(priceListCreateDTO).getId();
    }

    @RequestMapping(value = "/find-pricelist/{id}", method = RequestMethod.GET)
    public Long findPriceList(@PathVariable Long id) {
        return priceListService.findById(id).getId();
    }

    @RequestMapping(value = "/find-price-per-day/{id}", method = RequestMethod.GET)
    public Float findPricePerDay(@PathVariable Long id) {
        return priceListService.findById(id).getPricePerDay();
    }



}
