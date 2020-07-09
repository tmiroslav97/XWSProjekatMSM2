package agent.app.service.intf;

import agent.app.dto.discount.DiscountListCreateDTO;
import agent.app.dto.discount.DiscountListDTO;
import agent.app.model.DiscountList;

import java.util.List;

public interface DiscountListService {
    DiscountList findById(Long id);

    List<DiscountListDTO> findAll();

    List<DiscountList> findAllByAgent(String email);

    List<DiscountListDTO> findAllByAgentDTO(String email);

    DiscountList save(DiscountList discountList);

    void delete(DiscountList discountList);

    Integer deleteById(Long id);

    Integer edit(DiscountList discountList);

    Integer createDiscount(DiscountListCreateDTO discountListCreateDTO, String email);

    Integer addDiscountToAd(Long discountId, Long adId);

    Integer deleteDiscountFromAd(Long discountId, Long adId);

}
