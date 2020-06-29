package agent.app.converter;

import agent.app.dto.sync.DiscountListSyncDTO;
import agent.app.model.DiscountList;

public class DiscountListConverter extends AbstractConverter {

    public static DiscountListSyncDTO toDiscountListSyncDTOFromDiscountList(DiscountList discountList) {
        return DiscountListSyncDTO.builder()
                .startDate(DateAPI.DateTimeToStringDateTime(discountList.getStartDate()))
                .endDate(DateAPI.DateTimeToStringDateTime(discountList.getEndDate()))
                .discount(discountList.getDiscount())
                .build();
    }
}
