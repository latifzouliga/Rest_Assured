package com.cydeo.day11_JUnit5_DDT;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class P04_CsvSource {

    @DisplayName("CsvSource")
    @ParameterizedTest(name = "{index} ---> {0} + {1} = {2}")
    @CsvSource({"1,3,4",
            "2,3,5",
            "4,10,14",
            "10,20,30"})
    void test1(int num1, int num2, int sum) {

        System.out.println(num1 + " + " + num2 + " = " + sum);
        assertEquals(num1 + num2, sum);

    }

    //TASK
    // Write a parameterized test for this request
    // GET https://api.zippopotam.us/us/{state}/{city}
    /*
        "NY, New York",
        "CO, Denver",
        "VA, Fairfax",
        "MA, Boston",
        "MD, Annapolis"
     */
    //verify each of the place name contains your city name
    //print number of places for each request

    // TODO: Create a pojo class and deserialize places
    @DisplayName("CSV source test with ")
    @ParameterizedTest
    @CsvSource({"NY, New York",
                "CO, Denver",
                "VA, Fairfax",
                "MA, Boston",
                "MD, Annapolis"})
    public void test2(String state, String city) {

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .when()
                .pathParam("state", state)
                .pathParam("city", city)
                .and()
                .get("https://api.zippopotam.us/us/{state}/{city}")//.prettyPeek()
                .then()
                .body("places.'place name'", everyItem(containsString(city))) // first approach verification
                .statusCode(200).extract().jsonPath();

        System.out.println("===========================");
        System.out.println("First place name = " + jsonPath.getString("places[0]['place name']"));

        List<String> placesList = jsonPath.getList("places['place name']");

        //verify each of the place name contains your city name

        // first approach verification
        for (String eachCity : placesList) {
            assertTrue(eachCity.contains(city));
        }


        // first approach verification
        placesList.forEach(p -> assertTrue(p.contains(city)));

        //print number of places for each request
        System.out.println(state + " " + city + " has " + placesList.size() + " places");

    }
}




















