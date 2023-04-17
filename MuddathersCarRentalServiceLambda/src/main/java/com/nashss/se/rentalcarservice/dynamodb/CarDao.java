package com.nashss.se.rentalcarservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.nashss.se.rentalcarservice.dynamodb.models.Car;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarDao {

    private static final String CAR_AVAILABILITY_INDEX = "AvailabilityClassIndex";
    private final DynamoDBMapper mapper;

    @Inject
    public CarDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public List<Car> searchCars(String[] criteria) {

        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":availability", new AttributeValue().withS("AVAILABLE"));
//        valueMap.put(":class", new AttributeValue().withS(""));
        System.out.println("Past valueMap");
        DynamoDBQueryExpression<Car> queryExpression = new DynamoDBQueryExpression<Car>()
                .withIndexName(CAR_AVAILABILITY_INDEX)
                .withConsistentRead(false)
                .withKeyConditionExpression("availability = :availability")
                .withExpressionAttributeValues(valueMap);
        System.out.println("Past QueryExpression");

        PaginatedQueryList<Car> carsFromGSI = mapper.query(Car.class, queryExpression);
        ArrayList<Car> carList = new ArrayList<>();

        System.out.println("Past query");

        for (Car car : carsFromGSI) {
            Car actualCar = mapper.load(Car.class, car.getVIN());
            carList.add(actualCar);
        }

        return carList;
    }
}
