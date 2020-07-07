package agent.app.converter;

import agent.app.dto.discount.DiscountListCreateDTO;
import agent.app.dto.discount.DiscountListDTO;
import agent.app.dto.sync.DiscountListSyncDTO;
import agent.app.model.DiscountList;

public class DiscountListConverter extends AbstractConverter {

    public static DiscountListSyncDTO toDiscountListSyncDTOFromDiscountList(DiscountList discountList) {
        return DiscountListSyncDTO.builder()
                .dayNum(discountList.getDayNum())
                .discount(discountList.getDiscount())
                .build();
    }

    public static DiscountListDTO toDiscountListDTOFromDiscountList(DiscountList discountList) {
        return DiscountListDTO.builder()
                .id(discountList.getId())
                .dayNum(discountList.getDayNum())
                .discount(discountList.getDiscount())
                .agentId(discountList.getAgent().getId())
                .build();
    }

    public static DiscountList toDiscountListFromDiscountListCreateDTO(DiscountListCreateDTO discountListCreateDTO) {
        return DiscountList.builder()
                .dayNum(discountListCreateDTO.getDayNum())
                .discount(discountListCreateDTO.getDiscount())
                .build();
    }
}
