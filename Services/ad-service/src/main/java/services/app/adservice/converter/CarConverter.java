package services.app.adservice.converter;

import services.app.adservice.dto.car.CarCreateDTO;
import services.app.adservice.dto.car.CarSynchronizeDTO;
import services.app.adservice.model.Car;

public class CarConverter {

    public static Car toCreateCarFromRequest(CarCreateDTO carCreateDTO){

        System.out.println("android flag "+carCreateDTO.getAndroidFlag());
//        System.out.println("android flag "+ carCreateDTO.getAndroidFlag());
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
                .androidFlag(Boolean.valueOf(carCreateDTO.getAndroidFlag()))
                .build();
    }

    public static CarSynchronizeDTO toCarSynchronizeDTOFromCar(Car car){
        return CarSynchronizeDTO.builder()
                .id(car.getId())
                .year(car.getYear().toString())
                .carManufacturer(car.getCarManufacturer())
                .carModel(car.getCarModel())
                .gearboxType(car.getGearboxType())
                .fuelType(car.getFuelType())
                .carType(car.getCarType())
                .mileage(car.getMileage())
                .childrenSeatNum(car.getChildrenSeatNum())
                .cdw(car.getCdw())
                .build();
    }

}
