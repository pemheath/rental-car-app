package com.nashss.se.rentalcarservice.activity.requests;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.nashss.se.rentalcarservice.models.AvailabilityEnum;

import java.math.BigDecimal;

@JsonDeserialize(builder = UpdateCarRequest.Builder.class)
public class UpdateCarRequest {

    private final String VIN;
    private final BigDecimal costPerDay;
    private final AvailabilityEnum availability;

    /**
     *
     * @param VIN the vin of the car to update
     * @param availability, the availability the updated car should have
     * Creates an update car request
     */

    private UpdateCarRequest(String VIN, BigDecimal costPerDay, AvailabilityEnum availability) {
        this.VIN = VIN;
        this.costPerDay = costPerDay;
        this.availability = availability;
    }

    public String getVIN() {
        return this.VIN;
    }

    public BigDecimal getCostPerDay() {
        return costPerDay;
    }

    @JsonValue
    public AvailabilityEnum getAvailability() {
        return this.availability;
    }

    @Override
    public String toString() {
        return "UpdateCarRequest{" +
                "VIN='" + VIN + '\'' +
                ", costPerDay=" + costPerDay +
                ", availability=" + availability +
                '}';
    }

    /**
     *
     * @return a JSON styled request object
     */
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String VIN;
        private BigDecimal costPerDay;
        private AvailabilityEnum availability;

        public Builder withVin(String VIN) {
            this.VIN = VIN;
            return this;
        }

        public Builder withCostPerDay(BigDecimal costPerDay) {
            this.costPerDay = costPerDay;
            return this;
        }

        public Builder withAvailability(AvailabilityEnum availability) {
            this.availability = availability;
            return this;
        }

        public UpdateCarRequest build() {
            return new UpdateCarRequest(VIN, costPerDay, availability);
        }
    }
}