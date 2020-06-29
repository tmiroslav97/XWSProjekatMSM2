package services.app.authenticationservice.service.intf;

import services.app.authenticationservice.dto.firm.FirmPageDTO;
import services.app.authenticationservice.dto.firm.FirmRegDTO;
import services.app.authenticationservice.model.Firm;

import java.util.List;

public interface FirmService {
    Firm findById(Long id);

    FirmPageDTO findAll(Integer page, Integer size);

    List<Firm> findAll();

    Integer registerFirm(FirmRegDTO firmRegDTO);

    Integer editFirm();

    Integer logicDeleteOrRevertById(Long id, Boolean status);

    Integer deleteById(Long id);

    void delete(Firm firm);

    Firm save(Firm firm);

    void syncData();
}
