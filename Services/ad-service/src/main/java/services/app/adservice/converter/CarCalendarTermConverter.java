package services.app.adservice.converter;


import services.app.adservice.dto.car.CarCalendarTermCreateDTO;
import services.app.adservice.dto.car.CarCalendarTermDTO;
import services.app.adservice.dto.car.CarCalendarTermSynchronizeDTO;
import services.app.adservice.model.CarCalendarTerm;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CarCalendarTermConverter {

    public static CarCalendarTerm toCreateCarCalendarTermFromRequest(CarCalendarTermCreateDTO carCalendarTermCreateDTO){

        return CarCalendarTerm.builder()
                .startDate(DateAPI.dateStringToDateTime(carCalendarTermCreateDTO.getStartDate()))
                .endDate(DateAPI.dateStringToDateTime(carCalendarTermCreateDTO.getEndDate()))
                .build();
    }

    public static CarCalendarTerm toCarCalendarTermFromRequest(CarCalendarTermDTO carCalendarTermDTO){

        return CarCalendarTerm.builder()
                .startDate(DateAPI.dateStringToDateTime(carCalendarTermDTO.getStartDate()))
                .endDate(DateAPI.dateStringToDateTime(carCalendarTermDTO.getEndDate()))
                .build();
    }

    public static List<CarCalendarTermSynchronizeDTO> toListCarCalendarTermSyncDTOFromListCarCalendarTerm(Set<CarCalendarTerm> carCalendarTermSet){
        List<CarCalendarTermSynchronizeDTO> carCalendarTermSynchronizeDTOS = new ArrayList<>();
        for(CarCalendarTerm cct : carCalendarTermSet){
            CarCalendarTermSynchronizeDTO cctDTO = CarCalendarTermSynchronizeDTO.builder()
                    .id(cct.getId())
                    .startDate(cct.getStartDate().toString())
                    .endDate(cct.getEndDate().toString())
                    .adId(cct.getAd().getId())
                    .build();
            carCalendarTermSynchronizeDTOS.add(cctDTO);
        }

        return carCalendarTermSynchronizeDTOS;
    }
}
