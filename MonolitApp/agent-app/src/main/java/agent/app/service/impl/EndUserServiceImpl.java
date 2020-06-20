package agent.app.service.impl;

import agent.app.converter.EndUserConverter;
import agent.app.dto.enduser.EndUserDTO;
import agent.app.dto.enduser.EndUserPageDTO;
import agent.app.exception.NotFoundException;
import agent.app.model.EndUser;
import agent.app.repository.EndUserRepository;
import agent.app.service.intf.AdService;
import agent.app.service.intf.EndUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EndUserServiceImpl implements EndUserService {

    @Autowired
    private EndUserRepository endUserRepository;

    @Autowired
    private AdService adService;

    @Override
    public EndUser findById(Long id) {
        return endUserRepository.findById(id).orElseThrow(() -> new NotFoundException("Krajnji korisnik sa zadatim id- em nije pronadjen"));
    }

    @Override
    public List<EndUser> findAll() {
        return endUserRepository.findAll();
    }

    @Override
    public EndUserPageDTO findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("email").ascending());
        Page<EndUser> endUserPage = endUserRepository.findAll(pageable);
        List<EndUserDTO> endUserDTOList = EndUserConverter.fromEntityList(new ArrayList<>(endUserPage.getContent()), EndUserConverter::fromEndUserToEndUserDTO);
        EndUserPageDTO endUserPageDTO = EndUserPageDTO.builder()
                .endUsers(endUserDTOList)
                .totalPageCnt(endUserPage.getTotalPages())
                .build();
        return endUserPageDTO;
    }

    @Override
    public String blockOrUnblockById(Long id, Boolean status) {
        EndUser endUser = this.findById(id);
        endUser.setEnabled(status);
        this.save(endUser);
        if (status) {
            return "Korisniku uspješno ukinuto blokiranje";
        } else {
            return "Korisnik uspješno blokiran";
        }
    }

    @Override
    public String obligateOrUnobligateById(Long id, Boolean status) {
        EndUser endUser = this.findById(id);
        endUser.setObliged(status);
        this.save(endUser);
        if (status) {
            return "Korisniku uspješno uvedena zabrana";
        } else {
            return "Korisniku uspješno skinuta zabrana";
        }
    }

    @Override
    public String logicDeleteOrRevertById(Long id, Boolean status) {
        EndUser endUser = this.findById(id);
        adService.logicalDeleteOrRevertAds(new ArrayList<>(endUser.getAds()), status);
        endUser.setDeleted(status);
        this.save(endUser);
        if (status) {
            return "Korisnik uspješno logički obrisan";
        } else {
            return "Korisnik uspješno vraćen";
        }
    }

    @Override
    public Integer deleteById(Long id) {
        EndUser endUser = this.findById(id);
        this.delete(endUser);
        return 1;
    }

    @Override
    public void delete(EndUser endUser) {
        endUserRepository.delete(endUser);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return endUserRepository.existsByEmail(email);
    }

    @Override
    public EndUser findByEmail(String email) {
        return endUserRepository.findByEmail(email);
    }

    @Override
    public EndUser save(EndUser endUser) {
        return endUserRepository.save(endUser);
    }

    @Override
    public Integer getAdLimitNum(String email) {
        EndUser endUser = this.findByEmail(email);
        if (endUser == null) {
            return 4; //non limit it's not end user, it is agent
        }
        return endUser.getAdLimitNum();
    }

    @Override
    public Integer reduceAdLimitNum(String email) {
        EndUser endUser = this.findByEmail(email);
        Integer br = getAdLimitNum(email);
        endUser.setAdLimitNum(br - 1);
        return br - 1;
    }
}
