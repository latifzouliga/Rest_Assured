package com.cydeo.homework;

import com.cydeo.pojo.Spartan;
import com.cydeo.pojo.region.Items;
import com.cydeo.utilities.Hr_TestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


public class homework_5 extends Hr_TestBase {

    /**
     * ORDS API DOCUMENT
     * —> https://documenter.getpostman.com/view/25449093/2s8ZDYYNBz
     * TASK 1:
     * —> POST a region then do GET same region to do validations.
     * Please use Map or POJO class, or JsonPath
     * Given accept is json
     * And content type is json
     * When I send post request to "/regions/" With json:
     * {
     * "region_id":100,
     * "region_name":"Test Region"
     * }
     * Then status code is 201
     * And content type is json
     * And region_id is 100
     * And region_name is Test Region
     * —> GET
     * Given accept is json
     * When I send GET request to "/regions/100"
     * Then status code is 200
     * And content type is json
     * And region_id is 100
     * And region_name is Test Region
     */
    static int regionId;
    static String region_name;

    @DisplayName("GET /regions")
    @Test
    @Order(1)
    void test1() {
        given().accept(ContentType.JSON)
                .and()
                .get("/regions")
                .then()
                .contentType(ContentType.JSON)
                .extract().response();

    }


    @DisplayName("POST /regions")
    @Test
    @Order(2)
    void test2() {

//        Items items = new Items();
//        items.setRegionId(103);
//        items.setRegionName("9arra 7arra Marra");

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("region_id", 105);
        map.put("region_name", "9arra 7arra Marra");

        regionId = (int) map.get("region_id");
        region_name = (String) map.get("region_name");

//        region_name = items.getRegionName();
//        regionId = items.getRegionId();


        given().contentType(ContentType.JSON)
                .when()
                 .body(map)     //getting the values from Map
              //  .body(items)   // getting the values from POJO
                .and()
                .post("/regions/")
                .prettyPeek()
                .then()
                .statusCode(201);

    }

    @DisplayName("PUT /regions/{id}")
    @Test
    @Order(3)
    void test3(){

        Items item = new Items();
        item.setRegionName("Wooden Spoon");
        item.setRegionId(regionId);

        given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().pathParam("id",regionId)
//                .and().body("{\n" +
//                        "\"region_id\": 100,\n" +
//                        "\"region_name\": \"Wooden Region\" \n" +
//                        "}")
                .body(item)
                .and().put("/regions/{id}")
                .then().statusCode(200);

    }


    @DisplayName("GET /regions/{id}")
    @Test
    @Order(4)
    void test4() {
        given().accept(ContentType.JSON)
                .when()
                .pathParam("id", regionId)
                .and()
                .get("/regions/{id}")
                .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body("region_id",is(regionId))
                .body("region_name", is(region_name));
    }

    @DisplayName("DELETE /regions/{id}")
    @Test
    @Order(5)
    void test5(){
        given().pathParam("id",regionId)
                .delete("/regions/{id}")
                .then()
                .statusCode(200);
    }


}











