package com.cydeo.api_test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_SpartanTest {

    String spartanBaseURL = "http://174.129.55.241:8000";

    @Test
    public void getAllSpartans() {


        Response response = RestAssured.get(spartanBaseURL + "/api/spartans");

        //Status code
        int statusCode = response.statusCode();
        System.out.println("statusCode = " + statusCode);
        Assertions.assertEquals(200, statusCode);

        // ContentType
        String contentType = response.contentType();
        System.out.println("contentType = " + contentType);
        Assertions.assertEquals("application/json", contentType);

        //Print Body
        System.out.println("***** Response AsString ******");
        System.out.println(response.asString());

        System.out.println("***** Pretty Print ******");
        System.out.println(response.prettyPrint());


    }
}
