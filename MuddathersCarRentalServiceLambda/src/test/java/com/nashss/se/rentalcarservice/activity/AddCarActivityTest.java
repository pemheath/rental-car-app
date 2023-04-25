package com.nashss.se.rentalcarservice.activity;

import com.nashss.se.rentalcarservice.dynamodb.CarDao;
import com.nashss.se.rentalcarservice.metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class AddCarActivityTest {

    @Mock
    private CarDao carDao;

    private AddCarActivity addCarActivity;

    @BeforeEach
    public void setUp() {
        addCarActivity = new AddCarActivity(carDao);
    }

    @Test
    public void handleReqeust_newCarInfoProvided_returnsNewlyUpdatedCar() {

    }







}