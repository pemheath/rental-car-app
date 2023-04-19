package com.nashss.se.rentalcarservice.activity.requests;

public class UpdateCarRequest {

    private final String VIN;

    private final String desiredAvailability;



    private UpdateCarRequest(String VIN, String desiredAvailability) {
        this.criteria = criteria;
    }

    public String getCriteria() {
        return criteria;
    }

    @Override
    public String toString() {
        return "GetCarRequest{" +
                "criteria='" + criteria + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String VIN;

        public Builder withVIN(String VIN) {
            this.VIN = VIN;
            return this;
        }

        public GetCarRequest build() {
            return new GetCarRequest(VIN);
        }
    }
}