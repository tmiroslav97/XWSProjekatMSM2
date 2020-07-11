package agent.app.converter;

import agent.app.dto.car.CarCreateDTO;
import agent.app.dto.sync.CarSyncDTO;
import agent.app.model.Car;
import agent.app.model.enumeration.DistanceLimitEnum;
import services.app.adservice.model.CarSync;

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
                .distanceLimit(carCreateDTO.getDistanceLimit())
                .distanceLimitFlag(DistanceLimitEnum.valueOf(carCreateDTO.getDistanceLimitFlag()))
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
                .distanceLimit(car.getDistanceLimit())
                .distanceLimitFlag(car.getDistanceLimitFlag().toString())
                .androidFlag(car.getAndroidFlag())
                .token(car.getToken())
                .build();
    }

    public static CarSync toCarSyncFromCar(Car car) {
        CarSync carSync = new CarSync();
        carSync.setYear(DateAPI.DateTimeToStringDateTime(car.getYear()));
        carSync.setCarManufacturer(car.getCarManufacturer());
        carSync.setCarModel(car.getCarModel());
        carSync.setGearboxType(car.getGearboxType());
        carSync.setFuelType(car.getFuelType());
        carSync.setCarType(car.getCarType());
        carSync.setMileage(car.getMileage());
        carSync.setChildrenSeatNum(car.getChildrenSeatNum());
        carSync.setCdw(car.getCdw());
        carSync.setDistanceLimit(car.getDistanceLimit());
        carSync.setDistanceLimitFlag(car.getDistanceLimitFlag().toString());
        carSync.setAndroidFlag(car.getAndroidFlag());
        carSync.setToken(car.getToken());
        return carSync;
    }
}
