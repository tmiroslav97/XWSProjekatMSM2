package services.app.pricelistanddiscountservice.service.intf;

import org.joda.time.DateTime;
import services.app.pricelistanddiscountservice.dto.pricelist.PriceListCreateDTO;
import services.app.pricelistanddiscountservice.model.PriceList;

import java.util.List;

public interface PriceListService {

    PriceList findById(Long id);
    List<PriceList> findAll();
    List<PriceListCreateDTO> findAllListDTO();
    List<PriceListCreateDTO> findAllListDTOFromPublisher(Long userId);
    String findPriceListById(Long id);
    PriceList save(PriceList priceList);
    void delete(PriceList priceList);
    Long createPriceListRMQ(String msg);
    PriceList createPriceList(PriceListCreateDTO priceListCreateDTO);
    Integer editPriceList(PriceList priceList);
    Integer deleteById(Long id);
    Long syncPriceList(String msg);
    Long authAgent(String email, String identifier);
    Long createPriceListFromAgent(Long publisherUser, DateTime creationDate, Float pricePerDay,
                                  Float pricePerKm, Float pricePerKmCDW);
    Long editPriceListFromAgent(Float pricePerDay, Float pricePerKm,
                                Float pricePerKmCDW, Long mainId);
    Long deletePriceListFromAgent(Long mainId);
}
