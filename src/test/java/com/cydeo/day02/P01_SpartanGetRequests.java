package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_SpartanGetRequests {

    String url = "http://174.129.55.241:8000";

    //* Given content type is application/son
    //* When User sends GET request /api/spartans endpoint
    //* Then status code should be 200
    //* And Content type should be application/json


    @Test
    public void getAllSpartans() {

        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get(url + "/api/spartans");

        //* Then status code should be 200
        System.out.println("statusCode = " + response.statusCode());
        Assertions.assertEquals(200, response.statusCode());

        //* And Content type should be application/json
        System.out.println("contentType = " + response.contentType());
        Assertions.assertEquals(ContentType.JSON.toString(), response.contentType());
        //response.prettyPrint();

        // how to get connection header value?
        // if we want to get any response header value, we can use ("headerName")
        //method from response object. it will return header value as string
        System.out.println("Content-type = " + response.header("Content-type"));
        System.out.println("connection = " + response.header("Connection"));
        System.out.println("date = " + response.header("Date"));

        // how to verify header exist?
        // hasHeaderWithName method help us to verify header exist or not
        // it is useful for dynamic header values like date, we are only verifying header exist or not
        boolean isDate = response.headers().hasHeaderWithName("Date");
        Assertions.assertTrue(isDate);


    }


    //* Given content type is application/son
    //* When user sends GET request /api/spartans/3 endpoint
    //* Then status code should be 200
    //• And Content type should be application/json
    //* And response body needs to contains Fidole

    @Test
    void getSpartan() {

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get(url + "/api/spartans/3");

        //* Then status code should be 200
        Assertions.assertEquals(200, response.statusCode());

        //* And Content type should be application/json
        Assertions.assertEquals(ContentType.JSON.toString(), response.contentType());
        Assertions.assertEquals(ContentType.JSON.toString(), response.getContentType());
        Assertions.assertEquals(ContentType.JSON.toString(), response.header("Content-Type"));

        //* And response body needs to contain Fidole
        Assertions.assertTrue(response.asString().contains("Fidole"));

        response.prettyPrint();

        // verify body contains "Fidole"
        // This is not a good way to make assertion. In this way we are just converting response to String
        // and with the help of String contains we are just looking into Response.But we should be able to get jso
        //"name" key value then verify that one is equal to "Fidole"
        System.out.println(response.body().asString());
        System.out.println(response.asString());



    }

    // Given no headers provided
    // When Users send GET request to /api/hello
    // Then response status code should be 200
    // And Content type header should be "text/plain; charset=UTF-8"
    // And header should contain Date And Content-Length should be 17
    // And body should be "Hello from Sparta"


    @Test
    void helloSpartan() {

        Response response = RestAssured.when().get(url + "/api/hello");

        // Then response status code should be 200
        Assertions.assertEquals(200,response.statusCode());

        // And Content type header should be "text/plain; charset=UTF-8"
        Assertions.assertEquals(ContentType.TEXT+";charset=UTF-8", response.contentType());


        // And header should contain Date
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        // And Content-Length should be 17
        Assertions.assertEquals("17",response.header("Content-Length"));

        // And body should be "Hello from Sparta"
        Assertions.assertEquals("Hello from Sparta",response.body().asString());

        System.out.println(response.headers().asList());


    }
}


































