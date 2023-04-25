package com.nashss.se.rentalcarservice.activity;

import com.nashss.se.rentalcarservice.activity.requests.AddCarRequest;
import com.nashss.se.rentalcarservice.activity.results.AddCarResult;
import com.nashss.se.rentalcarservice.dynamodb.CarDao;
import com.nashss.se.rentalcarservice.dynamodb.models.Car;
import com.nashss.se.rentalcarservice.exceptions.CarNotFoundException;
import com.nashss.se.rentalcarservice.exceptions.InvalidAttributesException;
import com.nashss.se.rentalcarservice.metrics.MetricsPublisher;
import com.nashss.se.rentalcarservice.models.AvailabilityEnum;
import com.nashss.se.rentalcarservice.models.CarClassEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class AddCarActivityTest {

    @Mock
    private CarDao carDao;

    @InjectMocks
    private AddCarActivity addCarActivity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void handleRequest_newCarInfoProvided_returnsNewlyUpdatedCar() {
        //GIVEN
        String vin = "123488856";
        String make = "Honda";
        String model = "Civic";
        CarClassEnum classOfVehicle = CarClassEnum.SEDAN;
        BigDecimal costPerDay = new BigDecimal(100);
        String year = "2020";
        Integer capacity = 5;

        Car car = new Car ();
        car.setVIN(vin);
        car.setMake(make);
        car.setModel(model);
        car.setCapacity(capacity);
        car.setYear(year);
        car.setCostPerDay(costPerDay);
        car.setClassOfVehicle(classOfVehicle);
        car.setAvailability(AvailabilityEnum.AVAILABLE);


        AddCarRequest request = AddCarRequest.builder()
                .withVIN(vin)
                .withMake(make)
                .withModel(model)
                .withClassOfVehicle(classOfVehicle)
                .withCostPerDay(costPerDay)
                .withYear(year)
                .withCapacity(capacity)
                .build();
        when(carDao.saveCar(car)).thenReturn(car);

        // WHEN
        AddCarResult result = addCarActivity.handleRequest(request);

        //THEN
        assertEquals(vin, result.getCar().getVIN());
        assertEquals(year, result.getCar().getYear());
        assertEquals(make, result.getCar().getMake());
        assertEquals(model, result.getCar().getModel());
        assertEquals(capacity, result.getCar().getCapacity());
        assertEquals(classOfVehicle, result.getCar().getClassOfVehicle());

    }
}