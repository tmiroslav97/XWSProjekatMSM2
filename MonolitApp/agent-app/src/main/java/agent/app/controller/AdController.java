package agent.app.controller;

import agent.app.converter.AdConverter;
import agent.app.converter.DateAPI;
import agent.app.dto.ad.AdCreateDTO;
import agent.app.model.CarManufacturer;
import agent.app.service.intf.AdService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/ad")
public class AdController {

    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    ObjectMapper objectMapper = new ObjectMapper();

    //    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAd(@PathVariable("id") Long id) {
        System.out.println("Service ad !!!!!");
        return new ResponseEntity<>(AdConverter.toAdDetailViewDTOFromAd(adService.findById(id)), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_AGENT')")
    @RequestMapping(value="/withImages", method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAdWithPhotos(@RequestParam(value = "photos0") MultipartFile photos0 ,
                                                @RequestParam(value = "photos1") MultipartFile photos1 ,
                                                @RequestParam(value = "photos2") MultipartFile photos2 ,
                                                @RequestParam(value = "photos3") MultipartFile photos3 ,
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
        }else if (flag == 2){
            return new ResponseEntity<>("Dozvoljeno je dodati samo 3 oglasa.", HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>("Desila se greska.", HttpStatus.BAD_REQUEST);
        }
    }

    //@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllPageAd(@RequestParam(value = "nextPage", required = false) Integer nextPage,
                                           @RequestParam(value = "size", required = false) Integer size) {

        if (nextPage != null) {
            System.out.println("ima 1 str");
            return new ResponseEntity<>(adService.findAll(nextPage, size), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(adService.findAll(), HttpStatus.OK);
        }

    }

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_AGENT')")
    @RequestMapping(value="/publisher",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllPageAdFromPublisher(@RequestParam(value = "nextPage", required = false) Integer nextPage,
                                                        @RequestParam(value = "size", required = false) Integer size,
                                                        Principal principal) {

        return new ResponseEntity<>(adService.findAll(nextPage, size, principal.getName()), HttpStatus.OK);
    }


    //@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_ADMIN')")
    @RequestMapping(path = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllSearch(@RequestParam(value = "nextPage", required = false) Integer nextPage,
                                           @RequestParam(value = "size", required = false) Integer size,
                                           @RequestParam(value = "location") String location,
                                           @RequestParam(value = "startDate") String startDate,
                                           @RequestParam(value = "endDate") String endDate) {


        DateTime startD = DateAPI.dateStringToDateTime(startDate);
        DateTime endD = DateAPI.dateStringToDateTime(endDate);
//        System.out.println(startD);
//        System.out.println(endD);
//        System.out.println(startD.toString(DateTimeFormat.forPattern("HH:mm dd-MM-yyyy")));
//        System.out.println(endD.toString(DateTimeFormat.forPattern("HH:mm dd-MM-yyyy")));
//        System.out.println(location);

        return new ResponseEntity<>(adService.findAllOrdinarySearch(nextPage, size, location, startD, endD), HttpStatus.OK);
    }
}
