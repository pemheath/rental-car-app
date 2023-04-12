package com.nashss.se.rentalcarservice.models;

import java.math.BigDecimal;
import java.util.Objects;

public class CarModel {

    private String VIN;
    private String make;
    private String model;
    private CarClassEnum classOfVehicle;
    private BigDecimal costPerDay;
    private AvailabilityEnum availability;
    private String year;
    private Integer capacity;

    public CarModel(String VIN, String make, String model, CarClassEnum classOfVehicle, BigDecimal costPerDay, AvailabilityEnum availability, String year, Integer capacity) {
        this.VIN = VIN;
        this.make = make;
        this.model = model;
        this.classOfVehicle = classOfVehicle;
        this.costPerDay = costPerDay;
        this.availability = availability;
        this.year = year;
        this.capacity = capacity;
    }

    public String getVIN() {
        return VIN;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public CarClassEnum getClassOfVehicle() {
        return classOfVehicle;
    }

    public BigDecimal getCostPerDay() {
        return costPerDay;
    }

    public AvailabilityEnum getAvailability() {
        return availability;
    }

    public String getYear() {
        return year;
    }

    public Integer getCapacity() {
        return capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarModel)) return false;
        CarModel carModel = (CarModel) o;
        return getVIN().equals(carModel.getVIN()) && getMake().equals(carModel.getMake()) && getModel().equals(carModel.getModel()) && getClassOfVehicle() == carModel.getClassOfVehicle() && getCostPerDay().equals(carModel.getCostPerDay()) && getAvailability() == carModel.getAvailability() && getYear().equals(carModel.getYear()) && getCapacity().equals(carModel.getCapacity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVIN(), getMake(), getModel(), getClassOfVehicle(), getCostPerDay(), getAvailability(), getYear(), getCapacity());
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String VIN;
        private String make;
        private String model;
        private CarClassEnum classOfVehicle;
        private BigDecimal costPerDay;
        private AvailabilityEnum availability;
        private String year;
        private Integer capacity;

        public Builder withVIN(String VIN) {
            this.VIN = VIN;
            return this;
        }

        public Builder withMake(String make) {
            this.make = make;
            return this;
        }

        public Builder withModel(String model) {
            this.model = model;
            return this;
        }

        public Builder withClass(CarClassEnum classOfVehicle) {
            this.classOfVehicle = classOfVehicle;
            return this;
        }

        public Builder withCost(BigDecimal costPerDay) {
            this.costPerDay = costPerDay;
            return this;
        }

        public Builder withAvailability(AvailabilityEnum availability) {
            this.availability = availability;
            return this;
        }

        public Builder withYear(String year) {
            this.year = year;
            return this;
        }

        public Builder withCapacity(Integer capacity) {
            this.capacity = capacity;
            return this;
        }

        public CarModel build() {
            return new CarModel(VIN, make, model, classOfVehicle, costPerDay, availability, year, capacity);
        }
    }
}
