package com.nashss.se.rentalcarservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.rentalcarservice.activity.requests.AddCarRequest;
import com.nashss.se.rentalcarservice.activity.results.AddCarResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddCarLambda extends LambdaActivityRunner<AddCarRequest, AddCarResult>
        implements RequestHandler<AuthenticatedLambdaRequest<AddCarRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddCarRequest> input, Context context) {
        log.info("Our input: '{}'" , input.toString());

        return super.runActivity(
                () -> {
                    AddCarRequest authenticatedRequest = input.fromBody(AddCarRequest.class);
                    log.info("Our request: '{}'" , authenticatedRequest.toString());
                    return AddCarRequest.builder()
                                    .withVIN(authenticatedRequest.getVIN())
                                    .withMake(authenticatedRequest.getMake())
                                    .withModel(authenticatedRequest.getModel())
                                    .withClassOfVehicle(authenticatedRequest.getClassOfVehicle())
                                    .withCostPerDay(authenticatedRequest.getCostPerDay())
                                    .withYear(authenticatedRequest.getYear())
                                    .withCapacity(authenticatedRequest.getCapacity())
                                    .build();
                },
                (request, serviceComponent) ->
                        serviceComponent.provideAddCarActivity().handleRequest(request)
        );
    }
}
