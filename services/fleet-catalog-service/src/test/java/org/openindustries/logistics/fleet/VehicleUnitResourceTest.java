package org.openindustries.logistics.fleet;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class VehicleUnitResourceTest {

    @Test
    void createAndTransition() {
        String id = given().contentType("application/json")
                .body("{\"name\":\"Truck T-450\",\"vehicleType\":\"TRUCK\",\"capacityKg\":12000}")
                .when().post("/api/v1/fleet-units")
                .then().statusCode(201)
                .body("status", is("AVAILABLE"))
                .body("vehicleType", is("TRUCK"))
                .extract().path("id");

        given().when().post("/api/v1/fleet-units/" + id + "/deploy")
                .then().statusCode(200).body("status", is("DEPLOYED"));
    }
}
