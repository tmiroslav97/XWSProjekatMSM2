package services.app.pricelistanddiscountservice.service.impl;

import org.springframework.stereotype.Service;
import services.app.pricelistanddiscountservice.model.DiscountList;
import services.app.pricelistanddiscountservice.service.intf.DiscountListService;

import java.util.List;

@Service
public class DiscountListServiceImpl implements DiscountListService {
    @Override
    public DiscountList findById(Long id) {
        return null;
    }

    @Override
    public List<DiscountList> findAll() {
        return null;
    }

    @Override
    public DiscountList save(DiscountList discountList) {
        return null;
    }

    @Override
    public void delete(DiscountList discountList) {

    }

    @Override
    public Integer deleteById(Long id) {
        return null;
    }

    @Override
    public Integer edit(Long id) {
        return null;
    }
}
