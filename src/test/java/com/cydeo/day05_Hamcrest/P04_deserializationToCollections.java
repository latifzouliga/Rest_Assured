package com.cydeo.day05_Hamcrest;

import com.cydeo.utilities.Spartan_TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class P04_deserializationToCollections extends Spartan_TestBase {

    /**
     * Given accept type is application/json
     * And Path param id = 10
     * When I send GET request to /api/spartans
     * Then status code is 200
     * And content type is json
     * And spartan data matching:
     * id > 10
     * name>Lorenza
     * gender >Female
     * phone >3312820936
     */

    @Test
    void test1() {

        Response response = given().accept(ContentType.JSON)
                .when().pathParam("id", 10)
                .and().get("/api/spartans/{id}")
                .then().statusCode(HttpStatus.SC_OK)
                .and().contentType(ContentType.JSON.toString())
                .extract().response();


        Map<String, Object> spartanMap = response.as(Map.class);
        System.out.println(spartanMap);

        int id = (int) spartanMap.get("id");
        String name = (String) spartanMap.get("name");

        JsonPath jsonPath = response.jsonPath();
        Map<String, Object> jsonMap = jsonPath.get("");
        System.out.println("map: " +jsonMap);
    }

    @DisplayName("GET All Spartans with Java Collections")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .and().get("/api/spartans")
                .then().statusCode(HttpStatus.SC_OK)
                .and().contentType(ContentType.JSON.toString())
                .extract().response();

        // Approach 1 -- With response object

        List<Map<String, Object>> spartanList = response.as(List.class);
        System.out.println("name = " + spartanList.get(0).get("name"));
        //spartanList.forEach(System.out::println);

        // Approach 2 -- With response object
        JsonPath jsonPath = response.jsonPath();
        List<Map<String, Object>> spartanList1 = jsonPath.getList("");
        System.out.println("spartanList1 = " + spartanList1.get(0));


    }
}











