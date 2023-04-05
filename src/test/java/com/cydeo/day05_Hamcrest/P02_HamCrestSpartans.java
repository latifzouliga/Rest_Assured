package com.cydeo.day05_Hamcrest;

import com.cydeo.utilities.Spartan_TestBase;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class P02_HamCrestSpartans extends Spartan_TestBase {

    /**
     * Given accept type is Json
     * And path param id is 15
     * When user sends a get request to api/spartans/{id}
     * Then status code is 200
     * And content type is Json
     * And json data has following:
     * "id": 15,
     * "name": "Meta",
     * "gender": "Female",
     * "phone": 1938695106
     */


    @DisplayName("GET single spartan with HamCrest")
    @Test
    void test1() {

        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 15)
                .when()
                .get("api/spartans/{id}")
                .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body("id", is(15))
                .and()
                .body("name", is("Meta"))
                .and()
                .body("gender", is("Female"))
                .and()
                .body("phone", is(1938695106));
    }


    // without synthetic sugar
    @DisplayName("GET single spartan with HamCrest without synthetic sugar")
    @Test
    void test2() {

        given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .get("api/spartans/{id}")
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", is(15))
                .body("name", is("Meta"))
                .body("gender", is("Female"))
                .body("phone", is(1938695106));
    }

    @DisplayName("GET single spartan with HamCrest with logs")
    @Test
    void test3() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .log().ifValidationFails(LogDetail.HEADERS) // to see request information if validation fails
                //.log().all()
                .when().get("api/spartans/{id}")
                //.prettyPeek()   // prints response object and still allows us to chain the methods
                //.prettyPrint() // prettyPrint does not work her, it breaks then() method
                .then().statusCode(200)
                //.log().all()
                .log().ifValidationFails() //// to see response information if validation fails
                .and().contentType(ContentType.JSON)
                .and().body("id", is(15))
                .and().body("name", is("Meta"))
                .and().body("gender", is("Female"))
                .and().body("phone", is(1938695106))
                .extract().response();


        int id = response.path("id");

        assertThat(id, is(15));

    }

    @DisplayName("GET single spartan with HamCrest with logs")
    @Test
    void test4() {

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .log().ifValidationFails(LogDetail.HEADERS) // to see request information if validation fails
                //.log().all()
                .when().get("api/spartans/{id}")
                // verification start from this line
                //.prettyPeek()   // prints response object and still allows us to chain the methods
                //.prettyPrint() // prettyPrint does not work her, it breaks then() method
                .then().statusCode(200)
                //.log().all()
                .log().ifValidationFails() //// to see response information if validation fails
                .and().contentType(ContentType.JSON)
                .and().body("id", is(15))
                .and().body("name", is("Meta"))
                .and().body("gender", is("Female"))
                .and().body("phone", is(1938695106))
                .extract().jsonPath();

        // all the verification is done in the above lines, but we still need an object to extract some data

        // Why we need to extract, while we can complete all of verification (status code, header, body) with then () and hamcrest mathcers
        // -Assume that we are going to do verification against D/UI. In that case I need to get data from API
        // After completing my api verification
        // So we need to sometimes list of names / ids / whatever field we have to check against to db or UI
        // That is why we need to initialize as Response or JsonPath to get related data from api that we want

        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");

        // hamCrest assertion
        assertThat(id, is(15));
        assertThat(name, is(equalTo("Meta")));
        assertThat(gender, is(equalTo("Female")));
        assertThat(phone, is(equalTo(1938695106L)));

        // JUnit 5 assertion
        Assertions.assertEquals(15, id);
        Assertions.assertEquals(jsonPath.getString("name"), name);
    }
}
