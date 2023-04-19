package com.nashss.se.rentalcarservice.activity;

import com.nashss.se.rentalcarservice.activity.requests.GetCarRequest;
import com.nashss.se.rentalcarservice.activity.requests.RemoveCarRequest;
import com.nashss.se.rentalcarservice.activity.results.GetCarResult;
import com.nashss.se.rentalcarservice.activity.results.RemoveCarResult;
import com.nashss.se.rentalcarservice.converters.ModelConverter;
import com.nashss.se.rentalcarservice.dynamodb.CarDao;
import com.nashss.se.rentalcarservice.dynamodb.models.Car;
import com.nashss.se.rentalcarservice.models.CarModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class RemoveCarActivity {
    private final Logger log = LogManager.getLogger();
    private final CarDao carDao;

    /**
     * Instantiates a new SearchCarsActivity object.
     *
     * @param carDao carDao to access the cars table.
     */
    @Inject
    public RemoveCarActivity(CarDao carDao) {
        this.carDao = carDao;
    }
    /**
     * This method handles the incoming request by retrieving the car from the database.
     * <p>
     * It then returns the car.
     * <p>
     * If the car does not exist, this should throw a PlaylistNotFoundException.
     *
     * @param removeCarRequest request object containing the car ID
     * @return getPlaylistResult result object containing the API defined {@link CarModel}
     */
    public RemoveCarResult handleRequest(final RemoveCarRequest removeCarRequest) {
        log.info("Received RemoveCarRequest {}", removeCarRequest);
        String requestedVIN = removeCarRequest.getVIN();
        Car car = carDao.removeCar(requestedVIN);
        CarModel carModel = new ModelConverter().toCarModel(car);
        return RemoveCarResult.builder()
                .withCar(carModel)
                .build();
    }
}
