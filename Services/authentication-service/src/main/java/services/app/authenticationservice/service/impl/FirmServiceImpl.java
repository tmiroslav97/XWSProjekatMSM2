package services.app.authenticationservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import services.app.authenticationservice.converter.FirmConverter;
import services.app.authenticationservice.dto.EmailDTO;
import services.app.authenticationservice.dto.firm.FirmDTO;
import services.app.authenticationservice.dto.firm.FirmPageDTO;
import services.app.authenticationservice.dto.firm.FirmRegDTO;
import services.app.authenticationservice.exception.ExistsException;
import services.app.authenticationservice.exception.NotFoundException;
import services.app.authenticationservice.model.Agent;
import services.app.authenticationservice.model.Authority;
import services.app.authenticationservice.model.Firm;
import services.app.authenticationservice.repository.FirmRepository;
import services.app.authenticationservice.service.intf.FirmService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class FirmServiceImpl implements FirmService {

    @Autowired
    private FirmRepository firmRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

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
        if (firmRepository.existsByEmail(firmRegDTO.getEmail())) {
            throw new ExistsException("Firma sa zadatim email-om vec postoji");
        }
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 35;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        while (firmRepository.existsByIdentifier(generatedString)) {
            generatedString = random.ints(leftLimit, rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
        }
        Firm firm = Firm.builder()
                .firmName(firmRegDTO.getFirmName())
                .email(firmRegDTO.getEmail())
                .address(firmRegDTO.getAddress())
                .deleted(false)
                .identifier(generatedString)
                .pmb(firmRegDTO.getPmb())
                .build();
        firm = this.save(firm);
        EmailDTO emailDTO = EmailDTO.builder()
                .email(firmRegDTO.getEmail())
                .subject("Registracija na RENT-A-CAR")
                .message("Postovani,\n\n" +
                        "Registrovani ste na rent-a-car sistem!\n" +
                        "Vas jedinstveni kod za verifikaciju i pristup je (pod navodnicima):\n\n" +
                        "\"" + generatedString + "\"" + "\n\n" +
                        "Admin tim")
                .build();
        try {
            String msg = objectMapper.writeValueAsString(emailDTO);
            rabbitTemplate.convertAndSend("emails", msg);
            return 1;
        } catch (JsonProcessingException exception) {
            return 2;
        }
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
