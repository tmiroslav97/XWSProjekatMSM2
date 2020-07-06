package services.app.adsearchservice.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.app.adsearchservice.converter.DateAPI;
import services.app.adsearchservice.dto.ad.AdPageContentDTO;
import services.app.adsearchservice.dto.ad.AdSynchronizeDTO;
import services.app.adsearchservice.service.intf.AdService;


@RestController
@RequestMapping(value = "/ad", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdController {

    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
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

    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public ResponseEntity<?> findAllSearch(@RequestParam(value = "nextPage", required = false) Integer nextPage,
                                           @RequestParam(value = "size", required = false) Integer size,
                                           @RequestParam(value = "location") String location,
                                           @RequestParam(value = "startDate") String startDate,
                                           @RequestParam(value = "endDate") String endDate,
                                           @RequestParam(value = "carManufacturer") String carManufacturer,
                                           @RequestParam(value = "carModel") String carModel,
                                           @RequestParam(value = "carType") String carType,
                                           @RequestParam(value = "mileage") String mileage,
                                           @RequestParam(value = "mileageKM") Float mileageKM,
                                           @RequestParam(value = "gearboxType") Float gearboxType,
                                           @RequestParam(value = "fuelType") String fuelType,
                                           @RequestParam(value = "childrenSeatNum") Integer childrenSeatNum,
                                           @RequestParam(value = "cdw") Boolean cdw,
                                           @RequestParam(value = "startPrice") Float startPrice,
                                           @RequestParam(value = "endPrice") Float endPrice,
                                           @RequestParam(value = "advancedSearch") Boolean advancedSearch
                                           ) {

        AdPageContentDTO ret = null;

        if(advancedSearch)
            ret = adService.findAllOrdinarySearch(nextPage, size, location, startDate, endDate);
        else
            ret = adService.findAllOrdinarySearch(nextPage, size, location, startDate, endDate);

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }


    @RequestMapping(value = "/synchronize", method = RequestMethod.POST)
    public Integer synchronizeDatabase(@RequestBody AdSynchronizeDTO adSynchronizeDTO) {
        System.out.println("SINHRONIZACIJA");
        System.out.println(adSynchronizeDTO);
        Integer rez = adService.syncData(adSynchronizeDTO);
        System.out.println("REZULTAT: " + rez);
        return 1;
    }

}
