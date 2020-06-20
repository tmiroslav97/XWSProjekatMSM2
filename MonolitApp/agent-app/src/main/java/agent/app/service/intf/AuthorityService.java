package agent.app.service.intf;

import agent.app.model.Authority;

import java.util.List;

public interface AuthorityService {
    List<Authority> findByName(String name);
}
