package com.nashss.se.rentalcarservice.activity.results;

import com.nashss.se.rentalcarservice.models.CarModel;

public class RemoveCarResult {
    private final CarModel carModel;

    public RemoveCarResult(CarModel carModel) {
        this.carModel = carModel;
    }

    public CarModel getCar() {
        return carModel;
    }

    @Override
    public String toString() {
        return "RemoveCarResult{" +
                "carModel=" + carModel +
                '}';
    }
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private CarModel carModel;

        public Builder withCar(CarModel carModel) {
            this.carModel = carModel;
            return this;
        }

        public RemoveCarResult build() {
            return new RemoveCarResult(carModel);
        }
    }
}
