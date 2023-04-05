package com.cydeo.day04_JsonPath;

import com.cydeo.utilities.Spartan_TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.apache.commons.lang3.function.Failable.accept;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P01_SpartanWithJsonPath extends Spartan_TestBase {

    @DisplayName("GET Spartan with Response Path")
    @Test
    void test1() {

        Response response = given()
                .accept(ContentType.JSON).and()
                .pathParam("id", 10).when().get("/api/spartans/{id}");

        response.prettyPrint();

        assertEquals(200, response.statusCode()) ;
         //verify content type
        assertEquals ("application/json", response. contentType()) ;

        // And response payload values match the following:
        //     id is 10,
        //     name is "Lorenza"
        //     gender is "Female",
        //     phone is 3312820936

        System.out.println("response.path(\"id\") = " + response.path("id").toString());
        System.out.println("response.path(\"name\") = " + response.path("name"));

        // we saved our response aas JSONPATH object
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        long phone = jsonPath.getLong("phone");


        //assertion
        assertEquals(10,id);
        assertEquals("Lorenza",name);
        assertEquals("Lorenza",name);


    }
}
