package services.app.carrequestservice.converter;

import services.app.carrequestservice.dto.carreq.AcceptReqestCalendarTermsDTO;
import services.app.carrequestservice.model.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RequestConverter extends AbstractConverter {
    public static AcceptReqestCalendarTermsDTO toCreateAcceptRequestCalendarTermsDTO(Request request) {
        List<Long> ads = new ArrayList<>(request.getAds().stream().map(ad -> ad.getId()).collect(Collectors.toList()));
        return AcceptReqestCalendarTermsDTO.builder()
                .bundle(request.getBundle())
                .ads(ads)
                .build();
    }
}
