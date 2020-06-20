package agent.app.converter;

import agent.app.model.CarModel;

public class CarModelConverter extends AbstractConverter {

    public static String fromEntityToString(CarModel carModel) {
        return carModel.getName();
    }
}
