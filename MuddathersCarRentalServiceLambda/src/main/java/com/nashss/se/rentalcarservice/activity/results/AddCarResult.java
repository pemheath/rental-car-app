package com.nashss.se.rentalcarservice.activity.results;

import com.nashss.se.rentalcarservice.models.CarModel;

public class AddCarResult {

    private final CarModel carModel;

    private AddCarResult(CarModel carModel) {
        this.carModel = carModel;
    }

    public CarModel getCar() {
        return carModel;
    }

    @Override
    public String toString() {
        return "AddCarResult{" +
                "carModel=" + carModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private CarModel carModel;

        public Builder withCar(CarModel carModel) {
            this.carModel = carModel;
            return this;
        }

        public AddCarResult build() {
            return new AddCarResult(carModel);
        }
    }
}
