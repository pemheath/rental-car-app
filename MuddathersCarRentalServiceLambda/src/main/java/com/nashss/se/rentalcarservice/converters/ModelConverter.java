package com.nashss.se.rentalcarservice.converters;

import com.nashss.se.rentalcarservice.dynamodb.models.Car;
import com.nashss.se.rentalcarservice.models.CarModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts between Data and API models.
 */
public class ModelConverter {
    /**
     * Converts a provided {@link Car} into a {@link CarModel} representation.
     *
     * @param car the car to convert
     * @return the converted car
     */
    public CarModel toCarModel(Car car) {

        return CarModel.builder()
                .withVIN(car.getVIN())
                .withMake(car.getMake())
                .withModel(car.getModel())
                .withClass(car.getClassOfVehicle())
                .withCost(car.getCostPerDay())
                .withAvailability(car.getAvailability())
                .withYear(car.getYear())
                .withCapacity(car.getCapacity())
                .build();
    }


    /**
     * Converts a list of Cars to a list of CarModels.
     *
     * @param cars The Cars to convert to CarModels
     * @return The converted list of CarModels
     */
    public List<CarModel> toCarModelList(List<Car> cars) {
        List<CarModel> carModels = new ArrayList<>();

        for (Car car : cars) {
            carModels.add(toCarModel(car));
        }

        return carModels;
    }
}
