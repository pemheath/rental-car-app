package com.nashss.se.rentalcarservice.activity.requests;

public class GetCarRequest {
    private final String VIN;

    private GetCarRequest(String VIN) {
        this.VIN = VIN;
    }

    public String getVIN() {
        return VIN;
    }

    @Override
    public String toString() {
        return "GetCarRequest{" +
                "VIN='" + VIN + '\'' +
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
