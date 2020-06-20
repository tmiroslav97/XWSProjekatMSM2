package services.app.adservice.converter;

import services.app.adservice.dto.car.CarCreateDTO;
import services.app.adservice.model.Car;

public class CarConverter {
    public static Car toCreateCarFromRequest(CarCreateDTO carCreateDTO){
        return Car.builder()
                .year(DateAPI.dateStringToYear(carCreateDTO.getYear()))
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
