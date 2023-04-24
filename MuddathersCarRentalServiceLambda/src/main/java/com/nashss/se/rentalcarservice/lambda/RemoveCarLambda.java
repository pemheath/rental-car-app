package com.nashss.se.rentalcarservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.rentalcarservice.activity.requests.GetCarRequest;
import com.nashss.se.rentalcarservice.activity.requests.RemoveCarRequest;
import com.nashss.se.rentalcarservice.activity.results.GetCarResult;
import com.nashss.se.rentalcarservice.activity.results.RemoveCarResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RemoveCarLambda extends LambdaActivityRunner<RemoveCarRequest, RemoveCarResult>
        implements RequestHandler<AuthenticatedLambdaRequest<RemoveCarRequest>, LambdaResponse> {
    private final Logger log = LogManager.getLogger();
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<RemoveCarRequest> input, Context context) {
        //takes data coming in as input , converts that data into a request object in your activity (GetCarRequest)
        log.info("handleRequest");
        return super.runActivity( //this returns lambda response
                () -> input.fromPath(path ->
                        RemoveCarRequest.builder()
                                .withVIN(path.get("vin"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideRemoveCarActivity().handleRequest(request)
                // get the activity using dagger, call the activity with the request
        );
    }
}
