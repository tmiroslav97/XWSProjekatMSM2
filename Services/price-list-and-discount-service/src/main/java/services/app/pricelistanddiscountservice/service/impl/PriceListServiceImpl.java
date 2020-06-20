package services.app.pricelistanddiscountservice.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import services.app.pricelistanddiscountservice.client.AuthenticationClient;
import services.app.pricelistanddiscountservice.converter.PriceListConverter;
import services.app.pricelistanddiscountservice.dto.pricelist.PriceListCreateDTO;
import services.app.pricelistanddiscountservice.exception.ExistsException;
import services.app.pricelistanddiscountservice.exception.NotFoundException;
import services.app.pricelistanddiscountservice.model.CustomPrincipal;
import services.app.pricelistanddiscountservice.model.PriceList;
import services.app.pricelistanddiscountservice.repository.PriceListRepository;
import services.app.pricelistanddiscountservice.service.intf.PriceListService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PriceListServiceImpl implements PriceListService {

    @Autowired
    PriceListRepository priceListRepository;

    @Autowired
    private AuthenticationClient authenticationClient;

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
    public List<PriceListCreateDTO> findAllListDTOFromPublisher() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();
        Long publishUser = authenticationClient.findPublishUserByEmail(principal.getToken());
        System.out.println("PUBLISH USER " + publishUser);
        List<PriceList> priceLists = this.findAll();
        List<PriceList> priceListsFromPublishUser = new ArrayList<>();
        if(!priceLists.isEmpty()){
            for(PriceList pl : priceLists){
                if(pl.getPublisherUser().equals(publishUser)){
                    System.out.println("dodata price lista");
                    priceListsFromPublishUser.add(pl);
                }
            }
        }
        List<PriceListCreateDTO> ret = priceListsFromPublishUser.stream().map(PriceListConverter::toCreatePriceListCreateDTOFromPriceList).collect(Collectors.toList());
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();
        Long publisherUser = authenticationClient.findPublishUserByEmail(principal.getToken());
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
