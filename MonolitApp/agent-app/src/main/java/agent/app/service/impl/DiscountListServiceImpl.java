package agent.app.service.impl;

import agent.app.exception.NotFoundException;
import agent.app.model.DiscountList;
import agent.app.repository.DiscountListRepository;
import agent.app.service.intf.DiscountListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<DiscountList> findAllByAgent(String email) {
        return discountListRepository.findAllByAgent(email);
    }

    @Override
    public DiscountList save(DiscountList discountList) {
        return discountListRepository.save(discountList);
    }

    @Override
    public void delete(DiscountList discountList) {
        discountListRepository.delete(discountList);
    }

    @Override
    public Integer deleteById(Long id) {
        DiscountList discountList = this.findById(id);
        this.delete(discountList);
        return 1;
    }
}
