package com.nashss.se.rentalcarservice.activity;

import com.nashss.se.rentalcarservice.activity.requests.UpdateCarRequest;
import com.nashss.se.rentalcarservice.activity.results.UpdateCarResult;
import com.nashss.se.rentalcarservice.dynamodb.CarDao;
import com.nashss.se.rentalcarservice.dynamodb.models.Car;
import com.nashss.se.rentalcarservice.exceptions.CarNotFoundException;
import com.nashss.se.rentalcarservice.metrics.MetricsPublisher;
import com.nashss.se.rentalcarservice.models.AvailabilityEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;


class UpdateCarActivityTest {

    @Mock
    private CarDao carDao;

    @Mock
    MetricsPublisher metricsPublisher;

    @InjectMocks
    private UpdateCarActivity updateCarActivity;

    @BeforeEach
    public  void setUp() {
        openMocks(this);
        updateCarActivity = new UpdateCarActivity(carDao, metricsPublisher);

    }

    @Test
    public void handleRequest_goodRequest_updateCarAvailability() {
        //GIVEN
        String vin = "123456";
        AvailabilityEnum availability = AvailabilityEnum.AVAILABLE;

        UpdateCarRequest request = UpdateCarRequest.builder()
                .withVin(vin)
                .withAvailability(availability)
                .build();

        Car startingCar = new Car();
        startingCar.setVIN(vin);
        startingCar.setAvailability(AvailabilityEnum.RENTED);

        when(carDao.getCar(vin)).thenReturn(startingCar);
        when(carDao.saveCar(startingCar)).thenReturn(startingCar);

        //WHEN
        UpdateCarResult result = updateCarActivity.handleRequest(request);

        //THEN
        assertEquals(vin, result.getCar().getVIN());
        assertEquals(availability, result.getCar().getAvailability());

    }

    @Test public void handleRequest_nonExistentVin_throwsCarNotFoundException() {

        String vin = "123456";
        AvailabilityEnum availability = AvailabilityEnum.AVAILABLE;
        //GIVEN
        UpdateCarRequest request = UpdateCarRequest.builder()
                .withVin(vin)
                .withAvailability(availability)
                .build();

        when(carDao.getCar(vin)).thenThrow(new CarNotFoundException());

        //WHEN/THEN
        assertThrows(CarNotFoundException.class, () -> updateCarActivity.handleRequest(request));

    }

}