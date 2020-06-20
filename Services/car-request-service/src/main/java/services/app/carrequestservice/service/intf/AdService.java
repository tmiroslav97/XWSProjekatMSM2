package services.app.carrequestservice.service.intf;

import services.app.carrequestservice.model.Ad;

import java.util.List;

public interface AdService {
    Ad findById(Long id);

    List<Ad> findAll();

    Integer deleteById(Long id);

    Boolean existsById(Long id);

    void delete(Ad ad);

    Ad save(Ad ad);
}
