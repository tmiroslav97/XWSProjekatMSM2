package services.app.adservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import services.app.adservice.client.AdSearchClient;
import services.app.adservice.client.AuthenticationClient;
import services.app.adservice.client.PricelistAndDiscountClient;
import services.app.adservice.converter.AdConverter;
import services.app.adservice.converter.CarCalendarTermConverter;

import services.app.adservice.converter.ImageConverter;
import services.app.adservice.dto.ad.*;
import services.app.adservice.dto.car.StatisticCarDTO;
import services.app.adservice.dto.car.CarCalendarTermCreateDTO;
import services.app.adservice.dto.image.ImagesSynchronizeDTO;

import services.app.adservice.dto.ad.*;

import services.app.adservice.dto.car.StatisticCarDTO;
import services.app.adservice.dto.ad.AdCreateDTO;
import services.app.adservice.dto.ad.AdPageContentDTO;
import services.app.adservice.dto.ad.AdPageDTO;
import services.app.adservice.dto.ad.AdRatingDTO;
import services.app.adservice.dto.car.CarCalendarTermCreateDTO;

import services.app.adservice.dto.user.PublisherUserDTO;


import services.app.adservice.exception.ExistsException;
import services.app.adservice.exception.NotFoundException;
import services.app.adservice.model.*;
import services.app.adservice.repository.AdRepository;
import services.app.adservice.service.intf.AdService;
import services.app.adservice.service.intf.CarCalendarTermService;
import services.app.adservice.service.intf.CarService;
import services.app.adservice.service.intf.ImageService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private CarService carService;
    
    @Autowired
    private CarCalendarTermService carCalendarTermService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private PricelistAndDiscountClient pricelistAndDiscountClient;

    @Autowired
    private AuthenticationClient authenticationClient;

    @Autowired
    private AdSearchClient adSearchClient;

    @Override
    public Ad findById(Long id) {
        return adRepository.findById(id).orElseThrow(()-> new NotFoundException("Oglas ne postoi."));
    }

    @Override
    public List<Ad> findAll() {
        return adRepository.findAll();
    }

    @Override
    public AdPageContentDTO findAll(Integer page, Integer size, String userId) {
//        User pu = userService.findByEmail(email);

        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<Ad> ads = adRepository.findAllByDeletedAndPublisherUser(false, Long.valueOf(userId), pageable);

        List<AdPageDTO> ret = ads.stream().map(AdConverter::toCreateAdPageDTOFromAd).collect(Collectors.toList());
        System.out.println(ret.size());
        AdPageContentDTO adPageContentDTO = AdPageContentDTO.builder()
                .totalPageCnt(ads.getTotalPages())
                .ads(ret)
                .build();


        return adPageContentDTO;
    }

    @Override
    public Ad save(Ad ad) {
        if(ad.getId() != null){
            if(adRepository.existsById(ad.getId())){
                throw new ExistsException(String.format("Oglas vec postoji."));
            }
        }

        return adRepository.save(ad);    }

    @Override
    public Ad edit(Ad ad) {
        this.findById(ad.getId());
        return adRepository.save(ad);
    }

    @Override
    public void delete(Ad ad) {
        adRepository.delete(ad);

    }

    @Override
    public Integer deleteById(Long id) {
        Ad ad = this.findById(id);
        this.delete(ad);
        return 1;
    }

    @Override
    public AdPageContentDTO findAll (Integer page, Integer size) {

//        Pageable pageable;
//        if(sort.equals("-")){
//            pageable = PageRequest.of(page, size);
//        }else{
//            String par[] = sort.split(" ");
//            if(par[1].equals("opadajuce")) {
//                pageable = PageRequest.of(page, size, Sort.by(par[0]).descending());
//            }else{
//                pageable = PageRequest.of(page, size, Sort.by(par[0]).ascending());
//            }
//
//        }

        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<Ad> ads =  adRepository.findAllByDeleted(false, pageable);
        System.out.println(ads.getSize());
        List<AdPageDTO> ret = ads.stream().map(AdConverter::toCreateAdPageDTOFromAd).collect(Collectors.toList());
        AdPageContentDTO adPageContentDTO = AdPageContentDTO.builder()
                .totalPageCnt(ads.getTotalPages())
                .ads(ret)
                .build();

        System.out.println(adPageContentDTO);

        return adPageContentDTO;
    }

    @Override
    public Integer createAd(AdCreateDTO adCreateDTO) {
        System.out.println("***************************************************************");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();

        //postavljen publish user
        adCreateDTO.getPriceListCreateDTO().setPublisherUsername(principal.getEmail());

        //provereno za end user-a koliko oglasa moze da dodaje
        Integer rez = authenticationClient.getAdLimitNum(principal.getToken());
        if(rez == 4){
            System.out.println("nije end userrrr");
        }else if(rez == 0){
            System.out.println("end user");
            System.out.println("ne sme dodavati vise oglasa");
            return 2;
        }
        if(rez != 4){
            Integer r = authenticationClient.reduceLimitNum(principal.getToken());
            System.out.println("Limit num : "+ r);
        }
        Ad ad = AdConverter.toCreateAdFromRequest(adCreateDTO);

        //dodeljen cenovnik
        if (adCreateDTO.getPriceListCreateDTO().getId() == null) {
            //pravljenje novog cenovnika
            Long priceList = pricelistAndDiscountClient.createPricelist(adCreateDTO.getPriceListCreateDTO(),principal.getUserId(), principal.getEmail(),
                    principal.getRoles(), principal.getToken());
            System.out.println("ID nove price liste: "+ priceList);
            ad.setPriceList(priceList);
        } else {
            //dodavanje vec postojeceg cenovnika
            Long priceList = pricelistAndDiscountClient.findPriceList(adCreateDTO.getPriceListCreateDTO().getId(), principal.getUserId(), principal.getEmail(),
                    principal.getRoles(), principal.getToken());
            System.out.println("ID stare price liste od tog publisher-a: "+ priceList);
            ad.setPriceList(priceList);
        }
        //dodeljeni termini
        if (adCreateDTO.getCarCalendarTermCreateDTOList() != null) {
            List<CarCalendarTermCreateDTO> carCalendarTermCreateDTOList = adCreateDTO.getCarCalendarTermCreateDTOList();
            for (CarCalendarTermCreateDTO carCalendarTermCreateDTO : carCalendarTermCreateDTOList) {
                CarCalendarTerm carCalendarTerm = CarCalendarTermConverter.toCreateCarCalendarTermFromRequest(carCalendarTermCreateDTO);
                carCalendarTerm = carCalendarTermService.save(carCalendarTerm);
                ad.getCarCalendarTerms().add(carCalendarTerm);
            }
        }
        //kreirana klasa za automobil
        Car car = carService.createCar(adCreateDTO.getCarCreateDTO());
        ad.setCar(car);
        //token ako ima android uredjaj
        System.out.println("-------------------------------------------");
        if(adCreateDTO.getCarCreateDTO().getAndroidFlag() == true){
            String token = this.generateToken();
            System.out.println("token: " + token);
            ad.getCar().setToken(token);
        }
        System.out.println("-------------------------------------------");
        //dodeljen publisherUser za oglas
        Long publisherUser = authenticationClient.findPublishUserByEmail(principal.getToken());
        ad.setPublisherUser(publisherUser);

        ad = this.save(ad);
        //dodeljene slike
        List<Image> images = new ArrayList<>();
        if(adCreateDTO.getImagesDTO() != null){
            List<String> slike = adCreateDTO.getImagesDTO();
            for(String slika : slike){
                Image image = imageService.findByName(slika);
                if(image != null){
                    System.out.println("slika: " + image.getName());
                    image.setAd(ad);
                    image = imageService.editImage(image);
                    images.add(image);
//                    ad.getImages().add(image);
//                    ad = this.edit(ad);
                }
            }
        }

        for (CarCalendarTerm carCalendarTerm : ad.getCarCalendarTerms()) {
                carCalendarTerm.setAd(ad);
                carCalendarTerm = carCalendarTermService.editCarCalendarTerm(carCalendarTerm);
        }
        System.out.println("***************************************************************");
        System.out.println("SINHRONIZACIJA AD SERVICE");

        AdSynchronizeDTO adSynchronizeDTO = AdConverter.toAdSynchronizeDTOFromAd(ad);
        adSynchronizeDTO.setPricePerDay(adCreateDTO.getPriceListCreateDTO().getPricePerDay());
        List<ImagesSynchronizeDTO> imagesSynchronizeDTOS = new ArrayList<>();
        for(Image im : images){
            System.out.println("slika "+ im.getName());
            ImagesSynchronizeDTO imDTO = ImageConverter.toImagesSynchronizeDTOFromImage(im);
            imagesSynchronizeDTOS.add(imDTO);
        }
        adSynchronizeDTO.setImagesSynchronizeDTOS(imagesSynchronizeDTOS);
        adSearchClient.synchronizeDatabase(adSynchronizeDTO, principal.getUserId(), principal.getEmail(),
                                            principal.getRoles(), principal.getToken());

        System.out.println("***************************************************************");


        return 1;
    }

    @Override
    public List<StatisticCarDTO> getCarsWithBestRating(Long publisherId) {
        return null;
    }

    @Override
    public void syncData() {

    }

    @Override
    public void setRating(AdRatingDTO ad) {

    }

    @Override
    public void logicalDeleteOrRevertAds(List<Ad> ads, Boolean status) {
        for (Ad ad : ads) {
            this.logicalDeleteOrRevert(ad, status);
        }
    }

    @Override
    public void logicalDeleteOrRevert(Ad ad, Boolean status) {
        ad.setDeleted(status);
        this.save(ad);
    }

    @Override
    public String generateToken() {
        String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        int count = 20;
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        System.out.println("IZGENERISAN TOKEN: " + builder.toString());


        while(this.isExistToken(builder.toString()) != 1){
            System.out.println("USLO U WHILE");
            builder = new StringBuilder();
            while (count-- != 0) {
                System.out.println("USLO U DRUGI WHILE");
                int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
                builder.append(ALPHA_NUMERIC_STRING.charAt(character));
            }
        }
        System.out.println("USPELO");
        return builder.toString();
    }

    @Override
    public Integer isExistToken(String token) {
        List<Ad> ads = this.findAll();
        if(ads != null){
            for(Ad ad : ads){
                if(ad.getCar().getAndroidFlag()){
                    System.out.println("token: " + ad.getCar().getToken() );
                    if(ad.getCar().getToken().equals(token)){
                        return 2;
                    }
                }
            }
        }
        return 1;
    }

    @Override
    public Integer addRatingToAd(AdRatingDTO adRatingDTO) {
        Ad ad = this.findById(adRatingDTO.getAdId());
        ad.setRatingNum(ad.getRatingNum() + adRatingDTO.getRating());
        ad.setRatingCnt(ad.getRatingCnt() + 1);
        ad = this.edit(ad);
        return 1;

    }
    @Override
    public AdDetailViewDTO getAdDetailView(Long ad_id) {

        AdDetailViewDTO adDV = AdConverter.toAdDetailViewDTOFromAd(findById(ad_id));
        System.out.println("GET DETAIL VIEW ");


        Float priceList = pricelistAndDiscountClient.findPricePerDay(adDV.getPriceId());
        System.out.println("Cijena : "+ priceList);
        adDV.setPricePerDay(priceList);

        System.out.println(adDV.getPublisherUserId());
        PublisherUserDTO puDTO = authenticationClient.findPublishUserById(adDV.getPublisherUserId());
        adDV.setPublisherUserFirstName(puDTO.getPublisherUserFirstName());
        adDV.setPublisherUserLastName(puDTO.getPublisherUserLastName());
        System.out.println(adDV);
        System.out.println(puDTO.getPublisherUserId());
        System.out.println(puDTO.getPublisherUserFirstName());
        System.out.println(puDTO.getPublisherUserLastName());


        return  adDV;

    }

}
