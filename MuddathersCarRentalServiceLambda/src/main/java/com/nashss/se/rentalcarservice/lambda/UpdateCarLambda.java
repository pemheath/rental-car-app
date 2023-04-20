package com.nashss.se.rentalcarservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.rentalcarservice.activity.requests.UpdateCarRequest;
import com.nashss.se.rentalcarservice.activity.results.UpdateCarResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateCarLambda extends LambdaActivityRunner<UpdateCarRequest, UpdateCarResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateCarRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateCarRequest> input, Context context){

        log.info("Our input to UpdateCarLambda: '{}'", input.toString());


        return super.runActivity(
                () -> {
                    UpdateCarRequest authenticatedRequest = input.fromBody(UpdateCarRequest.class);
                    log.info("Out UpdateCarRequest: '{}'", authenticatedRequest.toString());
                    return UpdateCarRequest.builder()
                            .withVin(authenticatedRequest.getVIN())
                            .withAvailability(authenticatedRequest.getAvailability())
                            .build();
                },
                (request, serviceComponent) ->
                        serviceComponent.provideUpdateCarActivity().handleRequest(request)

        ) ;
    }


}
