package com.nashss.se.rentalcarservice.activity;

import com.nashss.se.rentalcarservice.activity.requests.SearchCarsRequest;
import com.nashss.se.rentalcarservice.activity.results.SearchCarsResult;
import com.nashss.se.rentalcarservice.converters.ModelConverter;
import com.nashss.se.rentalcarservice.dynamodb.CarDao;
import com.nashss.se.rentalcarservice.dynamodb.models.Car;
import com.nashss.se.rentalcarservice.models.CarModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

import static com.nashss.se.rentalcarservice.utils.NullUtils.ifNull;

public class SearchCarsActivity {

    private final Logger log = LogManager.getLogger();
    private final CarDao carDao;

    /**
     * Instantiates a new SearchCarsActivity object.
     *
     * @param carDao carDao to access the cars table.
     */
    @Inject
    public SearchCarsActivity(CarDao carDao) {
        this.carDao = carDao;
    }

    /**
     * This method handles the incoming request by searching for playlist from the database.
     * <p>
     * It then returns the matching playlists, or an empty result list if none are found.
     *
     * @param searchCarsRequest request object containing the search criteria
     * @return searchPlaylistsResult result object containing the playlists that match the
     * search criteria.
     */
    public SearchCarsResult handleRequest(final SearchCarsRequest searchCarsRequest) {
        log.info("Received SearchCarsRequest {}", searchCarsRequest);

        String criteria = ifNull(searchCarsRequest.getCriteria(), "");
        String[] criteriaArray = criteria.isBlank() ? new String[0] : criteria.split("\\s");

        List<Car> results = carDao.searchCars(criteriaArray);

        List<CarModel> carModels = new ModelConverter().toCarModelList(results);

        return SearchCarsResult.builder()
                .withCars(carModels)
                .build();
    }
}
