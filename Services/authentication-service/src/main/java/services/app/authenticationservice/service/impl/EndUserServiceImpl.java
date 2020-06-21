package services.app.authenticationservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import services.app.authenticationservice.converter.EndUserConverter;
import services.app.authenticationservice.dto.EndUserDTO;
import services.app.authenticationservice.dto.EndUserPageDTO;
import services.app.authenticationservice.exception.NotFoundException;
import services.app.authenticationservice.model.EndUser;
import services.app.authenticationservice.repository.EndUserRepository;
import services.app.authenticationservice.service.intf.EndUserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class EndUserServiceImpl implements EndUserService {

    @Autowired
    private EndUserRepository endUserRepository;

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
    public EndUser save(EndUser endUser) {
        return endUserRepository.save(endUser);
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
    public Integer getAdLimitNum(String email) {
        EndUser endUser = this.findByEmail(email);
        if (endUser == null) {
            return 4; //non limit it's not end user, it is agent
        }
        System.out.println("limit num: " + endUser.getAdLimitNum());
        return endUser.getAdLimitNum();
    }

    @Override
    public Integer reduceAdLimitNum(String email) {
        EndUser endUser = this.findByEmail(email);
        Integer br = getAdLimitNum(email);
        System.out.println("limit num pre: " + br);
        endUser.setAdLimitNum(br - 1);
        endUserRepository.save(endUser);
        System.out.println("limit num posle: " + endUser.getAdLimitNum());
        return br - 1;
    }
}
