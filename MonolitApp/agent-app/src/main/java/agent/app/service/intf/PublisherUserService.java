package agent.app.service.intf;

import agent.app.dto.ad.AdPageContentDTO;
import agent.app.dto.ad.AdStatisticsDTO;
import agent.app.dto.car.CarCalendarTermCreateDTO;
import agent.app.model.CarCalendarTerm;
import agent.app.model.PriceList;
import agent.app.model.PublisherUser;
import agent.app.model.User;

import java.util.List;


public interface PublisherUserService {
    PublisherUser findById(Long id);
    List<PublisherUser> findAll();
    PublisherUser save(PublisherUser publisherUser);
    void delete(PublisherUser publisherUser);
    Integer deleteById(Long id);
    PublisherUser createPublisherUser(String publishUserUsername);
    PublisherUser editPublisherUser(PublisherUser publisherUser);
    Boolean existsByEmail(String email);
    PublisherUser findByEmail(String email);
    List<PriceList> findPriceListsFromPublishUser(String publishUserUsername);
    AdStatisticsDTO findBestAverageGrade(String email);
    AdStatisticsDTO findMaxMileage(String email);
    AdPageContentDTO findAll(Integer page, Integer size, String email);

}
