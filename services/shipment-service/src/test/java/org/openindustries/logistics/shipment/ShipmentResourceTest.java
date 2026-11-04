package org.openindustries.logistics.shipment;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ShipmentResourceTest {

    @Test
    void createAndFetch() {
        String id = given().contentType("application/json")
                .body("{\"mode\":\"ROAD\",\"ssccCode\":\"SSCC-095211015300001234\",\"laneCode\":\"MX-US-TX1\"}")
                .when().post("/api/v1/shipments")
                .then().statusCode(201)
                .body("status", is("CREATED"))
                .body("ssccCodeMasked", endsWith("1234"))
                .extract().path("id");

        given().when().get("/api/v1/shipments/" + id)
                .then().statusCode(200).body("status", is("CREATED"));
    }

    @Test
    void transitionWithoutBody() {
        // Lifecycle transitions take no body and must not require a Content-Type (no 415).
        String id = given().contentType("application/json")
                .body("{\"mode\":\"ROAD\",\"ssccCode\":\"SSCC-095211015300001234\",\"laneCode\":\"MX-US-TX1\"}")
                .when().post("/api/v1/shipments")
                .then().statusCode(201)
                .extract().path("id");

        given().when().post("/api/v1/shipments/" + id + "/dispatch")
                .then().statusCode(200).body("status", is("IN_TRANSIT"));
    }
}
