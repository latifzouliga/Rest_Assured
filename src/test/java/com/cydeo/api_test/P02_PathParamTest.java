package com.cydeo.api_test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class P02_PathParamTest {

    //Given accept type is JSON
    //And ID Parameter values is
    //When user sends GET request /api/spartans/{id}
    //Then response status code should be 200
    //And response content type is application/json

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://174.129.55.241:8000";//api/spartans/:id
    }


    @Test
    public void pathParamTest() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id",3)
                .when().get("/api/spartans/{id}");



        // status code
        int statusCode = response.statusCode();
        System.out.println(statusCode);
        Assertions.assertEquals(200, statusCode);
        Assertions.assertEquals(200, response.statusCode());

        //Response content type is application/json
        Assertions.assertEquals("application/json", response.contentType());
        Assertions.assertEquals(ContentType.JSON.toString(), response.contentType());

        response.prettyPrint();


    }
}
