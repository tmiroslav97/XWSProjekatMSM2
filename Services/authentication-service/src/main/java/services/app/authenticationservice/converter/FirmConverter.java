package services.app.authenticationservice.converter;

import services.app.authenticationservice.dto.firm.FirmDTO;
import services.app.authenticationservice.model.Firm;

public class FirmConverter extends AbstractConverter {

    public static FirmDTO fromFirmToFirmDTO(Firm firm) {
        return FirmDTO.builder()
                .id(firm.getId())
                .email(firm.getEmail())
                .firmName(firm.getFirmName())
                .deleted(firm.getDeleted())
                .pmb(firm.getPmb())
                .build();
    }
}
