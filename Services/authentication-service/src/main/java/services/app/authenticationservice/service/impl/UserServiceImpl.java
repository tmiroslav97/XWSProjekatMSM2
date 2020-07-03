package services.app.authenticationservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import services.app.authenticationservice.config.LocalRabbitMQConfiguration;
import services.app.authenticationservice.converter.PublisherUserConverter;
import services.app.authenticationservice.dto.UserFLNameDTO;
import services.app.authenticationservice.exception.NotFoundException;
import services.app.authenticationservice.model.User;
import services.app.authenticationservice.repository.UserRepository;
import services.app.authenticationservice.service.intf.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with email '%s'.", email));
        } else {
            return user;
        }
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("Korisnik sa zadatim id- em nije pronadjen"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Integer editUser() {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @RabbitListener(queues = LocalRabbitMQConfiguration.USER_ID_QUEUE_NAME, containerFactory = "localFactory")
    public Long findUserIdByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return user.getId();
        } else {
            return null;
        }
    }

    @Override
    @RabbitListener(queues = LocalRabbitMQConfiguration.USER_FL_NAME_QUEUE_NAME, containerFactory = "localFactory")
    public String findUserFLNameById(Long id) {
        try {
            User user = this.findById(id);
            UserFLNameDTO userFLNameDTO = PublisherUserConverter.toUserFLNameDTOFromUser(user);
            return objectMapper.writeValueAsString(userFLNameDTO);
        } catch (JsonProcessingException exception) {
            return null;
        }
    }
}
