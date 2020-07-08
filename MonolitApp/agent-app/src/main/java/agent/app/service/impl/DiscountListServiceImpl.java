package agent.app.service.impl;

import agent.app.converter.DiscountListConverter;
import agent.app.dto.discount.DiscountListCreateDTO;
import agent.app.dto.discount.DiscountListDTO;
import agent.app.exception.NotFoundException;
import agent.app.model.Ad;
import agent.app.model.Agent;
import agent.app.model.DiscountList;
import agent.app.repository.DiscountListRepository;
import agent.app.service.intf.AdService;
import agent.app.service.intf.AgentService;
import agent.app.service.intf.DiscountListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<DiscountList> findAllByAgent(String email) {
        return discountListRepository.findAllByAgent(email);
    }

    @Override
    public List<DiscountListDTO> findAllByAgentDTO(String email) {

        List<DiscountList> discountLists = this.findAllByAgent(email);
        List<DiscountListDTO> discountListDTOS = new ArrayList<>();

        for(DiscountList dl : discountLists){
            DiscountListDTO discountListDTO = DiscountListConverter.toDiscountListDTOFromDiscountList(dl);

            List<Long> adsId = new ArrayList<>();
            for(Ad ad : dl.getAds()){
                adsId.add(ad.getId());
            }
            discountListDTO.setAdsId(adsId);
            discountListDTOS.add(discountListDTO);
        }
        return discountListDTOS;
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
        discountList1.setDayNum(discountList.getDayNum());
        discountList1.setDiscount(discountList.getDiscount());
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

    @Override
    public Integer addDiscountToAd(Long discountId, Long adId) {
        System.out.println("dodavanjeeee");
        System.out.println(discountId + "    " + adId);
        DiscountList discountList = this.findById(discountId);
        Ad ad = adService.findById(adId);
        ad.getDiscountLists().add(discountList);
        ad = adService.edit(ad);
        return 1;
    }

    @Override
    public Integer deleteDiscountFromAd(Long discountId, Long adId) {
        System.out.println("brisanjeee");
        System.out.println(discountId + "    " + adId);
        DiscountList discountList = this.findById(discountId);
        Ad ad = adService.findById(adId);
        ad.getDiscountLists().remove(discountList);
        ad = adService.edit(ad);
        return 1;
    }
}
