package com.nashss.se.rentalcarservice.activity.results;

import com.nashss.se.rentalcarservice.models.CarModel;

public class UpdateCarResult {

    private final CarModel car;

    private UpdateCarResult(CarModel car) {this.car = car;}

    public CarModel getCar() {return car;}

    @Override
    public String toString() {
        return "UpdateCarResult{" +
                "car=" + car +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private CarModel car;

        public Builder withCar(CarModel car) {
            this.car = car;
            return this;
        }

        public UpdateCarResult build() {
            return new UpdateCarResult(car);
        }
    }


}
