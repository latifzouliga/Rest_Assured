package com.cydeo.day06_Deserialization;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class P01_HRDeserialization_Map_List extends HrTestBase {
    /**
     * Create a test called getLocation
     * 1. Send request to GET /locations
     * 2. log uri to see
     * 3. Get all Json as a map and print out screen all the things with the help of  map
     * System.out.println("====== GET FIRST LOCATION  ======");
     * System.out.println("====== GET FIRST LOCATION LINKS  ======");
     * System.out.println("====== GET ALL LOCATIONS AS LIST OF MAP======");
     * System.out.println("====== FIRST LOCATION ======");
     * System.out.println("====== FIRST LOCATION ID ======");
     * System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
     * System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");
     */

    @DisplayName("GET /locations to deserialization into Java Collections")
    @Test
    void test1() {
        Response response = given()
                .accept(ContentType.JSON)
                .get("/locations")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        System.out.println("====== GET FIRST LOCATION  ======");
        //response.as("items[0]",Map.class) --> there is no method like this, that is why as() method is not enough
        Map<Object, Object> firstLocation = jsonPath.getMap("items[0]");
        System.out.println("firstLocation.get(\"location_id\") = " + firstLocation.get("location_id"));

        System.out.println("====== GET FIRST LOCATION LINKS  ======");
        Map<Object, Object> map = jsonPath.getMap("items[0].links[0]");
        System.out.println(map);


        System.out.println("====== GET ALL LOCATIONS AS LIST OF MAP======");
        //List<Map<String ,Object>> listOfMap = response.as(List.class); // can not convert to list of maps
        List<Map<String,Object>> items = jsonPath.getList("items"); // we need to use jsonPath
        items.forEach(System.out::println);

        System.out.println("====== FIRST LOCATION ======");
        System.out.println("items.get(0) = " + items.get(0));

        System.out.println("====== FIRST LOCATION ID ======");
        System.out.println("items.get(0).get(\"location_id\") = " + items.get(0).get("location_id"));

        System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
        System.out.println("items.get(0).get(\"country_id\") = " + items.get(0).get("country_id"));

        System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");
        List<Map<String,String>> links = (List) items.get(0).get("links");
        System.out.println("link = " + links.get(0).get("href"));

    }
}
