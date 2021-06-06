package com.sqills.api.resource;

import com.sqills.api.model.SqillsModel;
import com.sqills.service.InputProductionService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Endpoint to receive input from user
 *
 * @author mert.tutuncu
 */
@Produces(MediaType.APPLICATION_JSON)
@Path("/api")
public class SqillsResource {

    @Inject
    InputProductionService inputProductionService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postMethod(@Valid @NotNull SqillsModel model) {

        inputProductionService.processInput(model);
        return Response.ok().build();
    }
}