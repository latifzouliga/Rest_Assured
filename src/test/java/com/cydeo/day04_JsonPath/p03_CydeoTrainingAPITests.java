package com.cydeo.day04_JsonPath;

import com.cydeo.utilities.Cydeo_TestBase;
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

public class p03_CydeoTrainingAPITests extends Cydeo_TestBase {

//    Given accept type is application
//    And path param is 2
//    When user send request /student/{id}
//    Then the status code should be 200
//    And content type is application/json:charset=UTF
//    And Date header is exist
//    And Server header is envoy
//    And verify following

//                firstName Mark
//                batch 13
//                major math
//                emailAddress mark@email.com
//                companyName Cydeo
//                street 777 5th Ave
//                zipCode 33222

    @DisplayName("GET- /student/2")
    @Test
    public void test() {
        Response response = given()
                .accept(ContentType.JSON)
                .and().pathParam("id", 2)
                .when().get("/student/{id}");

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals("application/json;charset=UTF-8", response.contentType());
        assertTrue(response.headers().hasHeaderWithName("date"));
        assertEquals("envoy", response.header("server"));

        //  response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        // firstName Mark
        String firstName = jsonPath.getString("students[0].firstName");
        System.out.println(firstName);
        assertEquals("Mark",firstName);


        // batch 13
        String batch = jsonPath.getString("students[0].batch");
        System.out.println(batch);
        assertEquals("13", batch);

        // major math
        String major = jsonPath.getString("students[0].major");
        System.out.println(major);
        assertEquals("math", major);

        // emailAddress mark@email.com
        String email = jsonPath.getString("students[0].contact.emailAddress");
        System.out.println(email);
        assertEquals("mark@email.com",email);


        // companyName Cydeo
        String companyName = jsonPath.getString("students[0].company.companyName");
        System.out.println(companyName);

        // street 777 5th Ave
        String street = jsonPath.getString("students[0].company.address.street");
        System.out.println(street);
        assertEquals("777 5th Ave",street);


        // zipCode 33222
        String zipCode = jsonPath.getString("students[0].company.address.zipCode");
        System.out.println(zipCode);

        response.prettyPrint();


    }

    //TASK
    //    Given accept type is application/json
    //    And path param is 22
    //    When user send request /student/batch/{batch}
    //    Then status code should be 200
    //    And content type is application/json;charset=UTF-8
    //    And Date header is exist
    //    And Server header is envoy
    //    And verify all the batch number is 22

    @DisplayName("GET- /student/batch/{batch}")
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("batch", 22)
                .when()
                .get("/student/batch/{batch}");

        JsonPath jsonPath = response.jsonPath();
        String string = jsonPath.getString("students.findAll {it.batch == 22}");
        System.out.println(string);

        //    Then status code should be 200
        assertEquals(HttpStatus.SC_OK,response.statusCode());

        //    And content type is application/json;charset=UTF-8
        assertEquals("application/json;charset=UTF-8",response.contentType());

        //    And Date header is exist
        assertTrue(response.headers().hasHeaderWithName("date"));

        //    And Server header is envoy
        assertEquals("envoy",response.header("server"));

        //    And verify all the batch number is 22
        List<Integer> list = jsonPath.getList("students.batch");
        System.out.println(list);

        list.forEach(v ->  assertEquals(v , 22));


    }




}








