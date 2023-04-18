package com.nashss.se.rentalcarservice.activity.requests;

public class RemoveCarRequest {
    private final String VIN;

    public RemoveCarRequest(String VIN) {
        this.VIN = VIN;
    }

    public String getVIN() {
        return VIN;
    }

    @Override
    public String toString() {
        return "RemoveCarRequest{" +
                "VIN='" + VIN + '\'' +
                '}';
    }
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String VIN;

        public Builder withVIN(String VIN) {
            this.VIN = VIN;
            return this;
        }

        public RemoveCarRequest build() {
            return new RemoveCarRequest(VIN);
        }
    }
}
