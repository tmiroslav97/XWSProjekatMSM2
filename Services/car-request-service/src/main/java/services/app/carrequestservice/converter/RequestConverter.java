package services.app.carrequestservice.converter;

import services.app.carrequestservice.dto.ad.AdRequestDTO;
import services.app.carrequestservice.dto.carreq.AcceptReqestCalendarTermsDTO;
import services.app.carrequestservice.model.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RequestConverter extends AbstractConverter {
    public static AcceptReqestCalendarTermsDTO toCreateAcceptRequestCalendarTermsDTO(Request request) {
        List<AdRequestDTO> adRequestDTOS = new ArrayList<>(request.getAds().stream().map(ad -> AdRequestDTO.builder()
                .id(ad.getMainId())
                .adName(ad.getAdName())
                .startDate(DateAPI.DateTimeToStringDateTime(ad.getStartDate()))
                .endDate(DateAPI.DateTimeToStringDateTime(ad.getEndDate()))
                .build()).collect(Collectors.toList()));
        return AcceptReqestCalendarTermsDTO.builder()
                .bundle(request.getBundle())
                .publisherUserId(request.getPublisherUser())
                .adRequestDTOS(adRequestDTOS)
                .build();
    }
}
