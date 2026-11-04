package org.openindustries.logistics.shipment;

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

@Path("/api/v1/shipments")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Shipments", description = "Shipment registry; the SSCC is masked at creation.")
public class ShipmentResource {

    @Inject
    ShipmentService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a shipment")
    public Response create(@Valid CreateShipmentRequest request) {
        return Response.status(Response.Status.CREATED).entity(service.create(request)).build();
    }

    @GET
    @Operation(summary = "List all")
    public List<Shipment> list() {
        return service.list();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get one by id")
    public Shipment get(@PathParam("id") UUID id) {
        return service.get(id);
    }

    @POST
    @Path("/{id}/dispatch")
    @Operation(summary = "Dispatch this shipment (lifecycle transition, no body)")
    public Shipment dispatch(@PathParam("id") UUID id) {
        return service.dispatch(id);
    }

    @POST
    @Path("/{id}/deliver")
    @Operation(summary = "Deliver this shipment (lifecycle transition, no body)")
    public Shipment deliver(@PathParam("id") UUID id) {
        return service.deliver(id);
    }
}
