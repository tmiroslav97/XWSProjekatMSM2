package agent.app.service.impl;

import agent.app.converter.DiscountListConverter;
import agent.app.dto.discount.DiscountListCreateDTO;
import agent.app.dto.discount.DiscountListDTO;
import agent.app.exception.NotFoundException;
import agent.app.model.Ad;
import agent.app.model.Agent;
import agent.app.model.DiscountList;
import agent.app.model.PriceList;
import agent.app.repository.DiscountListRepository;
import agent.app.service.intf.AdService;
import agent.app.service.intf.AgentService;
import agent.app.service.intf.DiscountListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class DiscountListServiceImpl implements DiscountListService {

    @Autowired
    private DiscountListRepository discountListRepository;

    @Autowired
    private AgentService agentService;

    @Autowired
    private AdService adService;

    @Override
    public DiscountList findById(Long id) {
        return discountListRepository.findById(id).orElseThrow(() -> new NotFoundException("Popust ne postoji."));
    }

    @Override
    public List<DiscountListDTO> findAll() {
        List<DiscountList> discountLists = discountListRepository.findAll();
        return DiscountListConverter.fromEntityList(discountLists, DiscountListConverter::toDiscountListDTOFromDiscountList);
    }

    @Override
    public List<DiscountListDTO> findAllByAgent(String email) {
        List<DiscountList> discountLists = discountListRepository.findAllByAgent(email);
        return DiscountListConverter.fromEntityList(discountLists, DiscountListConverter::toDiscountListDTOFromDiscountList);
    }

    @Override
    public List<DiscountListDTO> findAllDiscountFromAd(Long id) {
        Ad ad =  adService.findById(id);
        List<DiscountList> discountLists = new ArrayList<>();
        for(DiscountList dl : ad.getDiscountLists()){
            discountLists.add(dl);
        }
        return DiscountListConverter.fromEntityList(discountLists, DiscountListConverter::toDiscountListDTOFromDiscountList);
    }

    @Override
    public DiscountList save(DiscountList discountList) {
        return discountListRepository.save(discountList);
    }

    @Override
    public void delete(DiscountList discountList) {
        discountListRepository.delete(discountList);
    }

    @Override
    public Integer deleteById(Long id) {
        DiscountList discountList = this.findById(id);
        this.delete(discountList);
        return 1;
    }

    @Override
    public Integer edit(DiscountList discountList) {
        DiscountList discountList1 = this.findById(discountList.getId());
        discountList1 = discountListRepository.save(discountList1);
        return 1;
    }

    @Override
    public Integer createDiscount(DiscountListCreateDTO discountListCreateDTO, String email) {

        DiscountList discountList = DiscountListConverter.toDiscountListFromDiscountListCreateDTO(discountListCreateDTO);
        Agent agent = agentService.findByEmail(email);
        discountList.setAgent(agent);
        discountList = this.save(discountList);
        return 1;
    }
}
