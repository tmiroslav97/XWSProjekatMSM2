package services.app.adservice.converter;

import services.app.adservice.dto.car.CarCreateDTO;
import services.app.adservice.dto.car.CarSynchronizeDTO;
import services.app.adservice.dto.sync.CarSyncDTO;
import services.app.adservice.model.Car;
import services.app.adservice.model.CarSync;
import services.app.adservice.model.enumeration.DistanceLimitEnum;

public class CarConverter extends AbstractConverter {

    public static Car toCreateCarFromRequest(CarCreateDTO carCreateDTO) {
        DistanceLimitEnum distanceLimitEnum;
        if(carCreateDTO.getDistanceLimitFlag().equals("true")){
            distanceLimitEnum = DistanceLimitEnum.LIMITED;
        }else{
            distanceLimitEnum=DistanceLimitEnum.UNLIMITED;
        }
        return Car.builder()
                .year(DateAPI.DateStringToDateTimeFromFronted(carCreateDTO.getYear()))
                .carManufacturer(carCreateDTO.getCarManufacturer())
                .carModel(carCreateDTO.getCarModel())
                .gearboxType(carCreateDTO.getGearboxType())
                .fuelType(carCreateDTO.getFuelType())
                .carType(carCreateDTO.getCarType())
                .mileage(carCreateDTO.getMileage())
                .childrenSeatNum(carCreateDTO.getChildrenSeatNum())
                .distanceLimitFlag(distanceLimitEnum)
                .distanceLimit(carCreateDTO.getDistanceLimit())
                .cdw(carCreateDTO.getCdw())
                .androidFlag(Boolean.valueOf(carCreateDTO.getAndroidFlag()))
                .build();
    }

    public static CarSynchronizeDTO toCarSynchronizeDTOFromCar(Car car) {
        return CarSynchronizeDTO.builder()
                .id(car.getId())
                .year(DateAPI.DateTimeToStringDateTime(car.getYear()))
                .carManufacturer(car.getCarManufacturer())
                .carModel(car.getCarModel())
                .gearboxType(car.getGearboxType())
                .fuelType(car.getFuelType())
                .carType(car.getCarType())
                .mileage(car.getMileage())
                .childrenSeatNum(car.getChildrenSeatNum())
                .distanceLimit(car.getDistanceLimit())
                .distanceLimitFlag(car.getDistanceLimitFlag().toString())
                .cdw(car.getCdw())
                .build();
    }

    public static Car toCarFromCarSyncDTO(CarSyncDTO carSyncDTO) {
        return Car.builder()
                .year(DateAPI.DateTimeStringToDateTime(carSyncDTO.getYear()))
                .carManufacturer(carSyncDTO.getCarManufacturer())
                .childrenSeatNum(carSyncDTO.getChildrenSeatNum())
                .carModel(carSyncDTO.getCarModel())
                .gearboxType(carSyncDTO.getGearboxType())
                .fuelType(carSyncDTO.getFuelType())
                .carType(carSyncDTO.getCarType())
                .mileage(carSyncDTO.getMileage())
                .cdw(carSyncDTO.getCdw())
                .distanceLimit(carSyncDTO.getDistanceLimit())
                .distanceLimitFlag(DistanceLimitEnum.valueOf(carSyncDTO.getDistanceLimitFlag()))
                .androidFlag(carSyncDTO.getAndroidFlag())
                .token(carSyncDTO.getToken())
                .build();
    }

    public static Car toCarFromCarSync(CarSync carSyncDTO) {
        return Car.builder()
                .year(DateAPI.DateTimeStringToDateTime(carSyncDTO.getYear()))
                .carManufacturer(carSyncDTO.getCarManufacturer())
                .childrenSeatNum(carSyncDTO.getChildrenSeatNum())
                .carModel(carSyncDTO.getCarModel())
                .gearboxType(carSyncDTO.getGearboxType())
                .fuelType(carSyncDTO.getFuelType())
                .carType(carSyncDTO.getCarType())
                .mileage(carSyncDTO.getMileage())
                .cdw(carSyncDTO.isCdw())
                .distanceLimit(carSyncDTO.getDistanceLimit())
                .distanceLimitFlag(DistanceLimitEnum.valueOf(carSyncDTO.getDistanceLimitFlag()))
                .androidFlag(carSyncDTO.isAndroidFlag())
                .token(carSyncDTO.getToken())
                .build();
    }
}
