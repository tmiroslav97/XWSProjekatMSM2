package services.app.authenticationservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import services.app.authenticationservice.converter.FirmConverter;
import services.app.authenticationservice.dto.firm.FirmDTO;
import services.app.authenticationservice.dto.firm.FirmPageDTO;
import services.app.authenticationservice.dto.firm.FirmRegDTO;
import services.app.authenticationservice.exception.NotFoundException;
import services.app.authenticationservice.model.Firm;
import services.app.authenticationservice.repository.FirmRepository;
import services.app.authenticationservice.service.intf.FirmService;

import java.util.ArrayList;
import java.util.List;

@Service
public class FirmServiceImpl implements FirmService {

    @Autowired
    private FirmRepository firmRepository;

    @Override
    public Firm findById(Long id) {
        return firmRepository.findById(id).orElseThrow(() -> new NotFoundException("Firma sa zadatim id- em ne postoji."));
    }

    @Override
    public FirmPageDTO findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("email").ascending());
        Page<Firm> firmPage = firmRepository.findAll(pageable);
        List<FirmDTO> firmDTOList = FirmConverter.fromEntityList(new ArrayList<>(firmPage.getContent()), FirmConverter::fromFirmToFirmDTO);
        FirmPageDTO firmPageDTO = FirmPageDTO.builder()
                .firms(firmDTOList)
                .totalPageCnt(firmPage.getTotalPages())
                .build();
        return firmPageDTO;
    }

    @Override
    public List<Firm> findAll() {
        return firmRepository.findAll();
    }

    @Override
    public Integer registerFirm(FirmRegDTO firmRegDTO) {
        return null;
    }

    @Override
    public Integer editFirm() {
        return null;
    }

    @Override
    public Integer logicDeleteOrRevertById(Long id, Boolean status) {
        Firm firm = this.findById(id);
        if (status) {
            firm.setDeleted(status);
            this.save(firm);
            return 1;
        } else {
            firm.setDeleted(status);
            this.save(firm);
            return 2;
        }
    }

    @Override
    public Integer deleteById(Long id) {
        Firm firm = this.findById(id);
        this.delete(firm);
        return 1;
    }

    @Override
    public void delete(Firm firm) {
        firmRepository.delete(firm);
    }

    @Override
    public Firm save(Firm firm) {
        return firmRepository.save(firm);
    }

    @Override
    public void syncData() {

    }
}
