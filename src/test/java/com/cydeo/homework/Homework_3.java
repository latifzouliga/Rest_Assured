package com.cydeo.homework;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Homework_3 {

    /**
     * FormulaAPI INFO --> http://ergast.com/mrd/methods/drivers/
     * - It's for historical formula one race information
     * - In this particular api , it decided to give you ml by default for response
     * type and In this particular api , it decided to give you json if you add .json
     * at the end of url
     * FOR EXAMPLE
     * - http://ergast.com/api/f1/drivers.json ---> return JSON
     * BASE URL —> http://ergast.com/api/f1/
     * TASK 1 : Solve same task with 4 different way
     * <p>
     * - Given accept type is json
     * - And path param driverId is alonso
     * - When user send request /drivers/{driverId}.json
     * - Then verify status code is 200
     * - And content type is application/json; charset=utf-8
     * - And total is 1
     * - And givenName is Fernando
     * - And familyName is Alonso
     * - And nationality is Spanish
     * - Use JSONPATH
     * int total = jsonpath.getInt(“pathOfField”)
     * - Use HAMCREST MATCHERS
     * then().body(..........)
     * <p>
     * <p>
     * Print givenName of Driver by using extract method after HamCrest
     * - Convert Driver information to Java Structure
     * Map<String,Object> driverMap=jsonpath.getMap(“pathOfDriver”)
     * - Convert Driver information POJO Class
     * Driver driver=getObject(“pathOfDriver”,Driver.class)
     */

    @Test
    public void task1() {
        /**
         * - Given accept type is json
         * - And path param driverId is alonso
         * - When user send request /drivers/{driverId}.json
         * - Then verify status code is 200
         * - And content type is application/json; charset=utf-8
         *  - And total is 1
         * - And givenName is Fernando
         * - And familyName is Alonso
         * - And nationality is Spanish
         * - Use JSONPATH
         * int total = jsonpath.getInt(“pathOfField”)
         * - Use HAMCREST MATCHERS
         * then().body(..........)
         */

        Response response = given().accept(ContentType.JSON)
                .when()
                .pathParam("id", "alonso")
                .and()
                .get("http://ergast.com/api/f1/drivers/{id}.json")
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract().response();


        // * - And content type is application/json; charset=utf-8
        String content_type = response.header("Content-type");
        String total = response.path("MRData.total");

        String driverId = response.path("MRData.DriverTable.Drivers[0].driverId");
        String firstName = response.path("MRData.DriverTable.Drivers[0].givenName");
        String familyName = response.path("MRData.DriverTable.Drivers[0].familyName");
        String nationality = response.path("MRData.DriverTable.Drivers[0].nationality");

        assertThat("application/json; charset=utf-8",is(content_type));
        assertThat("alonso",is(driverId));

        // *  - And total is 1
        assertEquals("1", total);

        // * - And givenName is Fernando
        assertEquals("Fernando", firstName);

        // * - And familyName is Alonso
        assertEquals("Alonso", familyName);

        // * - And nationality is Spanish
        assertEquals("Spanish", nationality);

        // * - Use JSONPATH
        // * int total = jsonpath.getInt(“pathOfField”)
        // * - Use HAMCREST MATCHERS


    }


    @Test
    public void test2() {
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .when()
                .pathParam("id", "alonso")
                .and()
                .get("http://ergast.com/api/f1/drivers/{id}.json")
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract().jsonPath();

        //*  - And total is 1
        String total = jsonPath.getString("MRData.total");
        // * - And givenName is Fernando
        String givenName = jsonPath.getString("MRData.DriverTable.Drivers[0].givenName");
        // * - And familyName is Alonso
        String familyName = jsonPath.getString("MRData.DriverTable.Drivers[0].familyName");
        // * - And nationality is Spanish
        String nationality = jsonPath.getString("MRData.DriverTable.Drivers[0].nationality");

        assertThat("1", is(total));
        assertThat("Fernando", is(givenName));
        assertThat("Alonso", is(familyName));
        assertThat("Spanish", is(nationality));

    }


    @Test
    void test3() {

        // *  - And total is 1
        // * - And givenName is Fernando
        // * - And familyName is Alonso
        // * - And nationality is Spanish
        given().accept(ContentType.JSON)
                .when()
                .pathParam("id", "alonso")
                .and()
                .get("http://ergast.com/api/f1/drivers/{id}.json")
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("MRData.DriverTable.Drivers[0].givenName",is("Fernando"))
                .body("MRData.DriverTable.Drivers[0].familyName",is("Alonso"))
                .body("MRData.DriverTable.Drivers[0].nationality",is("Spanish"))
                .body("MRData.total",is("Fernando"));






    }
}




























