package services.app.authenticationservice.converter;

import services.app.authenticationservice.dto.EndUserDTO;
import services.app.authenticationservice.model.EndUser;

public class EndUserConverter extends AbstractConverter {

    public static EndUserDTO fromEndUserToEndUserDTO(EndUser endUser) {
        return EndUserDTO.builder()
                .id(endUser.getId())
                .email(endUser.getEmail())
                .firstName(endUser.getFirstName())
                .lastName(endUser.getLastName())
                .canceledCnt(endUser.getCanceledCnt())
                .enabled(endUser.getEnabled())
                .obligated(endUser.getObliged())
                .deleted(endUser.getDeleted())
                .build();
    }
}