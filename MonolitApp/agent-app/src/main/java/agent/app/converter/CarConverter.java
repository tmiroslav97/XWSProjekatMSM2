package agent.app.converter;

import agent.app.dto.car.CarCreateDTO;
import agent.app.dto.sync.CarSyncDTO;
import agent.app.model.Car;

public class CarConverter extends AbstractConverter {
    public static Car toCreateCarFromRequest(CarCreateDTO carCreateDTO) {
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

    public static CarSyncDTO toCarSyncDTOFromCar(Car car) {
        return CarSyncDTO.builder()
                .year(DateAPI.DateTimeToStringDateTime(car.getYear()))
                .carManufacturer(car.getCarManufacturer())
                .carModel(car.getCarModel())
                .gearboxType(car.getGearboxType())
                .fuelType(car.getFuelType())
                .carType(car.getCarType())
                .mileage(car.getMileage())
                .childrenSeatNum(car.getChildrenSeatNum())
                .cdw(car.getCdw())
                .androidFlag(car.getAndroidFlag())
                .token(car.getToken())
                .build();
    }
}
