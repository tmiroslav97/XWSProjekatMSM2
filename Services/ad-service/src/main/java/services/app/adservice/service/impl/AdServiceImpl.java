package services.app.adservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import services.app.adservice.client.AuthenticationClient;
import services.app.adservice.config.AppConfig;
import services.app.adservice.config.RabbitMQConfiguration;
import services.app.adservice.converter.*;
import services.app.adservice.dto.AcceptReqestCalendarTermsDTO;
import services.app.adservice.dto.AgentFirmIdentificationDTO;
import services.app.adservice.dto.ad.*;
import services.app.adservice.dto.car.CarCalendarTermCreateDTO;
import services.app.adservice.dto.car.CarCalendarTermSynchronizeDTO;
import services.app.adservice.dto.car.StatisticCarDTO;
import services.app.adservice.dto.discountlist.DiscountInfoDTO;
import services.app.adservice.dto.image.ImagesSynchronizeDTO;
import services.app.adservice.dto.pricelist.PriceListDTO;
import services.app.adservice.dto.sync.AdSyncDTO;
import services.app.adservice.dto.user.UserFLNameDTO;
import services.app.adservice.exception.ExistsException;
import services.app.adservice.exception.NotFoundException;
import services.app.adservice.model.*;
import services.app.adservice.repository.AdRepository;
import services.app.adservice.service.intf.*;

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
    private CarCalendarTermService carCalendarTermService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private AuthenticationClient authenticationClient;

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DiscountListService discountListService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Ad findById(Long id) {
        return adRepository.findById(id).orElseThrow(() -> new NotFoundException("Oglas ne postoji."));
    }

    @Override
    public List<Ad> findAll() {
        return adRepository.findAll();
    }

    @Override
    public AdPageContentDTO findAll(Integer page, Integer size, String userId) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<Ad> ads = adRepository.findAllByDeletedAndPublisherUser(false, Long.valueOf(userId), pageable);

        List<AdPageDTO> ret = new ArrayList<>();
        for (Ad ad : ads) {
            AdPageDTO adPageDTO = AdConverter.toCreateAdPageDTOFromAd(ad, appConfig.getPhotoDir());
            try {
                String priceListStr = (String) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.PL_GET_QUEUE_NAME, ad.getPriceList());
                PriceListDTO priceListDTO = objectMapper.readValue(priceListStr, PriceListDTO.class);
                adPageDTO.setPrice(priceListDTO.getPricePerDay());
                ret.add(adPageDTO);
            } catch (JsonProcessingException exception) {
                ret.add(adPageDTO);
                continue;
            }
        }
        AdPageContentDTO adPageContentDTO = AdPageContentDTO.builder()
                .totalPageCnt(ads.getTotalPages())
                .ads(ret)
                .build();


        return adPageContentDTO;
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
    public Integer deleteById(Long id) {
        Ad ad = this.findById(id);
        this.delete(ad);
        return 1;
    }

    @Override
    public AdPageContentDTO findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<Ad> ads = adRepository.findAllByDeleted(false, pageable);
        System.out.println(ads.getSize());
        List<AdPageDTO> ret = ads.stream().map(ad -> AdConverter.toCreateAdPageDTOFromAd(ad, appConfig.getPhotoDir())).collect(Collectors.toList());
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
        if (rez == 4) {
            System.out.println("nije end userrrr");
        } else if (rez == 0) {
            System.out.println("end user");
            System.out.println("ne sme dodavati vise oglasa");
            return 2;
        }
        if (rez != 4) {
            Integer r = authenticationClient.reduceLimitNum(principal.getToken());
            System.out.println("Limit num : " + r);
        }
        Ad ad = AdConverter.toCreateAdFromRequest(adCreateDTO);

        //dodeljen cenovnik
        if (adCreateDTO.getPriceListCreateDTO().getId() == null) {
            //pravljenje novog cenovnika
            try {
                adCreateDTO.getPriceListCreateDTO().setPublisherUserId(Long.valueOf(principal.getUserId()));
                String priceListCreateDTOStr = objectMapper.writeValueAsString(adCreateDTO.getPriceListCreateDTO());
                Long priceListId = (Long) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.PL_NEW_EDIT_QUEUE_NAME, priceListCreateDTOStr);
                System.out.println("ID nove price liste: " + priceListId);
                ad.setPriceList(priceListId);
            } catch (JsonProcessingException exception) {
                ad.setPriceList(null);
            }
        } else {
            ad.setPriceList(adCreateDTO.getPriceListCreateDTO().getId());
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
        if (adCreateDTO.getCarCreateDTO().getAndroidFlag() == true) {
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
        if (adCreateDTO.getImagesDTO() != null) {
            List<String> slike = adCreateDTO.getImagesDTO();
            for (String slika : slike) {
                Image image = imageService.findByName(slika);
                if (image != null) {
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
//        System.out.println("SINHRONIZACIJA AD SERVICE");

        AdSynchronizeDTO adSynchronizeDTO = AdConverter.toAdSynchronizeDTOFromAd(ad);
        adSynchronizeDTO.setPricePerDay(adCreateDTO.getPriceListCreateDTO().getPricePerDay());
        //preko rabbitmq
        List<ImagesSynchronizeDTO> imagesSynchronizeDTOS = ImageConverter.fromEntityList(images, ImageConverter::toImagesSynchronizeDTOFromImage);
        adSynchronizeDTO.setImagesSynchronizeDTOS(imagesSynchronizeDTOS);
        try {
            String adSynchronizeDTOStr = objectMapper.writeValueAsString(adSynchronizeDTO);
            rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.AD_SEARCH_SYNC_QUEUE_NAME, adSynchronizeDTOStr);
        } catch (JsonProcessingException exception) {
            return 1;
        }
//        List<ImagesSynchronizeDTO> imagesSynchronizeDTOS = new ArrayList<>();
//        for (Image im : images) {
//            System.out.println("slika " + im.getName());
//            ImagesSynchronizeDTO imDTO = ImageConverter.toImagesSynchronizeDTOFromImage(im);
//            imagesSynchronizeDTOS.add(imDTO);
//        }
//        adSynchronizeDTO.setImagesSynchronizeDTOS(imagesSynchronizeDTOS);
//        adSearchClient.synchronizeDatabase(adSynchronizeDTO, principal.getUserId(), principal.getEmail(),
//                principal.getRoles(), principal.getToken());


        return 1;
    }

    @Override
    public List<StatisticCarDTO> getCarsWithBestRating(Long publisherId) {
        return null;
    }

    @Override
    @RabbitListener(queues = RabbitMQConfiguration.ACCEPT_REQUEST_QUEUE_NAME)
    public Boolean acceptCarCalendar(String msg) {
        AcceptReqestCalendarTermsDTO acceptReqestCalendarTermsDTO;
        UserFLNameDTO userFLNameDTO;
        String routingKey;
        try {
            acceptReqestCalendarTermsDTO = objectMapper.readValue(msg, AcceptReqestCalendarTermsDTO.class);
//            String userFLNameDTOStr = (String) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.USER_FL_NAME_QUEUE_NAME, acceptReqestCalendarTermsDTO.getPublisherUserId());
//            userFLNameDTO = objectMapper.readValue(userFLNameDTOStr, UserFLNameDTO.class);
//            routingKey = userFLNameDTO.getUserEmail().replace("@", ".")+".cct";
        } catch (JsonProcessingException exception) {
            return false;
        }
        int cnt = 0;
        for (AdRequestDTO adRequestDTO : acceptReqestCalendarTermsDTO.getAdRequestDTOS()) {
            Boolean flag = carCalendarTermService.canSplitCarCalendarTerm(adRequestDTO.getId(), DateAPI.DateTimeStringToDateTime(adRequestDTO.getStartDate()), DateAPI.DateTimeStringToDateTime(adRequestDTO.getEndDate()));
            if (flag) {
                cnt++;
            }
        }
        if (cnt == acceptReqestCalendarTermsDTO.getAdRequestDTOS().size()) {
            for (AdRequestDTO adRequestDTO : acceptReqestCalendarTermsDTO.getAdRequestDTOS()) {
                carCalendarTermService.splitCarCalendarTerm(adRequestDTO.getId(), DateAPI.DateTimeStringToDateTime(adRequestDTO.getStartDate()), DateAPI.DateTimeStringToDateTime(adRequestDTO.getEndDate()));
                List<CarCalendarTerm> carCalendarTerms = carCalendarTermService.findAllByAdId(adRequestDTO.getId());
                List<CarCalendarTermSynchronizeDTO> carCalendarTermSynchronizeDTOS = CarCalendarTermConverter.toListCarCalendarTermSyncDTOFromListCarCalendarTerm(new HashSet<>(carCalendarTerms));
                try {
                    String carCalendarTermSynchronizeDTOSStr = objectMapper.writeValueAsString(carCalendarTermSynchronizeDTOS);
                    rabbitTemplate.convertAndSend(RabbitMQConfiguration.CCT_SYNC_QUEUE_NAME, carCalendarTermSynchronizeDTOSStr);
//                    if (!userFLNameDTO.getLocal()) {
//                        rabbitTemplate.convertAndSend(RabbitMQConfiguration.AGENT_SYNC_QUEUE_NAME, routingKey, carCalendarTermSynchronizeDTOSStr);
//                    }
                } catch (JsonProcessingException exception) {
                    continue;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    @RabbitListener(queues = RabbitMQConfiguration.AD_SYNC_QUEUE_NAME)
    public Long syncAd(String msg) {
        try {
            AdSyncDTO adSyncDTO = objectMapper.readValue(msg, AdSyncDTO.class);
            Ad ad = AdConverter.toAdFromAdSyncDTO(adSyncDTO);
            Car car = CarConverter.toCarFromCarSyncDTO(adSyncDTO.getCarSyncDTO());
            car = carService.save(car);
            List<CarCalendarTerm> carCalendarTerms = CarCalendarTermConverter.toEntityList(adSyncDTO.getCarCalendarTermSyncDTOList(), CarCalendarTermConverter::toCarCalendarTermFromCarCalendarTermSyncDTO);
            for (CarCalendarTerm carCalendarTerm : carCalendarTerms) {
                carCalendarTerm = carCalendarTermService.save(carCalendarTerm);
            }
            Long userId = (Long) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.USER_ID_QUEUE_NAME, adSyncDTO.getEmail());
            Set<Image> images = new HashSet<>();
            for (String image : adSyncDTO.getImages()) {
                String imgStr = imageService.saveImageBase64(image);
                Image img = imageService.findByName(imgStr);
                images.add(img);
            }
            String coverPhotoStr = imageService.saveImageBase64(adSyncDTO.getCoverPhoto());
            Image coverPhotoImg = imageService.findByName(coverPhotoStr);
            images.add(coverPhotoImg);
            ad.setCoverPhoto(coverPhotoStr);
            ad.setImages(images);
            ad.setCar(car);
            ad.setCarCalendarTerms(new HashSet<>(carCalendarTerms));
            ad.setPublisherUser(userId);
            ad = this.save(ad);
            for (CarCalendarTerm carCalendarTerm : carCalendarTerms) {
                carCalendarTerm.setAd(ad);
                carCalendarTerm = carCalendarTermService.edit(carCalendarTerm);
            }
            for (Image img : ad.getImages()) {
                img.setAd(ad);
                img = imageService.editImage(img);
            }
            //sihronizacija sa search servisom
            AdSynchronizeDTO adSynchronizeDTO = AdConverter.toAdSynchronizeDTOFromAd(ad);
            adSynchronizeDTO.setPricePerDay(adSyncDTO.getPricePerDay());
            List<ImagesSynchronizeDTO> imagesSynchronizeDTOS = ImageConverter.fromEntityList(ad.getImages().stream().collect(Collectors.toList()), ImageConverter::toImagesSynchronizeDTOFromImage);
            adSynchronizeDTO.setImagesSynchronizeDTOS(imagesSynchronizeDTOS);
            String adSynchronizeDTOStr = objectMapper.writeValueAsString(adSynchronizeDTO);
            rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.AD_SEARCH_SYNC_QUEUE_NAME, adSynchronizeDTOStr);
            return ad.getId();
        } catch (JsonProcessingException exception) {
            return null;
        }
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
        try {
            String adRatingDTOStr = objectMapper.writeValueAsString(adRatingDTO);
            Integer flag = (Integer) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.RATE_AD_QUEUE_NAME, adRatingDTOStr);
            if (flag == 1) {
                Ad ad = this.findById(adRatingDTO.getMainId());
                ad.setRatingNum(ad.getRatingNum() + adRatingDTO.getRating());
                ad.setRatingCnt(ad.getRatingCnt() + 1);
                ad = this.edit(ad);
                String userFLNameStr = (String) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.USER_FL_NAME_QUEUE_NAME, ad.getPublisherUser());
                UserFLNameDTO userFLName = objectMapper.readValue(userFLNameStr, UserFLNameDTO.class);
                if (!userFLName.getLocal()) {
                    String routingKey = userFLName.getUserEmail().replace("@", ".") + ".rate";
                    rabbitTemplate.convertAndSend(RabbitMQConfiguration.AGENT_SYNC_QUEUE_NAME, routingKey, adRatingDTOStr);
                }
                return 1;
            } else {
                return flag;
            }
        } catch (JsonProcessingException exception) {
            return 3;
        }
    }

    @Override
    public AdDetailViewDTO getAdDetailView(Long ad_id) {

        AdDetailViewDTO adDV = AdConverter.toAdDetailViewDTOFromAd(findById(ad_id), appConfig.getPhotoDir());
        Ad ad = this.findById(ad_id);
        try {
            String priceListStr = (String) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.PL_GET_QUEUE_NAME, adDV.getPriceId());
            PriceListDTO priceListDTO = objectMapper.readValue(priceListStr, PriceListDTO.class);
            adDV.setPricePerDay(priceListDTO.getPricePerDay());
            adDV.setPricePerKm(priceListDTO.getPricePerKm());
            adDV.setPricePerKmCDW(priceListDTO.getPricePerKmCDW());

            List<DiscountInfoDTO> discountInfoDTOS = new ArrayList<>();
            for (DiscountList discountList : ad.getDiscountLists()) {
                String discountInfoDTOStr = (String) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.DISCOUNT_INFO_BY_ID_QUEUE_NAME, discountList.getId());
                DiscountInfoDTO discountInfoDTO = objectMapper.readValue(discountInfoDTOStr, DiscountInfoDTO.class);
                discountInfoDTOS.add(discountInfoDTO);
            }

            adDV.setDiscounts(discountInfoDTOS);
            String userFLNameDTOStr = (String) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.USER_FL_NAME_QUEUE_NAME, adDV.getPublisherUserId());
            UserFLNameDTO userFLNameDTO = objectMapper.readValue(userFLNameDTOStr, UserFLNameDTO.class);
            adDV.setPublisherUserFirstName(userFLNameDTO.getUserFirstName());
            adDV.setPublisherUserLastName(userFLNameDTO.getUserLastName());
        } catch (JsonProcessingException exception) {
            return adDV;
        }


        return adDV;

    }

    @Override
    public List<Long> findPricelistsFromAds() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();
        List<Long> pricelists = new ArrayList<>();
        List<Ad> ads = this.findAllFromPublisher(Long.parseLong(principal.getUserId()));
        for (Ad ad : ads) {
            if (!pricelists.contains(ad.getPriceList())) {
                pricelists.add(ad.getPriceList());
                System.out.println("cenovnik:    " + ad.getPriceList());
            }
        }
        return pricelists;
    }

    @Override
    public List<Ad> findAllFromPublisher(Long publisherId) {
        List<Ad> ret = new ArrayList<>();
        List<Ad> ads = adRepository.findAll();
        for (Ad ad : ads) {
            if (ad.getPublisherUser() == publisherId) {
                ret.add(ad);
            }
        }
        return ret;
    }

    @Override
    public Integer reversePricelist(ReversePricelistDTO reversePricelistDTO) {
        Ad ad = this.findById(reversePricelistDTO.getAdId());
        ad.setPriceList(reversePricelistDTO.getPricelistId());
        ad = this.edit(ad);
        return 1;
    }

    @RabbitListener(queues = RabbitMQConfiguration.AD_CAR_INFO_QUEUE_NAME)
    public String findAdCarInfoById(Long id) {
        Ad ad = this.findById(id);
        Car car = ad.getCar();
        AdCarInfoDTO adCarInfoDTO = new AdCarInfoDTO();
        try {
            String priceListStr = (String) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.PL_GET_QUEUE_NAME, ad.getPriceList());
            PriceListDTO priceListDTO = objectMapper.readValue(priceListStr, PriceListDTO.class);
            List<DiscountInfoDTO> discountInfoDTOS = new ArrayList<>();
            for (DiscountList discountList : ad.getDiscountLists()) {
                String discountInfoDTOStr = (String) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.DISCOUNT_INFO_BY_ID_QUEUE_NAME, discountList.getId());
                DiscountInfoDTO discountInfoDTO = objectMapper.readValue(discountInfoDTOStr, DiscountInfoDTO.class);
                discountInfoDTOS.add(discountInfoDTO);
            }
            adCarInfoDTO.setToken(car.getToken());
            adCarInfoDTO.setCdw(car.getCdw());
            adCarInfoDTO.setDistanceLimit(car.getDistanceLimit());
            adCarInfoDTO.setDistanceLimitFlag(car.getDistanceLimitFlag().toString());
            adCarInfoDTO.setPricePerDay(priceListDTO.getPricePerDay());
            adCarInfoDTO.setPricePerKm(priceListDTO.getPricePerKm());
            adCarInfoDTO.setPricePerKmCDW(priceListDTO.getPricePerKmCDW());
            adCarInfoDTO.setDiscountInfoDTOS(discountInfoDTOS);
            adCarInfoDTO.setMileage(car.getMileage());
            String adCarInfoDTOStr = objectMapper.writeValueAsString(adCarInfoDTO);
            return adCarInfoDTOStr;
        } catch (JsonProcessingException exception) {
            return null;
        }

    }

    @Override
    public List<Long> findAdsFromDiscount(Long discountId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();
        List<Long> adsList = new ArrayList<>();
        DiscountList discountList = discountListService.findById(discountId);
        for (Ad ad : discountList.getAds()) {
            adsList.add(ad.getId());
            System.out.println("oglas od popusta" + ad.getId());
        }
        return adsList;
    }

    @Override
    public Integer addDiscountToAd(Long discountId, Long adId) {
        Ad ad = this.findById(adId);
        DiscountList discountList = discountListService.findById(discountId);
        ad.getDiscountLists().add(discountList);
        ad = this.edit(ad);
        return 1;
    }

    @Override
    public Integer removeDiscountToAd(Long discountId, Long adId) {
        Ad ad = this.findById(adId);
        DiscountList discountList = discountListService.findById(discountId);
        ad.getDiscountLists().remove(discountList);
        ad = this.edit(ad);
        return 1;
    }

    @Override
    public Integer addDiscount(Long discountId) {
        return discountListService.addDiscount(discountId);
    }

    @Override
    public List<Ad> findMyAds(Long publisher_id) {

        return adRepository.findAllByDeletedAndPublisherUserId(false, publisher_id);
    }

    @Override
    public AdStatisticsDTO findBestAverageGrade(Long publisher_id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();
        System.out.println(principal.getEmail());
        Ad adT = null;
        double averageGrade = 0.0;
        double max = 0.0;
        System.out.println("Average method");
        for (Ad ad : findMyAds(publisher_id)) {
            if (ad.getRatingCnt() == 0) {
                averageGrade = 0.0;
                max = averageGrade;
                ad.setRatingCnt(1L); //zbog djeljenja sa 0
                adT = ad;
            } else {
                averageGrade = ad.getRatingNum() / ad.getRatingCnt();
                System.out.println("Izracunata ocjena: " + averageGrade);
                if (averageGrade > max) {
                    System.out.println("Average: " + averageGrade);
                    max = averageGrade;
                    adT = ad;
                }
            }
        }
        AdStatisticsDTO adPage = AdConverter.toCreateAdStatisticsDTOFromAd(adT);
        System.out.println("Konacna ocj " + adPage.getAverageGrade());
        return adPage;
    }

    @Override
    public AdStatisticsDTO findMaxMileage(Long publisher_id) {
        Ad adT = null;
        float max = 0;
        System.out.println("Average method za kilometrazu");
        for (Ad ad : findMyAds(publisher_id)) {
            if (ad.getRatingCnt() == 0) {

                ad.setRatingCnt(1L); //zbog djeljenja sa 0
                adT = ad;
            } else {
                if (ad.getCar().getMileage() > max) {
                    System.out.println("Max km: " + ad.getCar().getMileage());
                    max = ad.getCar().getMileage();
                    adT = ad;
                }
            }

        }

        AdStatisticsDTO adPage = AdConverter.toCreateAdStatisticsDTOFromAd(adT);
        return adPage;
    }

    @Override
    public AdStatisticsDTO findMaxComment(Long publisher_id) {
        Ad adT = null;
        int max = 0;
        System.out.println("Average method za komentare");
        for (Ad ad : findMyAds(publisher_id)) {
            if (ad.getRatingCnt() == 0) {

                ad.setRatingCnt(1L); //zbog djeljenja sa 0
                adT = ad;
            } else {
                for (Comment comment : ad.getComments()) {
                    if (comment.getApproved()) {
                        if (ad.getComments().size() >= max) {
                            System.out.println("Komentari " + ad.getComments().size());
                            max = ad.getComments().size();
                            adT = ad;
                        }
                    }
                }

            }

        }

        AdStatisticsDTO adPage = AdConverter.toCreateAdStatisticsDTOFromAd(adT);
        return adPage;
    }
    public Integer deleteDiscount(Long discountId) {
        DiscountList dl = discountListService.findById(discountId);
        List<Ad> ads = this.findAll();
        Boolean flag = false;
        for (Ad ad : ads) {
            if (!ad.getDeleted()) {
                if (ad.getDiscountLists().contains(dl)) {
                    flag = true;
                }
            }
        }
        if (flag == false) {
            discountListService.deleteDiscount(discountId);
            return 1;
        }
        return 2;
    }

    @Override
    @RabbitListener(queues = RabbitMQConfiguration.ADD_DISCOUNT_QUEUE_NAME)
    public void addDiscountRabbit(Long discountId) {
        Integer i = this.addDiscount(discountId);
    }

    @Override
    @RabbitListener(queues = RabbitMQConfiguration.DELETE_DISCOUNT_QUEUE_NAME)
    public void deleteDiscountRabbit(Long discountId) {
        Integer i = this.deleteDiscount(discountId);

    }

    @Override
    @RabbitListener(queues = RabbitMQConfiguration.ADD_DISCOUNT_TO_AD_QUEUE_NAME)
    public void addDiscountToAdRabbit(String string) {
        DiscountAndAdDTO discountAndAdDTO = null;
        try {
            discountAndAdDTO = objectMapper.readValue(string, DiscountAndAdDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Integer i = this.addDiscountToAd(discountAndAdDTO.getMainIdDiscount(), discountAndAdDTO.getMainIdAd());
    }

    @Override
    @RabbitListener(queues = RabbitMQConfiguration.DELETE_DISCOUNT_FROM_AD_QUEUE_NAME)
    public void deleteDiscountFromAdRabbit(String string) {
        DiscountAndAdDTO discountAndAdDTO = null;
        try {
            discountAndAdDTO = objectMapper.readValue(string, DiscountAndAdDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Integer i = this.removeDiscountToAd(discountAndAdDTO.getMainIdDiscount(), discountAndAdDTO.getMainIdAd());
    }

    @Override
    public Long authAgent(String email, String identifier) {
        AgentFirmIdentificationDTO agentFirmIdentificationDTO = AgentFirmIdentificationDTO.builder()
                .email(email)
                .identifier(identifier)
                .build();
        try {
            String agentFirmIdentificationDTOStr = objectMapper.writeValueAsString(agentFirmIdentificationDTO);
            Long publisherUserId = (Long) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.AGENT_ID_BY_EMAIL_ID_QUEUE_NAME, agentFirmIdentificationDTOStr);
            return publisherUserId;
        } catch (JsonProcessingException exception) {
            return null;
        }
    }

    @Override
    public Long createAdFromAgent(AdSync adSync) {
        try {
            Ad ad = AdConverter.toAdFromAdSync(adSync);
            Car car = CarConverter.toCarFromCarSync(adSync.getCarSyncDTO());
            car = carService.save(car);
            List<CarCalendarTerm> carCalendarTerms = CarCalendarTermConverter.toEntityList(adSync.getCarCalendarTermSyncDTOList(), CarCalendarTermConverter::toCarCalendarTermFromCarCalendarTermSync);
            for (CarCalendarTerm carCalendarTerm : carCalendarTerms) {
                carCalendarTerm = carCalendarTermService.save(carCalendarTerm);
            }
            Long userId = (Long) rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.USER_ID_QUEUE_NAME, adSync.getEmail());
            Set<Image> images = new HashSet<>();
            for (String image : adSync.getImages()) {
                String imgStr = imageService.saveImageBase64(image);
                Image img = imageService.findByName(imgStr);
                images.add(img);
            }
            String coverPhotoStr = imageService.saveImageBase64(adSync.getCoverPhoto());
            Image coverPhotoImg = imageService.findByName(coverPhotoStr);
            images.add(coverPhotoImg);
            ad.setCoverPhoto(coverPhotoStr);
            ad.setImages(images);
            ad.setCar(car);
            ad.setCarCalendarTerms(new HashSet<>(carCalendarTerms));
            ad.setPublisherUser(userId);
            ad = this.save(ad);
            for (CarCalendarTerm carCalendarTerm : carCalendarTerms) {
                carCalendarTerm.setAd(ad);
                carCalendarTerm = carCalendarTermService.edit(carCalendarTerm);
            }
            for (Image img : ad.getImages()) {
                img.setAd(ad);
                img = imageService.editImage(img);
            }
            //sihronizacija sa search servisom
            AdSynchronizeDTO adSynchronizeDTO = AdConverter.toAdSynchronizeDTOFromAd(ad);
            adSynchronizeDTO.setPricePerDay(adSync.getPricePerDay());
            List<ImagesSynchronizeDTO> imagesSynchronizeDTOS = ImageConverter.fromEntityList(ad.getImages().stream().collect(Collectors.toList()), ImageConverter::toImagesSynchronizeDTOFromImage);
            adSynchronizeDTO.setImagesSynchronizeDTOS(imagesSynchronizeDTOS);
            String adSynchronizeDTOStr = objectMapper.writeValueAsString(adSynchronizeDTO);
            rabbitTemplate.convertSendAndReceive(RabbitMQConfiguration.AD_SEARCH_SYNC_QUEUE_NAME, adSynchronizeDTOStr);
            return ad.getId();

        }catch (JsonProcessingException exception) {
            return null;
        }
    }

}
