package services.app.pricelistanddiscountservice.service.intf;

import org.springframework.data.domain.Page;
import services.app.pricelistanddiscountservice.dto.pricelist.PriceListCreateDTO;
import services.app.pricelistanddiscountservice.model.PriceList;

import java.util.List;

public interface PriceListService {

    PriceList findById(Long id);
    List<PriceList> findAll();
    List<PriceListCreateDTO> findAllListDTO();
    List<PriceListCreateDTO> findAllListDTOFromPublisher();
    PriceList save(PriceList priceList);
    void delete(PriceList priceList);
    PriceList createPriceList(PriceListCreateDTO priceListCreateDTO);
    Integer editPriceList(PriceList priceList);
    Integer deleteById(Long id);

}
