package services.app.authenticationservice.service.intf;

import services.app.authenticationservice.dto.FirmRegDTO;
import services.app.authenticationservice.model.Firm;

import java.util.List;

public interface FirmService {
    Firm findById(Long id);

    List<Firm> findAll();

    Integer registerFirm(FirmRegDTO firmRegDTO);

    Integer editFirm();

    Integer deleteById(Long id);

    void delete(Firm firm);

    Firm save(Firm firm);

    void syncData();
}
