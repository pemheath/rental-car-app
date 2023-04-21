package com.nashss.se.rentalcarservice.activity;
import com.nashss.se.rentalcarservice.activity.requests.UpdateCarRequest;
import com.nashss.se.rentalcarservice.activity.results.UpdateCarResult;
import com.nashss.se.rentalcarservice.converters.ModelConverter;
import com.nashss.se.rentalcarservice.dynamodb.CarDao;
import com.nashss.se.rentalcarservice.dynamodb.models.Car;
import com.nashss.se.rentalcarservice.exceptions.CarNotFoundException;
import com.nashss.se.rentalcarservice.metrics.MetricsConstants;
import com.nashss.se.rentalcarservice.metrics.MetricsPublisher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the UpdateCarActivity for the RentalCarService's UpdateCar API.
 *
 * This API allows the customer to update their car's availability status .
 */
public class UpdateCarActivity {
    private final Logger log = LogManager.getLogger();
    private final CarDao carDao;
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a new UpdateCarActivity object.
     *
     * @param carDao CarDao to access the car table.
     * @param metricsPublisher MetricsPublisher to publish metrics.
     */
    @Inject
    public UpdateCarActivity(CarDao carDao, MetricsPublisher metricsPublisher) {
        this.carDao = carDao;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * This method handles the incoming request by retrieving the car, updating it,
     * and persisting the car.
     * <p>
     * It then returns the updated car.
     * <p>
     * If the playlist does not exist, this should throw a CarNotFoundException.

     *
     * @param updateCarRequest request object containing the car VIN , and new/desired availability status
     * @return updateCarResult result object containing the newly updated car
     */
    public UpdateCarResult handleRequest(final UpdateCarRequest updateCarRequest) {
        log.info("Received UpdateCarRequest {}", updateCarRequest);
        Car car;

        try {
            car = carDao.getCar(updateCarRequest.getVIN());
        }
        catch (CarNotFoundException e) {
            publishExceptionMetrics(true);
            throw new CarNotFoundException("You attempted to update a car that does not exist", e.getCause());
        }
        publishExceptionMetrics(false);

        car.setAvailability(updateCarRequest.getAvailability());

        if (updateCarRequest.getCostPerDay() != null) {
            car.setCostPerDay(updateCarRequest.getCostPerDay());
        }

        car = carDao.saveCar(car);

        return UpdateCarResult.builder()
                .withCar(new ModelConverter().toCarModel(car))
                .build();
    }

    /**
     * Helper method to publish exception metrics.
     * @param isCarNotFound indicates whether CarNotFound is thrown
     */
    private void publishExceptionMetrics(final boolean isCarNotFound) {
        metricsPublisher.addCount(MetricsConstants.GETCAR_CARNOTFOUND_COUNT,
                isCarNotFound ? 1 : 0);
    }
}
