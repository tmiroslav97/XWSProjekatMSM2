package agent.app.converter;

import agent.app.dto.adrequest.AdRequestDTO;
import agent.app.dto.carreq.RequestDTO;
import agent.app.model.Request;

import java.util.List;

public class RequestConverter extends AbstractConverter {

    public static RequestDTO toRequestDTOFromRequest(Request request) {
        List<AdRequestDTO> ads = AdRequestConverter.fromEntityList(request.getAds(), AdRequestConverter::toAdRequestDTOFromAdRequest);
        return RequestDTO.builder()
                .id(request.getId())
                .ads(ads)
                .bundle(request.getBundle())
                .endUserId(request.getEndUser().getId())
                .endUserEmail(request.getEndUser().getEmail())
                .endUserFirstName(request.getEndUser().getFirstName())
                .endUserLastName(request.getEndUser().getLastName())
                .publisherUserId(request.getPublisherUser().getId())
                .publisherUserEmail(request.getPublisherUser().getEmail())
                .publisherUserFirstName(request.getPublisherUser().getFirstName())
                .publisherUserLastName(request.getPublisherUser().getLastName())
                .status(request.getStatus().toString())
                .submitDate(DateAPI.DateTimeToStringDateTime(request.getSubmitDate()))
                .build();
    }
}
