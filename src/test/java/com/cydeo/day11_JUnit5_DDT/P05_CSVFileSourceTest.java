package com.cydeo.day11_JUnit5_DDT;

import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class P05_CSVFileSourceTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/Math.csv", numLinesToSkip = 1)
    public void test1(int num1, int num2, int sum){
        System.out.println(num1);
        System.out.println(num2);
        System.out.println(sum);
    }

    /**
     *    // Write a parameterized test for this request
     *     // Get the data from csv source called as --> zipcode.csv
     *     // state , city , numberOfPlace
     *     // GET https://api.zippopotam.us/us/{state}/{city}
     *     // After getting response numberOfPlaces needs to be same
     *
     *     state , city , numberOfPlace
     *     NY,New York,166
     *     CO,Denver,76
     *     VA,Fairfax,10
     *     MA,Boston,56
     *     MD,Annapolis,9
     */

    @ParameterizedTest
    @CsvFileSource(resources = "/zipCode.csv", numLinesToSkip = 1)
    public void test2(String state, String city, int numberOfPlaces){

        given().accept(ContentType.JSON)
                .baseUri("https://api.zippopotam.us")
                .pathParam("state" ,state)
                .pathParam("city" ,city)
                .when()
                .get("/us/{state}/{city}").prettyPeek()
                .then()
                .statusCode(200)
                .and()
                .body("places", hasSize(numberOfPlaces));

    }


}




















