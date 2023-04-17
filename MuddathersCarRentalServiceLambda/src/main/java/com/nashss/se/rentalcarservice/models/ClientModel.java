package com.nashss.se.rentalcarservice.models;

import java.util.Objects;

public class ClientModel {

    private String licenseNumber;
    private String name;
    private String email;
    private String phoneNumber;

    public ClientModel(String licenseNumber, String name, String email, String phoneNumber) {
        this.licenseNumber = licenseNumber;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientModel)) return false;
        ClientModel that = (ClientModel) o;
        return getLicenseNumber().equals(that.getLicenseNumber()) && getName().equals(that.getName()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getPhoneNumber(), that.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLicenseNumber(), getName(), getEmail(), getPhoneNumber());
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String licenseNumber;
        private String name;
        private String email;
        private String phoneNumber;

        public Builder withLicenseNumber(String licenseNumber) {
            this.licenseNumber = licenseNumber;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public ClientModel build() {
            return new ClientModel(licenseNumber, name, email, phoneNumber);
        }
    }
}
