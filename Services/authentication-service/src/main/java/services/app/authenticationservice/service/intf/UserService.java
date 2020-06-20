package services.app.authenticationservice.service.intf;


import services.app.authenticationservice.model.User;

import java.util.List;

public interface UserService {
    User findById(Long id);

    List<User> findAll();

    Boolean existsByEmail(String email);

    Integer editUser();

    User findByEmail(String email);

    User save(User user);
}
