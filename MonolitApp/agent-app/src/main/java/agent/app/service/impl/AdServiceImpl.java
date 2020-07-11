package agent.app.service.impl;


import agent.app.config.AppConfig;
import agent.app.config.RabbitMQConfiguration;
import agent.app.converter.*;
import agent.app.dto.ad.*;
import agent.app.dto.car.CarCalendarTermCreateDTO;
import agent.app.dto.sync.*;
import agent.app.exception.ExistsException;
import agent.app.exception.NotFoundException;
import agent.app.model.*;
import agent.app.repository.AdRepository;
import agent.app.service.intf.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private AgentService agentService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private RabbitTemplate rabbitTemplate;

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
    public Ad findByMainId(Long mainId) {
        return adRepository.findByMainId(mainId);
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
    @RabbitListener(queues = "#{autoDeleteRateAd.name}")
    public void rateAd(String msg) {
        try {
            AdRatingDTO adRatingDTO = objectMapper.readValue(msg, AdRatingDTO.class);
            Ad ad = this.findByMainId(adRatingDTO.getMainId());
            ad.setRatingNum(ad.getRatingNum() + adRatingDTO.getRating());
            ad.setRatingCnt(ad.getRatingCnt() + 1);
            ad = this.edit(ad);
        } catch (JsonProcessingException exception) {
            return;
        }
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
    public List<Ad> findAllFromPublisher(String publisherEmail) {
        PublisherUser publisherUser = publisherUserService.findByEmail(publisherEmail);
        List<Ad> ret = new ArrayList<>();
        List<Ad> ads = adRepository.findAll();
        for(Ad ad : ads){
            if(ad.getPublisherUser().getId() == publisherUser.getId()){
                ret.add(ad);
            }
        }
        return ret;
    }

    @Override
    public AdPageContentDTO findAll(Integer page, Integer size) {
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
    public AdDetailViewDTO getAdDetailView(Long ad_id) {
        return AdConverter.toAdDetailViewDTOFromAd(findById(ad_id), appConfig.getPhotoDir());
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
        Agent agent = agentService.findByEmail(email);
        if (agent.getIdentifier() != null) {
            return 5;
        }
        try {
            AuthSyncDTO authSyncDTO = AuthSyncDTO.builder()
                    .email(email)
                    .identifier(identifier)
                    .build();
            String authSyncDTOstr = objectMapper.writeValueAsString(authSyncDTO);
            Integer authorization = (Integer) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.AUTH_SYNC_QUEUE_NAME, authSyncDTOstr);
            if (authorization == 2) {
                return 2;
            } else if (authorization == 3) {
                return 3;
            } else if (authorization == 4) {
                return 5;
            }
        } catch (JsonProcessingException exception) {
            return 4;
        }
        agent.setIdentifier(identifier);
        agent = agentService.save(agent);
        List<PriceList> pls = priceListService.findAllByPublisherUser(email);
        for (PriceList pl : pls) {
            try {
                PriceListSyncDTO plSync = PriceListConverter.toPriceListSyncDTOFromPriceList(pl);
                plSync.setEmail(email);
                String plSyncStr = objectMapper.writeValueAsString(plSync);
                Long mainId = (Long) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.PL_SYNC_QUEUE_NAME, plSyncStr);
                if (mainId != null) {
                    pl.setMainId(mainId);
                    priceListService.savePriceList(pl);
                }
            } catch (JsonProcessingException exception) {
                continue;
            }
        }

        List<DiscountList> dls = discountListService.findAllByAgent(email);
        for (DiscountList dl : dls) {
            try {
                DiscountListSyncDTO dlSync = DiscountListConverter.toDiscountListSyncDTOFromDiscountList(dl);
                dlSync.setEmail(email);
                String dlSyncStr = objectMapper.writeValueAsString(dlSync);
                Long mainId = (Long) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.DL_SYNC_QUEUE_NAME, dlSyncStr);
                if (mainId != null) {
                    dl.setMainId(mainId);
                    discountListService.save(dl);
                }
            } catch (JsonProcessingException exception) {
                continue;
            }
        }

        List<Ad> ads = adRepository.findAllByDeletedAndPublisherUserEmail(false, email);
        for (Ad ad : ads) {
            try {
                AdSyncDTO adSync = AdConverter.toAdSyncDTOFromAd(ad);
                CarSyncDTO carSyncDTO = CarConverter.toCarSyncDTOFromCar(ad.getCar());
                List<CarCalendarTermSyncDTO> carCalendarTermSyncDTOS = CarCalendarTermConverter.fromEntityList(ad.getCarCalendarTerms().stream().collect(Collectors.toList()), CarCalendarTermConverter::toCarCalendarTermSyncDTOFromCarCalendarTerm);
                List<Long> discountIds = ad.getDiscountLists().stream().map(discountList -> discountList.getMainId()).collect(Collectors.toList());
                List<String> images = new ArrayList<>();
                for (Image img : ad.getImages()) {
                    if (!img.getName().equals(ad.getCoverPhoto())) {
                        images.add(imageService.findImageByNameBase64(img.getName()));
                    }
                }
                adSync.setPricePerDay(ad.getPriceList().getPricePerDay());
                adSync.setCoverPhoto(imageService.findImageByNameBase64(ad.getCoverPhoto()));
                adSync.setImages(images);
                adSync.setEmail(email);
                adSync.setCarSyncDTO(carSyncDTO);
                adSync.setCarCalendarTermSyncDTOList(carCalendarTermSyncDTOS);
                adSync.setPriceList(ad.getPriceList().getMainId());
                adSync.setDiscountList(discountIds);
                String adSyncStr = objectMapper.writeValueAsString(adSync);
                Long mainId = (Long) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.AD_SYNC_QUEUE_NAME, adSyncStr);
                if (mainId != null) {
                    ad.setMainId(mainId);
                    this.edit(ad);
                }
            } catch (JsonProcessingException exception) {
                continue;
            }
        }
        return 1;
    }

}
