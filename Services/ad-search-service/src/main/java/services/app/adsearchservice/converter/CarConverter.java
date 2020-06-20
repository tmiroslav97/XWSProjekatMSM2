package services.app.adsearchservice.converter;

import services.app.adsearchservice.dto.car.CarSynchronizeDTO;
import services.app.adsearchservice.model.Car;

public class CarConverter {

    public static Car toCarFromCarSyncDTO(CarSynchronizeDTO dto){
        return Car.builder()
                .id(dto.getId())
                .year(DateAPI.dateStringToYear(dto.getYear()))
                .carManufacturer(dto.getCarManufacturer())
                .carModel(dto.getCarModel())
                .gearboxType(dto.getGearboxType())
                .fuelType(dto.getFuelType())
                .carType(dto.getCarType())
                .mileage(dto.getMileage())
                .childrenSeatNum(dto.getChildrenSeatNum())
                .cdw(dto.getCdw())
                .build();

    }
}
