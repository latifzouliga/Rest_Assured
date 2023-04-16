package com.cydeo.day04_JsonPath;

import com.cydeo.utilities.HrTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class P02_HrWithJasonPath extends HrTestBase {

    @DisplayName("GET all /countries")
    @Test
    void test() {
        Response response = RestAssured.get("/countries");

        //response.prettyPrint();
        assertEquals(HttpStatus.SC_OK, response.statusCode());

        // create a json path object
        JsonPath jsonPath = response.jsonPath();


        // get the 3rd country name
        String countryName = jsonPath.getString("items[2].country_name");
        System.out.println(countryName);

        // get the 3rd and 4th countries at the sametime
        String countryNames = jsonPath.getString("items[2,3].country_name");
        System.out.println(countryNames);

        // get all country names
        List<String> allCountryNames = jsonPath.getList("items.country_name");
        System.out.println(allCountryNames);
        allCountryNames.forEach(System.out::println);

        // get me all country name where region_id is 2
        List<Object> list = jsonPath.getList("items.findAll {it.region_id == 1}.country_name");
        System.out.println(list);


    }

    // And query param limit is 200
    // When User send request /employees
    // Then user should see
    @DisplayName("GET all /employees?limit=200 with JsonPath")
    @Test
    void test1() {
        Response response = given().accept(ContentType.JSON)
                .when()
                .queryParam("limit", 200)
                .when()
                .get("/employees");


        assertEquals(HttpStatus.SC_OK, response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        // get all emails from response
        List<Object> allEmails = jsonPath.getList("items.email");
        System.out.println("Total number of emails: " + allEmails);
        System.out.println("All emails: " + allEmails.size());

        // get all emails who is working as IT_PROG
        List<Object> list1 = jsonPath.getList("items.findAll {it.job_id == 'IT_PROG'}.email");
        System.out.println("All IT_PROG Names: " + list1);

        // get all employees first names whose salary i s more than 10000
        List<Object> list2 = jsonPath.getList("items.findAll {it.salary < 10000}.first_name");
        System.out.println("Employees who are making more than 10k: "+list2);

        // get all information from response who has max salary
        String maxSalary = jsonPath.getString("items.max {it.salary}");
        System.out.println("max salary: "+maxSalary);

        // get first name from response who has max salary
        String string = jsonPath.getString("items.max {it.salary}.first_name");
        System.out.println("Who is getting max salary: "+string);

        // get first name from response who has min salary
        String string1 = jsonPath.getString("items.min {it.salary}.first_name");
        System.out.println("How is getting min salary: " + string1);

        response.prettyPrint();

    }


}










