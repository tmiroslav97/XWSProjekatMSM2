package services.app.codebookservice.converter;

import services.app.codebookservice.model.CarModel;

public class CarModelConverter extends AbstractConverter {

    public static String fromEntityToString(CarModel carModel) {
        return carModel.getName();
    }
}
