package services.app.pricelistanddiscountservice.converter;

import services.app.pricelistanddiscountservice.dto.pricelist.PriceListCreateDTO;
import services.app.pricelistanddiscountservice.dto.sync.PriceListSyncDTO;
import services.app.pricelistanddiscountservice.model.PriceList;

public class PriceListConverter extends AbstractConverter{

    public static PriceList toCreatePriceListFromRequest(PriceListCreateDTO priceListCreateDTO){
        return PriceList.builder()
                .creationDate(DateAPI.DateTimeNow())
                .pricePerKm(priceListCreateDTO.getPricePerKm())
                .pricePerKmCDW(priceListCreateDTO.getPricePerKmCDW())
                .pricePerDay(priceListCreateDTO.getPricePerDay())
                .build();
    }

    public static PriceListCreateDTO toCreatePriceListCreateDTOFromPriceList(PriceList priceList){
        return PriceListCreateDTO.builder()
                .id(priceList.getId())
                .pricePerDay(priceList.getPricePerDay())
                .pricePerKm(priceList.getPricePerKm())
                .pricePerKmCDW(priceList.getPricePerKmCDW())
//                .publisherUsername(priceList.getPublisherUser().getEmail())
                .creationDate(priceList.getCreationDate().toString())
                .build();
    }

    public static PriceList toPriceListFromPriceListSyncDTO(PriceListSyncDTO priceListSyncDTO){
        return PriceList.builder()
                .pricePerDay(priceListSyncDTO.getPricePerDay())
                .pricePerKm(priceListSyncDTO.getPricePerKm())
                .pricePerKmCDW(priceListSyncDTO.getPricePerKmCDW())
                .creationDate(DateAPI.DateTimeStringToDateTime(priceListSyncDTO.getCreationDate()))
                .build();
    }

}
