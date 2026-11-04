package org.openindustries.logistics.functions.shipmentrisk;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class FunctionTest {

    @Test
    void healthyIsNormal() {
        given().contentType("application/json")
                .body("{\"shipmentId\":\"sh-1\",\"delayIndex\":6,\"damageIndex\":3,\"routeRiskIndex\":9}")
                .when().post("/shipmentRiskScorer")
                .then().statusCode(200).body("decision", is("NORMAL")).body("score", is(0));
    }

    @Test
    void stackedSignalsAreCritical() {
        given().contentType("application/json")
                .body("{\"shipmentId\":\"sh-2\",\"delayIndex\":83,\"damageIndex\":76,\"routeRiskIndex\":62}")
                .when().post("/shipmentRiskScorer")
                .then().statusCode(200).body("decision", is("CRITICAL")).body("score", is(100));
    }
}
