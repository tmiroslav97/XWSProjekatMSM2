package services.app.authenticationservice.converter;

import services.app.authenticationservice.dto.PublisherUserDTO;
import services.app.authenticationservice.model.User;

public class PublisherUserConverter {
    public static PublisherUserDTO fromPublisherUserToPublisherUserDTO(User user) {
        return PublisherUserDTO.builder()
                .publisherUserId(user.getId())
                .publisherUserFirstName(user.getFirstName())
                .publisherUserLastName(user.getLastName())
                .build();
    }
}
