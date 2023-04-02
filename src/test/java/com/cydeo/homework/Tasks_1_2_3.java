package com.cydeo.homework;

import com.cydeo.utilities.Hr_TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class Tasks_1_2_3 extends Hr_TestBase {

    @DisplayName("GET- /countries/US")
    @Test
    void test1() {
        //Task 1 :
        //- Given accept type is Json
        //- When users sends request to /countries/US
        //- Then status code is 200
        //- And Content - Type is application/json
        //- And response contains United States of America

        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("/countries/US");

        assertEquals(HttpStatus.SC_OK,response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        assertEquals("United States of America",response.path("country_name"));


    }

    @DisplayName("GET- /employees/1")
    @Test
    void test2() {
        //Task 2 :
        //- Given accept type is Json
        //- When users sends request to /employees/1
        //- Then status code is 404
        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/employees/1");

        assertEquals(HttpStatus.SC_NOT_FOUND,response.statusCode());

    }

    @DisplayName("GET- /regions/1")
    @Test
    public void test3() {
        //Task 3 :
        //- Given accept type is Json
        //- When users sends request to /regions/1
        //- Then status code is 200
        //- And Content - Type is application/json
        //- And response contains Europe
        //- And header should contains Date
        //- And Transfer-Encoding should be chunked
        Response response = given().accept(ContentType.JSON)
                .when()
                .pathParam("id", 1)
                .get("/regions/{id}");

        assertEquals(HttpStatus.SC_OK,response.statusCode());
        assertEquals(ContentType.JSON.toString(),response.getContentType());
        assertEquals("Europe",response.body().path("region_name"));
        assertTrue(response.headers().hasHeaderWithName("Date"));
        assertEquals("chunked",response.header("Transfer-Encoding"));


    }
}




















