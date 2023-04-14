package com.cydeo.day11_JUnit5_DDT;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class P03_ValueSourceTest {

    @ParameterizedTest
    @ValueSource(ints = {10, 50, 20, 40, 30})
    void test1(int number) {
        System.out.println("number = " + number);
        assertTrue(number < 40);
    }

    @ParameterizedTest(name = "{index} - verify name is {0}")
    @ValueSource(strings = {"Mike", "Rose", "Steven", "TJ", "Harold", "Severus", "Sherlock"})
    public void test2(String name) {
        System.out.println("name = " + name);

        assertTrue(name.startsWith("S"));
        assertTrue(name.length() > 4);

    }

    //TASK
    // SEND GET REQUEST TO https://api.zippopotam.us/us/{zipcode}
    // with these zipcodes 22030,22031, 22032, 22033 , 22034, 22035, 22036
    // check status code 200



    @DisplayName("GET /us/{zipcode} with ValueSource")
    @ParameterizedTest(name = "{index} --> Verify zipcode {0}")
    @ValueSource(ints = {22030, 22031, 22032, 22033, 22034, 22035, 22036, 15235})
    void test3(int zipCode) {

        given().accept(ContentType.JSON).log().uri()
                .baseUri("https://api.zippopotam.us")
                .when().pathParam("zipcode", zipCode)
                .and().get("/us/{zipcode}").prettyPeek()
                .then().statusCode(200);
    }
}
