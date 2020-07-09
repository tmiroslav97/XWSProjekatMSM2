package services.app.adservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import services.app.adservice.dto.ad.AdCreateDTO;
import services.app.adservice.dto.ad.AdRatingDTO;
import services.app.adservice.dto.ad.ReversePricelistDTO;
import services.app.adservice.model.CustomPrincipal;
import services.app.adservice.service.intf.AdService;

import java.security.Principal;


@RestController
@RequestMapping(value = "/ad", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdController {

    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAd(@PathVariable("id") Long id) {
        return new ResponseEntity<>(adService.getAdDetailView(id), HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_AGENT')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAd(@RequestBody AdCreateDTO adCreateDTO) {
        System.out.println(adCreateDTO);
        Integer flag = adService.createAd(adCreateDTO);
        if (flag == 1) {
            return new ResponseEntity<>("Oglas uspesno kreiran.", HttpStatus.CREATED);
        } else if (flag == 2) {
            return new ResponseEntity<>("Dozvoljeno je dodati samo 3 oglasa.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Desila se greska.", HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAllPageAd(@RequestParam(value = "nextPage", required = false) Integer nextPage,
                                           @RequestParam(value = "size", required = false) Integer size) {

        if (nextPage != null) {
            return new ResponseEntity<>(adService.findAll(nextPage, size), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(adService.findAll(), HttpStatus.OK);
        }

    }

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_AGENT')")
    @RequestMapping(value = "/publisher", method = RequestMethod.GET)
    public ResponseEntity<?> findAllPageAdFromPublisher(@RequestParam(value = "nextPage", required = false) Integer nextPage,
                                                        @RequestParam(value = "size", required = false) Integer size) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();
        System.out.println(principal.getUserId());
        System.out.println(nextPage + " dfas " + size);
        return new ResponseEntity<>(adService.findAll(nextPage, size, principal.getUserId()), HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping(value = "/rating", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addRatingToAd(@RequestBody AdRatingDTO adRatingDTO) {

        Integer rez = adService.addRatingToAd(adRatingDTO);
        if (rez == 1) {
            return new ResponseEntity<>("Uspjesno ste ocijenili oglas.", HttpStatus.OK);
        } else if (rez == 2) {
            return new ResponseEntity<>("Vec ste ocijenili oglas.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Desila se nepoznata greska.", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/pricelists", method = RequestMethod.GET)
    public ResponseEntity<?> getPricelistsFromAds() {
        return new ResponseEntity<>(adService.findPricelistsFromAds(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_AGENT')")
    @RequestMapping(value = "/reverse-pricelist", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> reversePricelist(@RequestBody ReversePricelistDTO reversePricelistDTO) {

        Integer rez = adService.reversePricelist(reversePricelistDTO);
        if (rez == 1) {
            return new ResponseEntity<>("Izmenili ste cenovnik oglasa.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Greska.", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/ads-from-discount", method = RequestMethod.GET)
    public ResponseEntity<?> getAdsFromDiscount(@RequestParam("discountId") Long discountId) {
        return new ResponseEntity<>(adService.findAdsFromDiscount(discountId), HttpStatus.OK);
    }
    @RequestMapping(value = "/add-discount", method = RequestMethod.GET)
    public ResponseEntity<?> addDiscount(@RequestParam("discountId") Long discountId) {
        return new ResponseEntity<>(adService.addDiscount(discountId), HttpStatus.OK);
    }

    @RequestMapping(value = "/add-discount-to-ad", method = RequestMethod.GET)
    public ResponseEntity<?> addDiscountToAd(@RequestParam("discountId") Long discountId, @RequestParam("adId") Long adId) {
        return new ResponseEntity<>(adService.addDiscountToAd(discountId, adId), HttpStatus.OK);
    }

    @RequestMapping(value = "/remove-discount-from-ad", method = RequestMethod.GET)
    public ResponseEntity<?> removeDiscountToAd(@RequestParam("discountId") Long discountId, @RequestParam("adId") Long adId) {
        return new ResponseEntity<>(adService.removeDiscountToAd(discountId, adId), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_AGENT')")
    @RequestMapping(value = "/best-average-grade", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findBestAverageGradeAd() {
        System.out.println("Best average grade");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();
        System.out.println(principal.getUserId());
        Long publisher_id = Long.parseLong(principal.getUserId());
        System.out.println(publisher_id);
        return new ResponseEntity<>(adService.findBestAverageGrade(publisher_id), HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('ROLE_AGENT')")
    @RequestMapping(value = "/max-mileage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findMaxMileageAd() {
        System.out.println("Max mileage contoller");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();
        System.out.println(principal.getUserId());
        Long publisher_id = Long.parseLong(principal.getUserId());
        System.out.println(publisher_id);
        return new ResponseEntity<>(adService.findMaxMileage(publisher_id), HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('ROLE_AGENT')")
    @RequestMapping(value = "/max-comments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findMaxCommentsAd() {
        System.out.println("Max comments contoller");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();
        System.out.println(principal.getUserId());
        Long publisher_id = Long.parseLong(principal.getUserId());
        System.out.println(publisher_id);
        return new ResponseEntity<>(adService.findMaxComment(publisher_id), HttpStatus.OK);

    }

}
