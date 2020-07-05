package services.app.adsearchservice.converter;

import services.app.adsearchservice.dto.car.CarSynchronizeDTO;
import services.app.adsearchservice.model.Car;
import services.app.adsearchservice.model.enumeration.DistanceLimitEnum;

public class CarConverter {

    public static Car toCarFromCarSyncDTO(CarSynchronizeDTO dto){
        return Car.builder()
                .id(dto.getId())
                .year(DateAPI.DateTimeStringToDateTime(dto.getYear()))
                .carManufacturer(dto.getCarManufacturer())
                .carModel(dto.getCarModel())
                .gearboxType(dto.getGearboxType())
                .fuelType(dto.getFuelType())
                .carType(dto.getCarType())
                .mileage(dto.getMileage())
                .childrenSeatNum(dto.getChildrenSeatNum())
                .distanceLimitFlag(DistanceLimitEnum.valueOf(dto.getDistanceLimitFlag()))
                .distanceLimit(dto.getDistanceLimit())
                .cdw(dto.getCdw())
                .build();

    }
}
