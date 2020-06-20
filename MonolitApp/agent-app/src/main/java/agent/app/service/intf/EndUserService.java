package agent.app.service.intf;

import agent.app.dto.enduser.EndUserPageDTO;
import agent.app.model.EndUser;

import java.util.List;

public interface EndUserService {
    EndUser findById(Long id);

    List<EndUser> findAll();

    EndUserPageDTO findAll(Integer page, Integer size);

    String blockOrUnblockById(Long id, Boolean status);

    String obligateOrUnobligateById(Long id, Boolean status);

    String logicDeleteOrRevertById(Long id, Boolean status);

    Integer deleteById(Long id);

    void delete(EndUser endUser);

    Boolean existsByEmail(String email);

    EndUser findByEmail(String email);

    EndUser save(EndUser endUser);

    Integer getAdLimitNum(String email);

    Integer reduceAdLimitNum(String email);
}
