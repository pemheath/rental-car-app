package com.nashss.se.rentalcarservice.activity;

import com.nashss.se.rentalcarservice.dynamodb.CarDao;
import com.nashss.se.rentalcarservice.metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;


class UpdateCarActivityTest {

    @Mock
    private CarDao carDao;

    @Mock
    MetricsPublisher metricsPublisher;

    private UpdateCarActivity updateCarActivity;

    @BeforeEach
    public  void setUp() {
        openMocks(this);
        updateCarActivity = new UpdateCarActivity(carDao, metricsPublisher);

    }

    @Test
    public void handleRequest_goodRequest_updateCarAvailability() {

    }

    @Test public void handleRequest_nonExistantVin_throwsCarNotFoundException() {

    }

}