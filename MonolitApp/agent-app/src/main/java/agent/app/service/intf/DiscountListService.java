package agent.app.service.intf;

import agent.app.dto.discount.DiscountListCreateDTO;
import agent.app.dto.discount.DiscountListDTO;
import agent.app.model.DiscountList;

import java.util.List;

public interface DiscountListService {
    DiscountList findById(Long id);

    List<DiscountListDTO> findAll();

    List<DiscountListDTO> findAllByAgent(String email);

    List<DiscountListDTO> findAllDiscountFromAd(Long id);

    DiscountList save(DiscountList discountList);

    void delete(DiscountList discountList);

    Integer deleteById(Long id);

    Integer edit(DiscountList discountList);

    Integer createDiscount(DiscountListCreateDTO discountListCreateDTO, String email);
}
