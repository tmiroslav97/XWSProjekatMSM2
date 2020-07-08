package services.app.authenticationservice.converter;

import services.app.authenticationservice.dto.UserFLNameDTO;
import services.app.authenticationservice.model.User;

public class PublisherUserConverter {
    public static UserFLNameDTO toUserFLNameDTOFromUser(User user) {
        return UserFLNameDTO.builder()
                .userId(user.getId())
                .userFirstName(user.getFirstName())
                .userLastName(user.getLastName())
                .userEmail(user.getEmail())
                .local(user.getLocal())
                .build();
    }
}
