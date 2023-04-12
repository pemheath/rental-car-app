package com.nashss.se.rentalcarservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.rentalcarservice.dynamodb.models.Car;

import java.util.List;

public class CarDao {

    private final DynamoDBMapper mapper;

    public CarDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public List<Car> searchCars() {
        return null;
    }
}
