package com.nashss.se.rentalcarservice.dynamodb.models;

import java.util.Objects;

public class Client {

    private String licenseNumber;
    private String name;
    private String email;
    private String phoneNumber;

    public Client(String licenseNumber, String name, String email, String phoneNumber) {
        this.licenseNumber = licenseNumber;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Client{" +
                "licenseNumber='" + licenseNumber + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return getLicenseNumber().equals(client.getLicenseNumber()) && getName().equals(client.getName()) && Objects.equals(getEmail(), client.getEmail()) && Objects.equals(getPhoneNumber(), client.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLicenseNumber(), getName(), getEmail(), getPhoneNumber());
    }
}
