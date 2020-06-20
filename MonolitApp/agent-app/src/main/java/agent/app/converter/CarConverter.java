package agent.app.converter;

import agent.app.dto.car.CarCreateDTO;
import agent.app.model.Car;

public class CarConverter {
    public static Car toCreateCarFromRequest(CarCreateDTO carCreateDTO){
//        DateTime dt = DateAPI.DateTimeFromString(carCreateDTO.getYear());
        return Car.builder()
                .year(DateAPI.dateStringToDateTime(carCreateDTO.getYear()))
                .carManufacturer(carCreateDTO.getCarManufacturer())
                .carModel(carCreateDTO.getCarModel())
                .gearboxType(carCreateDTO.getGearboxType())
                .fuelType(carCreateDTO.getFuelType())
                .carType(carCreateDTO.getCarType())
                .mileage(carCreateDTO.getMileage())
                .childrenSeatNum(carCreateDTO.getChildrenSeatNum())
                .cdw(carCreateDTO.getCdw())
                .androidFlag(carCreateDTO.getAndroidFlag())
                .build();
    }
}
