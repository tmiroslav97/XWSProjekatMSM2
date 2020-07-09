package agent.app.converter;

import agent.app.dto.pricelist.PriceListCreateDTO;
import agent.app.dto.sync.PriceListSyncDTO;
import agent.app.model.PriceList;

public class PriceListConverter extends AbstractConverter {

    public static PriceList toCreatePriceListFromRequest(PriceListCreateDTO priceListCreateDTO) {
        return PriceList.builder()
                .creationDate(DateAPI.DateTimeNow())
                .pricePerKm(priceListCreateDTO.getPricePerKm())
                .pricePerKmCDW(priceListCreateDTO.getPricePerKmCDW())
                .pricePerDay(priceListCreateDTO.getPricePerDay())
                .build();
    }

    public static PriceList toEditPriceListFromRequest(PriceListCreateDTO priceListCreateDTO) {

        return PriceList.builder()
                .id(priceListCreateDTO.getId())
                .pricePerKm(priceListCreateDTO.getPricePerKm())
                .pricePerKmCDW(priceListCreateDTO.getPricePerKmCDW())
                .pricePerDay(priceListCreateDTO.getPricePerDay())
                .build();
    }

    public static PriceListCreateDTO toCreatePriceListCreateDTOFromPriceList(PriceList priceList) {
        return PriceListCreateDTO.builder()
                .id(priceList.getId())
                .pricePerDay(priceList.getPricePerDay())
                .pricePerKm(priceList.getPricePerKm())
                .pricePerKmCDW(priceList.getPricePerKmCDW())
                .publisherUsername(priceList.getPublisherUser().getEmail())
                .creationDate(priceList.getCreationDate().toString())
                .build();
    }

    public static PriceListSyncDTO toPriceListSyncDTOFromPriceList(PriceList priceList) {
        return PriceListSyncDTO.builder()
                .creationDate(DateAPI.DateTimeToStringDateTime(priceList.getCreationDate()))
                .pricePerDay(priceList.getPricePerDay())
                .pricePerKm(priceList.getPricePerKm())
                .pricePerKmCDW(priceList.getPricePerKmCDW())
                .build();
    }
}
