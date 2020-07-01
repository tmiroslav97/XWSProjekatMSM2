package agent.app.controller;

import agent.app.config.AppConfig;
import agent.app.converter.AdConverter;
import agent.app.converter.DateAPI;
import agent.app.dto.ad.AdCreateDTO;
import agent.app.dto.ad.AdRatingDTO;
import agent.app.service.intf.AdService;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequestMapping(value = "/ad")
public class AdController {

    private final AdService adService;
    private final AppConfig appConfig;

    public AdController(AdService adService, AppConfig appConfig) {
        this.adService = adService;
        this.appConfig = appConfig;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAd(@PathVariable("id") Long id) {
        return new ResponseEntity<>(AdConverter.toAdDetailViewDTOFromAd(adService.findById(id), appConfig.getPhotoDir()), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_AGENT')")
    @RequestMapping(value = "/withImages", method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAdWithPhotos(@RequestParam(value = "photos0") MultipartFile photos0,
                                                @RequestParam(value = "photos1") MultipartFile photos1,
                                                @RequestParam(value = "photos2") MultipartFile photos2,
                                                @RequestParam(value = "photos3") MultipartFile photos3,
                                                @RequestParam(value = "data") String data,
                                                Principal principal) {
        System.out.println("///////////////////////////////////////////////////");
        System.out.println(data);
        System.out.println(photos0.getOriginalFilename());
        System.out.println(photos1.getOriginalFilename());
        System.out.println(photos2.getOriginalFilename());
        System.out.println(photos3.getOriginalFilename());
//        for(MultipartFile mf : photos){
//            System.out.println("usao u forrr");
//            if(!mf.isEmpty()){
//                System.out.println(mf.getOriginalFilename());
//            }
//        };
        System.out.println("///////////////////////////////////////////////////");


        return new ResponseEntity<>("Oglas uspesno kreiran.", HttpStatus.CREATED);

    }


    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_AGENT')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAd(@RequestBody AdCreateDTO adCreateDTO, Principal principal) {
        System.out.println(adCreateDTO);

        adCreateDTO.getPriceListCreateDTO().setPublisherUsername(principal.getName());
        Integer flag = adService.createAd(adCreateDTO, principal.getName());

        if (flag == 1) {
            return new ResponseEntity<>("Oglas uspesno kreiran.", HttpStatus.CREATED);
        } else if (flag == 2) {
            return new ResponseEntity<>("Dozvoljeno je dodati samo 3 oglasa.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Desila se greska.", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllPageAd(@RequestParam(value = "nextPage", required = false) Integer nextPage,
                                           @RequestParam(value = "size", required = false) Integer size) {

        if (nextPage != null) {
            return new ResponseEntity<>(adService.findAll(nextPage, size), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(adService.findAll(), HttpStatus.OK);
        }

    }

    @RequestMapping(path = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllSearch(@RequestParam(value = "nextPage", required = false) Integer nextPage,
                                           @RequestParam(value = "size", required = false) Integer size,
                                           @RequestParam(value = "location") String location,
                                           @RequestParam(value = "startDate") String startDate,
                                           @RequestParam(value = "endDate") String endDate) {

        DateTime startD = DateAPI.DateTimeStringToDateTimeFromFronted(startDate);
        DateTime endD = DateAPI.DateTimeStringToDateTimeFromFronted(endDate);

        return new ResponseEntity<>(adService.findAllOrdinarySearch(nextPage, size, location, startD, endD), HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping(value = "/rating", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addRatingToAd(@RequestBody AdRatingDTO adRatingDTO) {

        Integer rez = adService.addRatingToAd(adRatingDTO);
        if (rez == 1) {
            return new ResponseEntity<>("Ocenili ste oglas.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Greska.", HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasAuthority('ROLE_AGENT')")
    @RequestMapping(value = "/sync", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> syncData(@RequestBody String identifier, Principal principal) {
        Integer flag = adService.syncData(identifier, principal.getName());
        if (flag == 1) {
            return new ResponseEntity<>("Sinhronizacija uspjesno obavljena.", HttpStatus.OK);
        } else if (flag == 2) {
            return new ResponseEntity<>("Niste registrovani na rent-a-car sistem.", HttpStatus.BAD_REQUEST);
        } else if (flag == 3) {
            return new ResponseEntity<>("Los identifikacioni kod za ret-a-car sistem.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Desila se nepoznata greska.", HttpStatus.BAD_REQUEST);
        }
    }
}
