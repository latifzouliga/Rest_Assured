package com.cydeo.day03_Parameters_Path;

import com.cydeo.utilities.Hr_TestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class P02_HrWithParameters extends Hr_TestBase {


    // Given accept type is Json
    // And parameters: g = "{"region_id" :2}"
    // When users sends a GET request to "/countries"
    // Then status code is 200
    // And Content type is application/json
    // And Payload should contain "United States of America"

    @DisplayName("GET- /Countries with Region_id")
    @Test
    void test1() {

        Map<String, Object> queryMap = new HashMap<>();
        //queryMap.put("g",)

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and()
                .queryParam("q", "{\"region_id\":2}")
                .when()
                .get("/countries");

        response.prettyPrint();

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertEquals(ContentType.JSON + "", response.contentType());
        assertTrue(response.asString().contains("United States of America"));


    }
}
