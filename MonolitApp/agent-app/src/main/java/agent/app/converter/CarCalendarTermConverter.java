package agent.app.converter;

import agent.app.dto.car.CarCalendarTermCreateDTO;
import agent.app.model.CarCalendarTerm;

public class CarCalendarTermConverter {

    public static CarCalendarTerm toCreateCarCalendarTermFromRequest(CarCalendarTermCreateDTO carCalendarTermCreateDTO){
//
        return CarCalendarTerm.builder()
                .startDate(DateAPI.dateStringToDateTime(carCalendarTermCreateDTO.getStartDate()))
                .endDate(DateAPI.dateStringToDateTime(carCalendarTermCreateDTO.getEndDate()))
                .build();
    }
}
