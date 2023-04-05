package com.cydeo.day03_Parameters_Path;

import com.cydeo.utilities.Hr_TestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class P04_HrWithResponsePath extends Hr_TestBase {


    @DisplayName("GET Request to countries with using Response Path")
    @Test
    public void test1() {
        Response response = RestAssured.
                given()
                .accept(ContentType.JSON)
                .and()
                .queryParam("q", "{\"region_id\":2}")
                .when()
                .get("/countries");

        String firstLink = response.path("items[0].links[0].href");
        System.out.println(firstLink);

        //   print limit
        //   print hasMore
        //   print second country name
        //   print 4th element country name
        //   print 3th element href
        //   get all country names

        int limit = response.path("limit");
        boolean hasMore = response.path("hasMore");
        String second_country_name = response.path("items[1].country_name");
        String forth_country_name = response.path("items[3].country_name");
        String third_href = response.path("items[2].links[0].href");
        List<String> allCountries = response.path("items.country_name");


        System.out.println("limit = " + limit);                                 // 25
        System.out.println("hasMore = " + hasMore);                             // false
        System.out.println("second_country_name = " + second_country_name);     // Brazil
        System.out.println("forth_country_name = " + forth_country_name);       // Mexico
        System.out.println("third_href = " + third_href);        // "http://174.129.55.241:1000/ords/hr/countries/CA"
        System.out.println("all Countries = " + allCountries);   // [Argentina, Brazil, Canada, Mexico, United States of America]

        // Verify all region_ids equals 2
        List<Integer> region_ids = response.path("items.region_id");

        for (Integer each : region_ids) {
            Assertions.assertEquals(2, each);
        }

        System.out.println("href in links array = " + response.path("links[2]"));


    }
}
