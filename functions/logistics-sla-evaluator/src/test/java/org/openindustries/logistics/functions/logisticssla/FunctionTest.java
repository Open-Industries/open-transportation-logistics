package org.openindustries.logistics.functions.logisticssla;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class FunctionTest {

    @Test
    void compliant() {
        given().contentType("application/json")
                .body("{\"shipmentId\":\"sh-1\",\"measuredLateHours\":1,\"targetLateHours\":4,\"measuredOnTimePct\":98.0,\"targetOnTimePct\":95.0}")
                .when().post("/logisticsSlaEvaluator")
                .then().statusCode(200).body("compliant", is(true)).body("findings.size()", is(0));
    }

    @Test
    void breachIsReported() {
        given().contentType("application/json")
                .body("{\"shipmentId\":\"sh-2\",\"measuredLateHours\":19,\"targetLateHours\":4,\"measuredOnTimePct\":98.0,\"targetOnTimePct\":95.0}")
                .when().post("/logisticsSlaEvaluator")
                .then().statusCode(200).body("compliant", is(false)).body("findings.size()", is(1));
    }
}
