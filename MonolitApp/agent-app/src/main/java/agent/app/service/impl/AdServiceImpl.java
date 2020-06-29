package agent.app.service.impl;


import agent.app.config.AppConfig;
import agent.app.config.RabbitMQConfiguration;
import agent.app.converter.AdConverter;
import agent.app.converter.CarCalendarTermConverter;
import agent.app.dto.ad.*;
import agent.app.dto.car.CarCalendarTermCreateDTO;
import agent.app.exception.ExistsException;
import agent.app.exception.NotFoundException;
import agent.app.model.*;
import agent.app.repository.AdRepository;
import agent.app.service.intf.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private CarService carService;

    @Autowired
    private PriceListService priceListService;

    @Autowired
    private DiscountListService discountListService;

    @Autowired
    private CarCalendarTermService carCalendarTermService;

    @Autowired
    private EndUserService endUserService;

    @Autowired
    private PublisherUserService publisherUserService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange directExchange;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Ad findById(Long id) {
        return adRepository.findById(id).orElseThrow(() -> new NotFoundException("Oglas ne postoji."));
    }

    @Override
    public List<Ad> findAll() {
        return adRepository.findAll();
    }

    @Override
    public Ad save(Ad ad) {
        if (ad.getId() != null) {
            if (adRepository.existsById(ad.getId())) {
                throw new ExistsException(String.format("Oglas vec postoji."));
            }
        }

        return adRepository.save(ad);
    }

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
    public Integer deleteById(Long id) {
        Ad ad = this.findById(id);
        this.delete(ad);
        return 1;
    }

    @Override
    public Integer createAd(AdCreateDTO adCreateDTO, String email) {
        Integer rez = endUserService.getAdLimitNum(email);
        if (rez == 4) {
            System.out.println("nije end userrrr");
        } else if (rez == 0) {
            System.out.println("end user");
            System.out.println("ne sme dodavati vise oglasa");
            return 2;
        }
        if (rez != 4) {
            Integer r = endUserService.reduceAdLimitNum(email);
            System.out.println("Limit num: " + r);
        }

        Ad ad = AdConverter.toCreateAdFromRequest(adCreateDTO);

        if (adCreateDTO.getPriceListCreateDTO().getId() == null) {
            //pravljenje novog cenovnika
            PriceList priceList = priceListService.createPriceList(adCreateDTO.getPriceListCreateDTO());
            ad.setPriceList(priceList);
        } else {
            //dodavanje vec postojeceg cenovnika
            PriceList priceList = priceListService.findById(adCreateDTO.getPriceListCreateDTO().getId());
            ad.setPriceList(priceList);
        }

        if (adCreateDTO.getCarCalendarTermCreateDTOList() != null) {
            List<CarCalendarTermCreateDTO> carCalendarTermCreateDTOList = adCreateDTO.getCarCalendarTermCreateDTOList();
            for (CarCalendarTermCreateDTO carCalendarTermCreateDTO : carCalendarTermCreateDTOList) {
                CarCalendarTerm carCalendarTerm = CarCalendarTermConverter.toCreateCarCalendarTermFromRequest(carCalendarTermCreateDTO);
                carCalendarTerm = carCalendarTermService.save(carCalendarTerm);
                ad.getCarCalendarTerms().add(carCalendarTerm);
            }
        }
        Car car = carService.createCar(adCreateDTO.getCarCreateDTO());
        ad.setCar(car);
        //token ako ima android uredjaj
        System.out.println("-------------------------------------------");
        if (adCreateDTO.getCarCreateDTO().getAndroidFlag() == true) {
            String token = this.generateToken();
            System.out.println("token: " + token);
            ad.getCar().setToken(token);
        }
        System.out.println("-------------------------------------------");

        PublisherUser publisherUser = publisherUserService.findByEmail(email);
        ad.setPublisherUser(publisherUser);
        ad = this.save(ad);

        //dodeljene slike
        if (adCreateDTO.getImagesDTO() != null) {
            List<String> slike = adCreateDTO.getImagesDTO();
            for (String slika : slike) {
                Image image = imageService.findByName(slika);
                if (image != null) {
                    System.out.println("slika: " + image.getName());
                    image.setAd(ad);
                    image = imageService.editImage(image);
                }
            }
        }
        for (CarCalendarTerm carCalendarTerm : ad.getCarCalendarTerms()) {
            carCalendarTerm.setAd(ad);
            carCalendarTerm = carCalendarTermService.editCarCalendarTerm(carCalendarTerm);
        }

        return 1;
    }

    @Override
    public String generateToken() {
        String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        int count = 20;
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        System.out.println("IZGENERISAN TOKEN: " + builder.toString());


        while (this.isExistToken(builder.toString()) != 1) {
            System.out.println("USLO U WHILE");
            builder = new StringBuilder();
            while (count-- != 0) {
                System.out.println("USLO U DRUGI WHILE");
                int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
                builder.append(ALPHA_NUMERIC_STRING.charAt(character));
            }
        }
        System.out.println("USPELO");
        return builder.toString();
    }

    @Override
    public Integer isExistToken(String token) {
        List<Ad> ads = this.findAll();
        if (ads != null) {
            for (Ad ad : ads) {
                if (ad.getCar().getAndroidFlag()) {
                    System.out.println("token: " + ad.getCar().getToken());
                    if (ad.getCar().getToken().equals(token)) {
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
    public void syncData() {

    }

    @Override
    public AdPageContentDTO findAllOrdinarySearch(Integer page, Integer size, String location, DateTime startDate, DateTime endDate) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<Ad> ads = adRepository.findByDeletedAndLocationAndCarCalendarTermsStartDateBeforeAndCarCalendarTermsEndDateAfter(false, location, startDate, endDate, pageable);
        List<AdPageDTO> ret = ads.stream().map(ad -> AdConverter.toCreateAdPageDTOFromAd(ad, appConfig.getPhotoDir())).collect(Collectors.toList());

        System.out.println(ret.size());
        AdPageContentDTO adPageContentDTO = AdPageContentDTO.builder()
                .totalPageCnt(ads.getTotalPages())
                .ads(ret)
                .build();

        return adPageContentDTO;
    }

    @Override
    public AdPageContentDTO findAll(Integer page, Integer size) {

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
        Page<Ad> ads = adRepository.findAllByDeleted(false, pageable);


        List<AdPageDTO> ret = ads.stream().map(ad -> AdConverter.toCreateAdPageDTOFromAd(ad, appConfig.getPhotoDir())).collect(Collectors.toList());
        AdPageContentDTO adPageContentDTO = AdPageContentDTO.builder()
                .totalPageCnt(ads.getTotalPages())
                .ads(ret)
                .build();


        return adPageContentDTO;
    }

    @Override
    public AdPageContentDTO findAll(Integer page, Integer size, String email) {
        User pu = userService.findByEmail(email);

        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<Ad> ads = adRepository.findAllByDeletedAndPublisherUserEmail(false, email, pageable);

        List<AdPageDTO> ret = ads.stream().map(ad -> AdConverter.toCreateAdPageDTOFromAd(ad, appConfig.getPhotoDir())).collect(Collectors.toList());
        System.out.println(ret.size());
        AdPageContentDTO adPageContentDTO = AdPageContentDTO.builder()
                .totalPageCnt(ads.getTotalPages())
                .ads(ret)
                .build();


        return adPageContentDTO;
    }

    @Override
    public List<Ad> findMyAds(String email) {
        return adRepository.findAllByDeletedAndPublisherUserEmail(false, email);
    }

    @Override
    public Set<Ad> findAllByIds(List<Long> adIds) {
        Set<Ad> ads = new HashSet<>();
        for (Long adId : adIds) {
            Ad ad = this.findById(adId);
            ads.add(ad);
        }
        return ads;
    }

    @Override
    public Integer syncData(String identifier, String email) {
        List<PriceList> pls = priceListService.findAllByPublisherUser(email);
        for(PriceList pl : pls){
            System.out.println(pl.getPricePerDay());
        }
        List<DiscountList> dls = discountListService.findAllByAgent(email);
        for(DiscountList dl : dls){
            System.out.println(dl.getDiscount());
        }

        List<Ad> ads = adRepository.findAllByDeletedAndPublisherUserEmail(false, email);
        for (Ad ad : ads){
            try {
                String adStr = objectMapper.writeValueAsString(ad);
                rabbitTemplate.convertAndSend(directExchange.getName(), RabbitMQConfiguration.AD_SYNC_QUEUE_NAME, adStr);
            }catch (JsonProcessingException exception){
                continue;
            }
        }
        return 1;
    }

}
