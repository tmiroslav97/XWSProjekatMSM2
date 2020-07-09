package agent.app.service.impl;

import agent.app.converter.AdConverter;
import agent.app.converter.PriceListConverter;
import agent.app.dto.ad.AdPageDTO;
import agent.app.dto.ad.ReversePricelistDTO;
import agent.app.dto.pricelist.PriceListCreateDTO;
import agent.app.exception.ExistsException;
import agent.app.exception.NotFoundException;
import agent.app.model.Ad;
import agent.app.model.PriceList;
import agent.app.model.PublisherUser;
import agent.app.repository.PriceListRepository;
import agent.app.service.intf.AdService;
import agent.app.service.intf.PriceListService;
import agent.app.service.intf.PublisherUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PriceListServiceImpl implements PriceListService {

    @Autowired
    private PriceListRepository priceListRepository;

    @Autowired
    private PublisherUserService publisherUserService;

    @Autowired
    private AdService adService;

    @Override
    public PriceList findById(Long id) {
        return priceListRepository.findById(id).orElseThrow(() -> new NotFoundException("Cenovnik ne postoji."));
    }

    @Override
    public List<PriceList> findAll() {
        return priceListRepository.findAll();
    }

    @Override
    public List<PriceList> findAllByPublisherUser(String email) {
        return priceListRepository.findAllByPublisherUser(email);
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
        PriceList priceList1 = this.findById(priceList.getId());
        priceList1.setPricePerDay(priceList.getPricePerDay());
        priceList1.setPricePerKm(priceList.getPricePerKm());
        priceList1.setPricePerKmCDW(priceList.getPricePerKmCDW());
        priceList1 = priceListRepository.save(priceList1);
        return 1;
    }

    @Override
    public Integer deleteById(Long id, String publisher) {
        PriceList priceList = this.findById(id);
        List<Long> usedPricelists = this.findPricelistsFromAds(publisher);
        if(!usedPricelists.contains(id)){
            System.out.println("ne sadrzi cenovnik mozes obrisati.");
            this.delete(priceList);
            return 1;
        }
        System.out.println("sadrzi cenovnik ne  mozes obrisati.");
        return 2;
    }

    @Override
    public List<Long> findPricelistsFromAds(String publisher) {
        List<Long> pricelists = new ArrayList<>();
        List<Ad> ads = adService.findAllFromPublisher(publisher);
        for(Ad ad : ads){
            if(!pricelists.contains(ad.getPriceList())){
                pricelists.add(ad.getPriceList().getId());
                System.out.println("cenovnik:   "+ad.getPriceList().getId());
            }
        }
        return pricelists;
    }

    @Override
    public Integer reversePricelist(ReversePricelistDTO reversePricelistDTO) {
        Ad ad = adService.findById(reversePricelistDTO.getAdId());
        PriceList priceList = this.findById(reversePricelistDTO.getPricelistId());
        ad.setPriceList(priceList);
        ad = adService.edit(ad);
        return 1;
    }


}
