package com.nashss.se.rentalcarservice.activity;

import com.nashss.se.rentalcarservice.activity.requests.GetCarRequest;
import com.nashss.se.rentalcarservice.activity.results.GetCarResult;
import com.nashss.se.rentalcarservice.converters.ModelConverter;
import com.nashss.se.rentalcarservice.dynamodb.CarDao;
import com.nashss.se.rentalcarservice.dynamodb.models.Car;
import com.nashss.se.rentalcarservice.models.CarModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class GetCarActivity {

    private final Logger log = LogManager.getLogger();
    private final CarDao carDao;

    /**
     * Instantiates a new GetCarActivity object.
     *
     * @param carDao CarDao to access the car table.
     */
    @Inject
    public GetCarActivity(CarDao carDao) {
        this.carDao = carDao;
    }

    /**
     * This method handles the incoming request by retrieving the car from the database.
     * <p>
     * It then returns the car.
     * <p>
     * If the car does not exist, this should throw a PlaylistNotFoundException.
     *
     * @param getCarRequest request object containing the car ID
     * @return getPlaylistResult result object containing the API defined {@link CarModel}
     */
    public GetCarResult handleRequest(final GetCarRequest getCarRequest) {
        log.info("Received GetCarRequest {}", getCarRequest);
        String requestedVIN = getCarRequest.getVIN();
        Car car = carDao.getCar(requestedVIN);
        CarModel carModel = new ModelConverter().toCarModel(car);

        return GetCarResult.builder()
                .withCar(carModel)
                .build();
    }
}
