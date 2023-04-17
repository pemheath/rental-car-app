package com.nashss.se.rentalcarservice.activity.results;

import com.nashss.se.rentalcarservice.models.CarModel;

import java.util.ArrayList;
import java.util.List;

public class SearchCarsResult {

    private final List<CarModel> cars;

    private SearchCarsResult(List<CarModel> cars) {
        this.cars = cars;
    }

    public List<CarModel> getCars() {
        return cars;
    }

    @Override
    public String toString() {
        return "SearchCarsResult{" +
                "cars=" + cars +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<CarModel> cars ;

        public Builder withCars(List<CarModel> cars) {
            this.cars = new ArrayList<>(cars);
            return this;
        }

        public SearchCarsResult build() {
            return new SearchCarsResult(cars);
        }
    }
}
