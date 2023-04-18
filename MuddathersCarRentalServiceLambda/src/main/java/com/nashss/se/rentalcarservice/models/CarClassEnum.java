package com.nashss.se.rentalcarservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CarClassEnum {
    @JsonProperty("COMPACT") COMPACT,
    @JsonProperty("SEDAN") SEDAN,
    @JsonProperty("CONVERTIBLE") CONVERTIBLE,
    @JsonProperty("TRUCK") TRUCK,
    @JsonProperty("SUV") SUV,
    @JsonProperty("VAN") VAN
}
