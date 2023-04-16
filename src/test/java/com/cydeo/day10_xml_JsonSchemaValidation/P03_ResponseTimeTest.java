package com.cydeo.day10_xml_JsonSchemaValidation;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class P03_ResponseTimeTest extends SpartanAuthTestBase {


    @Test
    void name() {
        Response response = given().accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .when().get("/api/spartans")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                //.time(is(lessThan(500L))) // this method is storing the actual response time
                //.and()
                //.time(is(greaterThan(100L)));
                .time(both(greaterThan(200L)).and(lessThan(500L))).extract().response();

        long time = response.getTime();
        System.out.println(time);

        long timeIn = response.getTimeIn(TimeUnit.NANOSECONDS);
        System.out.println(timeIn);

    }
}
