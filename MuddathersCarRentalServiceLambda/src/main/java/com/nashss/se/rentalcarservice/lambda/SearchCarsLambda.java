package com.nashss.se.rentalcarservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.rentalcarservice.activity.requests.SearchCarsRequest;
import com.nashss.se.rentalcarservice.activity.results.SearchCarsResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchCarsLambda extends LambdaActivityRunner<SearchCarsRequest, SearchCarsResult>
        implements RequestHandler<LambdaRequest<SearchCarsRequest>, LambdaResponse>{

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(LambdaRequest<SearchCarsRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
                () -> input.fromQuery(query -> {
                        log.info("Searching for cars with availability {}", query.get("q"));
                        return SearchCarsRequest.builder()
                                .withCriteria(query.get("q"))
                                .build();}),
                (request, serviceComponent) ->
                        serviceComponent.provideSearchCarsActivity().handleRequest(request)
        );
    }

}
