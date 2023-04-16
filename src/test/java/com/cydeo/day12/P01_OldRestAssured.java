package com.cydeo.day12;

import com.cydeo.utilities.SpartanNewTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class P01_OldRestAssured extends SpartanNewTestBase {

    @Test
    void test1() {
        given()
                .accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .when()
                .get("/spartans").prettyPeek()
                .then()
                .statusCode(200)
                .body("id[0]", is(1))
                .body("id[-1]", is(100));


        // RequestSpecification: given = it is the request part
        // Response: when = Action part
        // Validatable response: then = it is the assertion part
    }

    @Test
    void getSpartansOldWay() {


        given().accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .expect()
                .statusCode(200)
                .body("id[0]", is(1))
                .body("id[-1]", is(100))
                .when()
                .get("/spartans");

        // RequestSpecification: given
        // ResponseSpecification: expect
        // RequestRender: when


    }
}


















