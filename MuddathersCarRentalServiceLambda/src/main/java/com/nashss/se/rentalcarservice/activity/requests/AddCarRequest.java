package com.nashss.se.rentalcarservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.nashss.se.rentalcarservice.models.AvailabilityEnum;
import com.nashss.se.rentalcarservice.models.CarClassEnum;

import java.math.BigDecimal;

@JsonDeserialize(builder = AddCarRequest.Builder.class)
public class AddCarRequest {

    private String VIN;
    private String make;
    private String model;
    private CarClassEnum classOfVehicle;
    private BigDecimal costPerDay;
    private AvailabilityEnum availability;
    private String year;
    private Integer capacity;

    public AddCarRequest(String VIN, String make, String model, CarClassEnum classOfVehicle, BigDecimal costPerDay, String year, Integer capacity) {
        this.VIN = VIN;
        this.make = make;
        this.model = model;
        this.classOfVehicle = classOfVehicle;
        this.costPerDay = costPerDay;
        this.availability = AvailabilityEnum.AVAILABLE;
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
    public String toString() {
        return "AddCarRequest{" +
                "VIN='" + VIN + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", classOfVehicle=" + classOfVehicle +
                ", costPerDay=" + costPerDay +
                ", availability=" + availability +
                ", year='" + year + '\'' +
                ", capacity=" + capacity +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }
    @JsonPOJOBuilder
    public static class Builder {

        private String VIN;
        private String make;
        private String model;
        private CarClassEnum classOfVehicle;
        private BigDecimal costPerDay;
//        private AvailabilityEnum availability;
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

        public Builder withClassOfVehicle(CarClassEnum classOfVehicle) {
            this.classOfVehicle = classOfVehicle;
            return this;
        }

        public Builder withCostPerDay(BigDecimal costPerDay) {
            this.costPerDay = costPerDay;
            return this;
        }

//        public Builder withAvailability(AvailabilityEnum availability) {
//            this.availability = availability;
//            return this;
//        }

        public Builder withYear(String year) {
            this.year = year;
            return this;
        }

        public Builder withCapacity(Integer capacity) {
            this.capacity = capacity;
            return this;
        }

        public AddCarRequest build() {
            return new AddCarRequest(VIN, make, model, classOfVehicle, costPerDay, year, capacity);
        }
    }
}
