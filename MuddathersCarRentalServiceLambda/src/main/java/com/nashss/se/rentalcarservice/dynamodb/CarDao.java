package com.nashss.se.rentalcarservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.nashss.se.rentalcarservice.dynamodb.models.Car;
import com.nashss.se.rentalcarservice.exceptions.CarNotFoundException;

import javax.inject.Inject;
import java.util.*;

public class CarDao {

    private static final String CAR_AVAILABILITY_INDEX = "AvailabilityClassIndex";
    private final DynamoDBMapper mapper;

    @Inject
    public CarDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public List<Car> searchCars(String[] criteria) {

        ArrayList<Car> carList = new ArrayList<>();

        if (Objects.equals(criteria[1], "none")) {
            Map<String, AttributeValue> valueMap = new HashMap<>();
            valueMap.put(":availability", new AttributeValue().withS(criteria[0]));

            DynamoDBQueryExpression<Car> queryExpression = new DynamoDBQueryExpression<Car>()
                    .withIndexName(CAR_AVAILABILITY_INDEX)
                    .withConsistentRead(false)
                    .withKeyConditionExpression("availability = :availability")
                    .withExpressionAttributeValues(valueMap);

            PaginatedQueryList<Car> carsFromGSI = mapper.query(Car.class, queryExpression);

            for (Car car : carsFromGSI) {
                Car actualCar = mapper.load(Car.class, car.getVIN());
                carList.add(actualCar);
            }
        }
        else {
            Map<String, AttributeValue> valueMap = new HashMap<>();
            valueMap.put(":availability", new AttributeValue().withS(criteria[0]));
            valueMap.put(":class", new AttributeValue().withS(criteria[1]));

            DynamoDBQueryExpression<Car> queryExpression = new DynamoDBQueryExpression<Car>()
                    .withIndexName(CAR_AVAILABILITY_INDEX)
                    .withConsistentRead(false)
                    .withKeyConditionExpression("availability = :availability and classOfVehicle = :class")
                    .withExpressionAttributeValues(valueMap);

            PaginatedQueryList<Car> carsFromGSI = mapper.query(Car.class, queryExpression);

            for (Car car : carsFromGSI) {
                Car actualCar = mapper.load(Car.class, car.getVIN());
                carList.add(actualCar);
            }
        }
        return carList;
    }

    public Car getCar(String VIN) {
        Car car = this.mapper.load(Car.class, VIN);

        if (car == null) {
            throw new CarNotFoundException("Could not find car with VIN " + VIN);
        }
        return car;
    }

    public Car removeCar(String VIN) {
        Car carToRemove = getCar(VIN);
        this.mapper.delete(carToRemove);
        return carToRemove;


    /**
     * Saves (creates or updates) the given car.
     *
     * @param car The car to save
     * @return The Car object that was saved
     */
    public Car saveCar(Car car) {
        this.mapper.save(car);
        return car;

    }
}
