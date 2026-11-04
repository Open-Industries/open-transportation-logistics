package org.openindustries.logistics.routing;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class RoutingRuleResourceTest {

    @Test
    void activateThenEvaluateAllowed() {
        String id = given().contentType("application/json")
                .body("{\"name\":\"road-standard\",\"mode\":\"ROAD\",\"maxDistanceKm\":2500,\"maxTransitHours\":72,\"priority\":1}")
                .when().post("/api/v1/routing-rules")
                .then().statusCode(201).body("status", is("DRAFT"))
                .extract().path("id");

        given().when().post("/api/v1/routing-rules/" + id + "/activate")
                .then().statusCode(200).body("status", is("ACTIVE"));

        given().contentType("application/json")
                .body("{\"mode\":\"ROAD\",\"requiredMaxTransitHours\":96,\"requiredMaxDistanceKm\":2000}")
                .when().post("/api/v1/routing-rules/evaluate")
                .then().statusCode(200).body("decision", is("ALLOWED"));
    }

    @Test
    void evaluateWithoutRuleIsDenied() {
        given().contentType("application/json")
                .body("{\"mode\":\"AIR\",\"requiredMaxTransitHours\":96,\"requiredMaxDistanceKm\":2000}")
                .when().post("/api/v1/routing-rules/evaluate")
                .then().statusCode(200).body("decision", is("DENIED"));
    }
}
