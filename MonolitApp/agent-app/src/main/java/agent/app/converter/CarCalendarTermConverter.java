package agent.app.converter;

import agent.app.dto.car.CarCalendarTermCreateDTO;
import agent.app.dto.car.CarCalendarTermDTO;
import agent.app.dto.cct.CarCalendarTermSynchronizeDTO;
import agent.app.dto.sync.CarCalendarTermSyncDTO;
import agent.app.model.CarCalendarTerm;

public class CarCalendarTermConverter extends AbstractConverter {

    public static CarCalendarTerm toCreateCarCalendarTermFromRequest(CarCalendarTermCreateDTO carCalendarTermCreateDTO) {
        return CarCalendarTerm.builder()
                .startDate(DateAPI.DateTimeStringToDateTimeFromFronted(carCalendarTermCreateDTO.getStartDate()))
                .endDate(DateAPI.DateTimeStringToDateTimeFromFronted(carCalendarTermCreateDTO.getEndDate()))
                .build();
    }

    public static CarCalendarTerm toCarCalendarTermFromRequest(CarCalendarTermDTO carCalendarTermDTO) {

        return CarCalendarTerm.builder()
                .startDate(DateAPI.DateTimeStringToDateTimeFromFronted(carCalendarTermDTO.getStartDate()))
                .endDate(DateAPI.DateTimeStringToDateTimeFromFronted(carCalendarTermDTO.getEndDate()))
                .build();
    }

    public static CarCalendarTermSyncDTO toCarCalendarTermSyncDTOFromCarCalendarTerm(CarCalendarTerm carCalendarTerm) {
        return CarCalendarTermSyncDTO.builder()
                .startDate(DateAPI.DateTimeToStringDateTime(carCalendarTerm.getStartDate()))
                .endDate(DateAPI.DateTimeToStringDateTime(carCalendarTerm.getEndDate()))
                .build();
    }

    public static CarCalendarTerm toCarCalendarTermFromSyncDTO(CarCalendarTermSynchronizeDTO dto){
        return CarCalendarTerm.builder()
                .startDate(DateAPI.DateTimeStringToDateTime(dto.getStartDate()))
                .endDate(DateAPI.DateTimeStringToDateTime(dto.getEndDate()))
                .build();
    }

}
