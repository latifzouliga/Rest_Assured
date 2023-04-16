package com.cydeo.day03_Parameters_Path;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class P03_SpartanWithResponsePath extends SpartanTestBase {


    // Given accept type is json
    // And path param id is 10
    // When user sends a get request to "api/spartans/{id}"
    // Then status code is 200
    // And content-type is "application/json"
    // And response payload values match the following:
    //     id is 10,
    //     name is "Lorenza"
    //     gender is "Female",
    //     phone is 3312820936
    @DisplayName("GET spartan with response Path")
    @Test
    public void test1(){

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 10)
                .when()
                .get("api/spartans/{id}");

        response.prettyPrint();
        assertEquals(ContentType.JSON+"",response.contentType());
        assertEquals(HttpStatus.SC_OK, response.statusCode());

        int id = response.path("id");
        long phone = response.path("phone");
        String name = response.path("name");
        String gender = response.path("gender");

        assertEquals(10,id);
        assertEquals("Lorenza",name);
        assertEquals("Female",gender);
        assertEquals(3312820936L,phone);

    }

    @DisplayName("GET All Spartans")
    @Test
    void test2() {

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .get("/api/spartans");


        int firstId = response.path("[0].id");
        System.out.println("firstId = " + firstId);

        int idFirst = response.path("id[0]");
        System.out.println("idFirst = " + idFirst);

        System.out.println("[0].name = " + response.path("[0].name"));
        System.out.println("name[0] = " + response.path("name[0]"));
     
        // get me the last name
        // name[-1] --> refers to the last name in the list
        System.out.println("the last name = " + response.path("name[-1]"));
        
        // the second last name
        System.out.println("the second last name = " + response.path("name[-2]"));
        
        // get all name
        // for getting all name, we need to provide the name only
        System.out.println("<<<<<<<<<< All Names >>>>>>>>>>");
        List<String> allNames = response.path("name");
        for (String each : allNames) {
            System.out.println(each);
        }


    }
}
