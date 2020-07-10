package services.app.adservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.app.adservice.exception.NotFoundException;
import services.app.adservice.model.DiscountList;
import services.app.adservice.repository.AdRepository;
import services.app.adservice.repository.DiscountListRepository;
import services.app.adservice.service.intf.DiscountListService;

import java.util.List;

@Service
public class DiscountListServiceImpl implements DiscountListService {

    @Autowired
    private DiscountListRepository discountListRepository;

    @Override
    public DiscountList findById(Long id) {
        return discountListRepository.findById(id).orElseThrow(() -> new NotFoundException("Popust ne postoji."));
    }

    @Override
    public List<DiscountList> findAll() {
        return discountListRepository.findAll();
    }

    @Override
    public Integer addDiscount(Long id) {
        DiscountList dl = new DiscountList();
        dl.setId(id);
        discountListRepository.save(dl);
        return 1;
    }

    @Override
    public Integer deleteDiscount(Long id) {
        DiscountList dl = this.findById(id);
        discountListRepository.delete(dl);
        return 1;
    }
}
