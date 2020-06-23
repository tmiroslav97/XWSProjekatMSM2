package agent.app.converter;

import agent.app.dto.car.CarCalendarTermCreateDTO;
import agent.app.dto.car.CarCalendarTermDTO;
import agent.app.model.CarCalendarTerm;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CarCalendarTermConverter {

    public static CarCalendarTerm toCreateCarCalendarTermFromRequest(CarCalendarTermCreateDTO carCalendarTermCreateDTO){
        return CarCalendarTerm.builder()
                .startDate(DateAPI.DateTimeStringToDateTimeFromFronted(carCalendarTermCreateDTO.getStartDate()))
                .endDate(DateAPI.DateTimeStringToDateTimeFromFronted(carCalendarTermCreateDTO.getEndDate()))
                .build();
    }

    public static CarCalendarTerm toCarCalendarTermFromRequest(CarCalendarTermDTO carCalendarTermDTO){

        return CarCalendarTerm.builder()
                .startDate(DateAPI.DateTimeStringToDateTimeFromFronted(carCalendarTermDTO.getStartDate()))
                .endDate(DateAPI.DateTimeStringToDateTimeFromFronted(carCalendarTermDTO.getEndDate()))
                .build();
    }


}
