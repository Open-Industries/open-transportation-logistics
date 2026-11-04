package org.openindustries.logistics.capacity;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class CapacityResourceTest {

    @Test
    void consumptionDrawsDown() {
        String id = given().contentType("application/json")
                .body("{\"laneCode\":\"MX-US-TX1\",\"capacityKg\":24000}")
                .when().post("/api/v1/lane-capacity")
                .then().statusCode(201).body("capacityKg", is(24000))
                .extract().path("id");

        given().contentType("application/json")
                .body("{\"bookingType\":\"LTL\",\"kg\":6000}")
                .when().post("/api/v1/lane-capacity/" + id + "/bookings")
                .then().statusCode(200)
                .body("remainingAfter", is(18000));
    }

    @Test
    void overdrawIsRejected() {
        String id = given().contentType("application/json")
                .body("{\"laneCode\":\"MX-US-TX1\",\"capacityKg\":24000}")
                .when().post("/api/v1/lane-capacity")
                .then().statusCode(201)
                .extract().path("id");

        given().contentType("application/json")
                .body("{\"bookingType\":\"LTL\",\"kg\":60000}")
                .when().post("/api/v1/lane-capacity/" + id + "/bookings")
                .then().statusCode(422).body("status", is(422));

        given().when().get("/api/v1/lane-capacity/" + id)
                .then().statusCode(200).body("capacityKg", is(24000));
    }
}
