package agent.app.service.impl;

import agent.app.converter.AdConverter;
import agent.app.dto.ad.AdPageContentDTO;
import agent.app.dto.ad.AdPageDTO;
import agent.app.dto.ad.AdStatisticsDTO;
import agent.app.exception.ExistsException;
import agent.app.exception.NotFoundException;
import agent.app.model.Ad;
import agent.app.model.PriceList;
import agent.app.model.PublisherUser;
import agent.app.model.User;
import agent.app.repository.PublisherUserRepository;
import agent.app.service.intf.AdService;
import agent.app.service.intf.PublisherUserService;
import agent.app.service.intf.UserService;
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
public class PublisherUserServiceImpl implements PublisherUserService {

    @Autowired
    private PublisherUserRepository publisherUserRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AdService adService;

    @Override
    public PublisherUser findById(Long id) {
        return publisherUserRepository.findById(id).orElseThrow(()-> new NotFoundException("Vlasnik oglasa ne postoji."));
    }

    @Override
    public List<PublisherUser> findAll() {
        return publisherUserRepository.findAll();
    }

    @Override
    public PublisherUser save(PublisherUser publisherUser) {
        if(publisherUserRepository.existsById(publisherUser.getId())){
            throw new ExistsException(String.format("Vlasnik oglasa vec postoji."));
        }
        return publisherUserRepository.save(publisherUser);
    }

    @Override
    public void delete(PublisherUser publisherUser) {
        publisherUserRepository.delete(publisherUser);
    }

    @Override
    public Integer deleteById(Long id) {
        PublisherUser publisherUser = findById(id);
        this.delete(publisherUser);
        return 1;
    }

    @Override
    public PublisherUser createPublisherUser(String publishUserUsername) {
        User user = userService.findByEmail(publishUserUsername);
        PublisherUser publisherUser = PublisherUser.publisherUserBuilder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .lastPasswordResetDate(user.getLastPasswordResetDate())
                .authorities(user.getAuthorities())
                .deleted(false)
                .ads(new HashSet<>())
                .priceLists(new HashSet<>())
                .comments(new HashSet<>())
                .reports(new HashSet<>())
                .build();
        return publisherUser;
    }

    @Override
    public PublisherUser editPublisherUser(PublisherUser publisherUser) {
        findById(publisherUser.getId());
        PublisherUser publisherUser1 = publisherUserRepository.save(publisherUser);

        return publisherUser1;
    }

    @Override
    public Boolean existsByEmail(String email) {
        return publisherUserRepository.existsByEmail(email);
    }

    @Override
    public PublisherUser findByEmail(String email) {
        return publisherUserRepository.findByEmail(email);
    }

    @Override
    public List<PriceList> findPriceListsFromPublishUser(String publishUserUsername) {
        PublisherUser publisherUser = this.findByEmail(publishUserUsername);
        List<PriceList> priceLists = new ArrayList<>(publisherUser.getPriceLists());
        return priceLists;
    }

    @Override
    public AdStatisticsDTO findBestAverageGrade(String email) {
        Ad adT = null;
        double averageGrade = 0.0;
        double max = 0.0;
        System.out.println("Average method");
        for (Ad ad : adService.findMyAds(email)) {
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
    public AdStatisticsDTO findMaxMileage(String email) {
        Ad adT = null;
        float max = 0;
        System.out.println("Average method za kilometrazu");
        for (Ad ad : adService.findMyAds(email)) {
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
    public AdStatisticsDTO findMaxComment(String email) {
        Ad adT = null;
        int max = 0;
        System.out.println("Average method za komentare");
        for (Ad ad : adService.findMyAds(email)) {
            if (ad.getRatingCnt() == 0) {

                ad.setRatingCnt(1L); //zbog djeljenja sa 0
                adT = ad;
            } else {
                if (ad.getComments().size() >= max) {
                    System.out.println("Komentari "  + ad.getComments().size());
                    max = ad.getComments().size();
                    adT = ad;
                }
            }

        }

        AdStatisticsDTO adPage = AdConverter.toCreateAdStatisticsDTOFromAd(adT);
        return adPage;
    }

    @Override
    public AdPageContentDTO findAll(Integer page, Integer size, String email) {
        return adService.findAll(page,size,email);
    }


}
