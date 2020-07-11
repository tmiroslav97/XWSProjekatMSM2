package agent.app.converter;

import agent.app.dto.adrequest.AdRequestDTO;
import agent.app.model.AdRequest;

public class AdRequestConverter extends AbstractConverter {

    public static AdRequestDTO toAdRequestDTOFromAdRequest(AdRequest adRequest) {
        return AdRequestDTO.builder()
                .id(adRequest.getId())
                .mainId(adRequest.getMainId())
                .adId(adRequest.getAdId())
                .adName(adRequest.getAdName())
                .cdw(adRequest.getCdw())
                .distanceLimit(adRequest.getDistanceLimit())
                .distanceLimitFlag(adRequest.getDistanceLimitFlag().toString())
                .startDate(DateAPI.DateTimeToStringDateTime(adRequest.getStartDate()))
                .endDate(DateAPI.DateTimeToStringDateTime(adRequest.getEndDate()))
                .pricePerDay(adRequest.getPricePerDay())
                .pricePerKm(adRequest.getPricePerKm())
                .pricePerKmCDW(adRequest.getPricePerKmCDW())
                .review(adRequest.getReview())
                .token(adRequest.getToken())
                .report(adRequest.getReport())
                .build();
    }
}
