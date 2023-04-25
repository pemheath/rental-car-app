package com.nashss.se.rentalcarservice.activity;

import com.nashss.se.projectresources.music.playlist.servic.util.MusicPlaylistServiceUtils;
import com.nashss.se.rentalcarservice.activity.requests.AddCarRequest;
import com.nashss.se.rentalcarservice.activity.results.AddCarResult;
import com.nashss.se.rentalcarservice.converters.ModelConverter;
import com.nashss.se.rentalcarservice.dynamodb.CarDao;
import com.nashss.se.rentalcarservice.dynamodb.models.Car;
import com.nashss.se.rentalcarservice.exceptions.CarNotFoundException;
import com.nashss.se.rentalcarservice.exceptions.InvalidAttributeValueException;
import com.nashss.se.rentalcarservice.exceptions.InvalidAttributesException;
import com.nashss.se.rentalcarservice.models.CarModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

import static java.util.Objects.isNull;

public class AddCarActivity {

    private final Logger log = LogManager.getLogger();
    private final CarDao carDao;

    /**
     * Instantiates a new AddCarActivity object.
     *
     * @param carDao   carDao to access the car table.
     */
    @Inject
    public AddCarActivity(CarDao carDao) {
        this.carDao = carDao;
    }


    public AddCarResult handleRequest(final AddCarRequest addCarRequest) {
        log.info("Received AddCarRequest {}", addCarRequest);

        if (!MusicPlaylistServiceUtils.isValidString(addCarRequest.getVIN())) {
            throw new InvalidAttributeValueException("Car VIN [" + addCarRequest.getVIN() +
                    "] contains illegal characters");
        }

        if (!MusicPlaylistServiceUtils.isValidString(addCarRequest.getYear())) {
            throw new InvalidAttributeValueException("Car year [" + addCarRequest.getYear() +
                    "] contains illegal characters");
        }

            Car newCar = new Car();
            newCar.setVIN(addCarRequest.getVIN());
            newCar.setMake(addCarRequest.getMake());
            newCar.setModel(addCarRequest.getModel());
            newCar.setClassOfVehicle(addCarRequest.getClassOfVehicle());
            newCar.setCostPerDay(addCarRequest.getCostPerDay());
            newCar.setAvailability(addCarRequest.getAvailability());
            newCar.setYear(addCarRequest.getYear());
            newCar.setCapacity(addCarRequest.getCapacity());

            carDao.saveCar(newCar);

            CarModel carModel = new ModelConverter().toCarModel(newCar);
            return AddCarResult.builder()
                    .withCar(carModel)
                    .build();


        }
}
