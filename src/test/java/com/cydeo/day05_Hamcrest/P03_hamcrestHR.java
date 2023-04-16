package com.cydeo.day05_Hamcrest;


import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class P03_hamcrestHR extends HrTestBase {

    /**
     * Given accept type is Json
     * And parameters: q = {"job_id":"IT_PROG"}
     * When users sends a GET request to "/employees"
     * Then status code is 200
     * And Content type is application/json
     * Verify
     * - each employees has manager
     * - each employees working as IT_PRQG
     * - each of them getting salary greater than 3000
     * - first names are . ... (find proper method to check list against list)
     * List<String> names = Arrays.asList("Alexander", "Bruce", "David", "Valli", "Diana");
     * - emails without checking order (provide emails in different order, just make sure it he
     * <p>
     * - emails without checking order (provided emails in different order, just make sure it has same emails)
     * List<String> emails = asList("DAUSTIN", "'AHUNOLD", "BERNST", "VPATABAL", "DLORENTZ")
     * <p>
     * - List<String> names = Arrays.asList("Alexander", "Bruce", "David", "Valli", "Diana");
     */

    @DisplayName("Get employees IT PROG with hamcrest")
    @Test
    void test1() {

        //expected first_names
        List<String> names = Arrays.asList("Alexander", "Bruce", "David", "Valli", "Diana");

        given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees")
                //*  Then status code is 200
                //*  And Content type is application/json
                .then().contentType(ContentType.JSON.toString())
                .and().statusCode(HttpStatus.SC_OK)
                .body("items.manager_id", everyItem(notNullValue()))
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                .body("items.salary", everyItem(greaterThan(3000)))
                .body("items.first_name", equalTo(names))
                .body("items.email", containsInAnyOrder("DLORENTZ", "AHUNOLD", "BERNST", "DAUSTIN", "VPATABAL"));


        /*

        // - each employees has manager
        for (Map<String, Object> each : items) {
            // - each employees has manager
            assertThat(each.get("manager_id"), is(not(0)));
            System.out.println(each.get("manager_id"));

            //each employees working as IT_PRQG
            assertThat(each.get("job_id"), is(equalTo("IT_PROG")));

            // - each of them getting salary greater than 3000
            assertThat((int)each.get("salary"),is(greaterThan(3000)));

        }

        List<String> actual_names = jsonPath.getList("items.first_name");
        assertThat(names, is(equalTo(actual_names)));


        List<String> emails = Arrays.asList("AHUNOLD", "BERNST", "DAUSTIN", "VPATABAL", "DLORENTZ");
        List<Object> list = jsonPath.getList("items.email");

        System.out.println(list);
        //assertThat(emails, is(equalTo(jsonPath.getList("items.email"))));
        assertThat(emails, is(containsInAnyOrder(list)));

         */


    }

    /**
     * Given
     * accept type is application/json
     * When
     * user sends get request to /regions
     * Then
     * response status code must be 200
     * verify Date has values
     * first region name is Europe
     * first region id is 1
     * four regions we have
     * region names are not null
     * Regions name should be same order as "Europe","Americas","Asia","Middle East and Africa"
     * region ids needs to be 1,2,3,4
     * print all the regions names
     * ...
     * ..
     * .
     */

    @DisplayName("GET region with hamcrest")
    @Test
    void test2() {
        /**
        1. response status code must be 200
        2. verify Date has values
        3. first region name is Europe
        4. first region id is 1
        5. four regions we have
        6. region names are not null
        7. Regions name should be same order as
          "Europe","Americas","Asia","Middle
          East and Africa"
        8. region ids needs to be 1,2,3,4
        9. print all the regions names
         */
        List<String> region_names = Arrays.asList(
                "Europe", "Americas", "Asia", "Middle East and Africa");

        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .when().get("/regions")
                .then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.SC_OK)
                .header("Date",notNullValue())
                .body("items[0].region_name", equalTo("Europe"))
                .body("items[0].region_id", equalTo(1))
                .body("items", hasSize(4))
                .body("items.region_name", notNullValue())
                .body("items.region_name", containsInRelativeOrder(region_names))
                .body("items.region_id", containsInRelativeOrder(1, 2, 3, 4))
                .extract().jsonPath();

        List<String> regions = jsonPath.getList("items.region_name");
        regions.forEach(System.out::println);


    }
}












