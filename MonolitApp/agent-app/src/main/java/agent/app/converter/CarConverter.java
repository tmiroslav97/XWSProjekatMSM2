package agent.app.converter;

import agent.app.dto.car.CarCreateDTO;
import agent.app.model.Car;

public class CarConverter {
    public static Car toCreateCarFromRequest(CarCreateDTO carCreateDTO){
        return Car.builder()
                .year(DateAPI.DateStringToDateTimeFromFronted(carCreateDTO.getYear()))
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
