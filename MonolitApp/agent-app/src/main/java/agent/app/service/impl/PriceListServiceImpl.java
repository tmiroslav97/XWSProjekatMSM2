package agent.app.service.impl;

import agent.app.converter.AdConverter;
import agent.app.converter.PriceListConverter;
import agent.app.dto.ad.AdPageDTO;
import agent.app.dto.pricelist.PriceListCreateDTO;
import agent.app.exception.ExistsException;
import agent.app.exception.NotFoundException;
import agent.app.model.PriceList;
import agent.app.model.PublisherUser;
import agent.app.repository.PriceListRepository;
import agent.app.service.intf.PriceListService;
import agent.app.service.intf.PublisherUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PriceListServiceImpl implements PriceListService {

    @Autowired
    PriceListRepository priceListRepository;

    @Autowired
    private PublisherUserService publisherUserService;

    @Override
    public PriceList findById(Long id) {
        return priceListRepository.findById(id).orElseThrow(() -> new NotFoundException("Cenovnik ne postoji."));
    }

    @Override
    public List<PriceList> findAll() {
        return priceListRepository.findAll();
    }

    @Override
    public List<PriceListCreateDTO> findAllListDTO() {
        return PriceListConverter.fromEntityList(this.findAll(), PriceListConverter::toCreatePriceListCreateDTOFromPriceList);
    }

    @Override
    public List<PriceListCreateDTO> findAllListDTOFromPublisher(String publisherUsername) {
        List<PriceList> priceLists = publisherUserService.findPriceListsFromPublishUser(publisherUsername);
        if(priceLists.isEmpty()){
            return null;
        }
        List<PriceListCreateDTO> ret = priceLists.stream().map(PriceListConverter::toCreatePriceListCreateDTOFromPriceList).collect(Collectors.toList());
        return ret;
    }

    @Override
    public PriceList save(PriceList priceList) {
        if(priceList.getId() != null){
            if(priceListRepository.existsById(priceList.getId())){
                throw new ExistsException(String.format("Cenovnik vec postoji."));
            }
        }

        return priceListRepository.save(priceList);
    }

    @Override
    public void delete(PriceList priceList) {
        priceListRepository.delete(priceList);
    }

    @Override
    public PriceList createPriceList(PriceListCreateDTO priceListCreateDTO) {
        PriceList priceList = PriceListConverter.toCreatePriceListFromRequest(priceListCreateDTO);
        PublisherUser publisherUser = publisherUserService.findByEmail(priceListCreateDTO.getPublisherUsername());
        priceList.setPublisherUser(publisherUser);
        priceList = this.priceListRepository.save(priceList);
        return priceList;
    }

    @Override
    public Integer editPriceList(PriceList priceList) {
        this.findById(priceList.getId());
        PriceList priceList1 = priceListRepository.save(priceList);
        return 1;
    }

    @Override
    public Integer deleteById(Long id) {
        PriceList priceList = this.findById(id);
        this.delete(priceList);
        return 1;
    }


}
