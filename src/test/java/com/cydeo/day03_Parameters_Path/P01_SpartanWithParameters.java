package com.cydeo.day03_Parameters_Path;

import com.cydeo.utilities.Spartan_TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class P01_SpartanWithParameters extends Spartan_TestBase {

    // Given accept type is Json
    // And Id parameter value is 24
    // When user Sends GET request to /api/spartans/{id}
    // Then response status code should be 200
    // And response content-type: application/json
    // And "Julio" should be in response payload (body)


    @DisplayName("GET- Spartans /api/spartans/{id}")
    @Test
    void test1() {

        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 24)
                .when()
                .get("/api/spartans/{id}");

        response.prettyPrint();

        // Then response status code should be 200
        assertEquals(200, response.statusCode());

        // And response content-type: application/json
        assertEquals(ContentType.JSON.toString(), response.contentType());

        // And "Julio" should be in response payload (body)
        assertTrue(response.asString().contains("Julio"));


    }

    // TASK
    // Given accept type is Json
    // And Id parameter value is 500
    // When user sends GET request to /api/spartans/{id}
    // Then response status code should be 404
    // And response content-type: application/json
    // And "Not Found' message should be in response payload

    @DisplayName("GET- /api/spartans/{id}")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 500)
                .when().get("/api/spartans/{id}");

        response.prettyPrint();
        // Then response status code should be 404
        assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());

        // And response content-type: application/json
        assertEquals(ContentType.JSON + "", response.contentType());

        // And "Not Found' message should be in response payload
        assertTrue(response.body().asString().contains("Not Found"));


    }


    // Given Accept type is Json
    // And query parameter values are:
    //       gender |Female
    //       name Contains | e
    // When user sends GET request to /api/spartans/search
    // Then response status code should be 200
    // And response content-type: application/json
    // And "Female" should be in response payload
    // And "Janette" should be in response payload

    @DisplayName("GET Request to /api/spartans/search with Query Params")
    @Test
    public void test3() {

        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("gender", "Female")
                .and()
                .queryParam("nameContains", "e")
                .when()
                .get("/api/spartans/search");

        System.out.println(response.body().asString());
        //response.prettyPrint();

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON + "", response.contentType());
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.asString().contains("Janette"));

        // Contains is not the proper way to verify something in the response. We will learn different method to ger specific data


    }

    @DisplayName("GET Request to /api/spartans/search with Query Params")
    @Test
    public void test4() {

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("gender", "Male");
        queryMap.put("nameContains", "k");



        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParams(queryMap)
                .when()
                .get("/api/spartans/search");

        System.out.println(response.body().asString());
        //response.prettyPrint();

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON + "", response.contentType());
        assertTrue(response.body().asString().contains("Male"));
        assertTrue(response.asString().contains("Mark"));

        // Contains is not the proper way to verify something in the response. We will learn different method to ger specific data


    }


}
