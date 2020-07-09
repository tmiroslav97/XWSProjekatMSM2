package services.app.pricelistanddiscountservice.converter;

import services.app.pricelistanddiscountservice.dto.discount.DiscountListCreateDTO;
import services.app.pricelistanddiscountservice.dto.discount.DiscountListDTO;
import services.app.pricelistanddiscountservice.dto.sync.DiscountListSyncDTO;
import services.app.pricelistanddiscountservice.model.DiscountList;

public class DiscountListConverter extends AbstractConverter {

    public static DiscountList toDiscountListFromDiscountListSyncDTO(DiscountListSyncDTO discountListSyncDTO) {
        return DiscountList.builder()
                .discount(discountListSyncDTO.getDiscount())
                .dayNum(discountListSyncDTO.getDayNum())
                .build();
    }

    public static DiscountListDTO toDiscountListDTOFromDiscountList(DiscountList discountList) {
        return DiscountListDTO.builder()
                .id(discountList.getId())
                .dayNum(discountList.getDayNum())
                .discount(discountList.getDiscount())
                .agentId(discountList.getAgent())
                .build();
    }

    public static DiscountList toDiscountListFromDiscountListCreateDTO(DiscountListCreateDTO discountListCreateDTO) {
        return DiscountList.builder()
                .dayNum(discountListCreateDTO.getDayNum())
                .discount(discountListCreateDTO.getDiscount())
                .build();
    }

    public static DiscountList toDiscountListFromDiscountListDTO(DiscountListDTO discountListDTO) {
        return DiscountList.builder()
                .id(discountListDTO.getId())
                .dayNum(discountListDTO.getDayNum())
                .discount(discountListDTO.getDiscount())
                .build();
    }
}
