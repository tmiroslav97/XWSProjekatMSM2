package agent.app.service.impl;

import agent.app.exception.ExistsException;
import agent.app.exception.NotFoundException;
import agent.app.model.PriceList;
import agent.app.model.PublisherUser;
import agent.app.model.User;
import agent.app.repository.PublisherUserRepository;
import agent.app.service.intf.PublisherUserService;
import agent.app.service.intf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PublisherUserServiceImpl implements PublisherUserService {

    @Autowired
    private PublisherUserRepository publisherUserRepository;

    @Autowired
    private UserService userService;

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
                .inbox(new HashSet<>())
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
}
