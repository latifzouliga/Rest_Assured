package com.cydeo.homework;

import com.cydeo.utilities.Hr_TestBase;
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

public class Tasks_4_5_6 extends Hr_TestBase {

    //ORDS API DOCUMENT
    //—> https://documenter.getpostman.com/view/25449093/2s8ZDYYNBz

    @DisplayName("GET- /countries")
    @Test
    void test1() {
        //TASK 1 :
        //  - Given accept type is Json
        //  - Path param value- US
        //  - When users sends request to /countries
        //  - Then status code is 200
        //  - And Content - Type is Json
        //  - And country_id is US
        //  - And Country_name is United States of America
        //    And Region_id is 2
        Response response = given().accept(ContentType.JSON)
                .pathParam("value", "US")
                .when()
                .get("/countries/{value}");

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        assertEquals("United States of America", response.path("country_name"));
        assertEquals("US", response.path("country_id"));
        assertEquals(2, (Integer) response.path("region_id"));

    }


    @DisplayName("GET- /employees")
    @Test
    public void test2() {
        //TASK 2 :
        //  - Given accept type is Json
        //  - Query param value - q={"department_id":80}
        //  - When users sends request to /employees
        //  - Then status code is 200
        //  - And Content - Type is Json
        //  - And all job_ids start with 'SA'
        //  - And all department_ids are 80
        //  - Count is 25
        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"department_id\":80}")
                .when()
                .get("/employees");

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.getContentType());

        JsonPath jsonPath = response.jsonPath();

        //  - And all job_ids start with 'SA'
        List<String> jobIds = jsonPath.getList("items.job_id");
       //jobIds.forEach(v -> assertEquals(v.substring(0,2),"SA"));
       jobIds.forEach(v -> assertTrue(v.startsWith("SA")));

        //  for (String each : jobIds) {
        //      assertTrue(each.startsWith("SA"));
        //  }


        //  - And all department_ids are 80
        List<Integer> departmentId = jsonPath.getList("items.department_id");
        departmentId.forEach(id -> assertEquals(id, 80));

        //  - Count is 25
        assertEquals(25, jsonPath.getInt("count"));


    }

    //TASK 3 :
    //  - Given accept type is Json
    //  - Query param value q={“region_id":3}
    //  - When users sends request to /countries
    //  - Then status code is 200
    //  - And all regions_id is 3
    //  - And count is 6
    //  - And hasMore is false
    //  - And Country_name are;
    //    Australia,China,India,Japan,Malaysia,Singapore
}
