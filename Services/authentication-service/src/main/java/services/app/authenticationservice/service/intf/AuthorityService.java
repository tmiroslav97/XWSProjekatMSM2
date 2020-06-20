package services.app.authenticationservice.service.intf;


import services.app.authenticationservice.model.Authority;

import java.util.List;

public interface AuthorityService {
    List<Authority> findByName(String name);
}
