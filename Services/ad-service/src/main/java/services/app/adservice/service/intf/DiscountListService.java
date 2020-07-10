package services.app.adservice.service.intf;

import services.app.adservice.model.Ad;
import services.app.adservice.model.DiscountList;

import java.util.List;

public interface DiscountListService {

    DiscountList findById(Long id);
    List<DiscountList> findAll();
    Integer addDiscount(Long id);
    Integer deleteDiscount(Long id);
}
