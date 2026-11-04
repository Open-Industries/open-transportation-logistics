package org.openindustries.logistics.fleet;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@Path("/api/v1/fleet-units")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Fleet Units", description = "Fleet unit catalogue by type and capacity.")
public class VehicleUnitResource {

    @Inject
    VehicleUnitService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a vehicleunit")
    public Response create(@Valid CreateVehicleUnitRequest request) {
        return Response.status(Response.Status.CREATED).entity(service.create(request)).build();
    }

    @GET
    @Operation(summary = "List all")
    public List<VehicleUnit> list() {
        return service.list();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get one by id")
    public VehicleUnit get(@PathParam("id") UUID id) {
        return service.get(id);
    }

    @POST
    @Path("/{id}/deploy")
    @Operation(summary = "Deploy (lifecycle transition, no body)")
    public VehicleUnit deploy(@PathParam("id") UUID id) {
        return service.deploy(id);
    }
}
