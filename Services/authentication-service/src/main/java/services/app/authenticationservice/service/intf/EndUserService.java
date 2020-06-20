package services.app.authenticationservice.service.intf;


import services.app.authenticationservice.dto.EndUserPageDTO;
import services.app.authenticationservice.model.EndUser;

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

    EndUser save(EndUser endUser);

    Boolean existsByEmail(String email);

    EndUser findByEmail(String email);

    Integer getAdLimitNum(String email);

    Integer reduceAdLimitNum(String email);

}
