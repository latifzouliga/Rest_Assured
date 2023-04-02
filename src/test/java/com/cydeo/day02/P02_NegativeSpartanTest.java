package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_NegativeSpartanTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://174.129.55.241:8000";

    }

    // Given accept content type is application/json
    // When User sends GET request /api/spartans endpoint
    // Then status code should be 200

    @DisplayName("GET- All spartans")
    @Test
    void getAllSpartans() {
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans");

        // Then status code should be 200
        assertEquals(200, response.statusCode());

        response.prettyPrint();
    }


    // ****** Negative Test Case *******
    // Given Accept type application/ xml
    // When user send GET request to /api/spartans/10 end point
    // Then status code must be 406
    // And response Content Type must be application/ml;charset=UTF
    @DisplayName("GET- All Spartans - Accept, application/xml - 406")
    @Test
    public void xmlTest() {
        Response response = given()
                .accept(ContentType.XML)
                .when()
                .get("/api/spartans/10");

        // Then status code must be 406
        assertEquals(406, response.statusCode());
        System.out.println(response.statusCode());

        // And response Content Type must be application/ml;charset=UTF
        assertEquals("application/xml;charset=UTF-8",response.contentType());
        System.out.println(response.contentType());

    }
}
