package agent.app.service.intf;

import agent.app.model.DiscountList;

import java.util.List;

public interface DiscountListService {
    DiscountList findById(Long id);

    List<DiscountList> findAll();

    List<DiscountList> findAllByAgent(String email);

    DiscountList save(DiscountList discountList);

    void delete(DiscountList discountList);

    Integer deleteById(Long id);
}
