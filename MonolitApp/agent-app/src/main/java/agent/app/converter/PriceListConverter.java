package agent.app.converter;

import agent.app.dto.pricelist.PriceListCreateDTO;
import agent.app.model.PriceList;

public class PriceListConverter extends AbstractConverter{

    public static PriceList toCreatePriceListFromRequest(PriceListCreateDTO priceListCreateDTO){
//        DateTime dt = DateAPI.DateTimeFromDateString(priceListCreateDTO.getCreationDate());

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
                .publisherUsername(priceList.getPublisherUser().getEmail())
                .creationDate(priceList.getCreationDate().toString())
                .build();
    }

}
