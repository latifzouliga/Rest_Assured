package com.cydeo.day07_Post_Patch_Delete;


import com.cydeo.pojo.Student;
import com.cydeo.pojo.Students;
import com.cydeo.utilities.Cydeo_TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P01_CydeoTrainingDeserializationPOJO extends Cydeo_TestBase {


    @DisplayName("GET /student/2")
    @Test
    public void test1() {
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 2)
                .when().get("/student/{id}");
        //verify status code
        assertEquals(200, response.statusCode());

        JsonPath jsonPath = response.jsonPath();
        Student student = jsonPath.getObject("students[0]", Student.class);

        assertThat("Mark", is(equalTo(student.getFirstName())));
        assertThat(13, equalTo(student.getBatch()));
        assertThat("math", equalTo(student.getMajor()));
        assertThat("mark@email.com", equalTo(student.getContact().getEmailAddress()));
        assertThat("Cydeo", equalTo(student.getCompany().getCompanyName()));
        assertThat("777 5th Ave", equalTo(student.getCompany().getAddress().getStreet()));


//        firstName Mark
        System.out.println("FirstName = " + student.getFirstName());
//        batch 13
        System.out.println("Batch = " + student.getBatch());
//        major math
        System.out.println("Major = " + student.getMajor());
//        emailAddress mark@email.com
        System.out.println("EmailAddress = " + student.getContact().getEmailAddress());
//        companyName Cydeo
        System.out.println("CompanyName = " + student.getCompany().getCompanyName());
//        street 777 5th Ave
        System.out.println("Street= " + student.getCompany().getAddress().getStreet());
//         zipCode 33222
        System.out.println("ZipCode= " + student.getCompany().getAddress().getZipCode());
    }

    @DisplayName("GET /student/all")
    @Test
    public void test2() {
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .when().get("/student/all");
        //verify status code
        assertEquals(200, response.statusCode());

        JsonPath jsonPath = response.jsonPath();
         Students students = jsonPath.getObject("", Students.class);

         Student student = students.getStudents().get(0);

        assertThat("Mark", is(equalTo(student.getFirstName())));
        assertThat(13, equalTo(student.getBatch()));
        assertThat("math", equalTo(student.getMajor()));
        assertThat("mark@email.com", equalTo(student.getContact().getEmailAddress()));
        assertThat("Cydeo", equalTo(student.getCompany().getCompanyName()));
        assertThat("777 5th Ave", equalTo(student.getCompany().getAddress().getStreet()));


        //print all students
        //students.getStudents().forEach(System.out::println);



    }
    @DisplayName("GET /student/2 Test ready POJO")
    @Test
    public void test13() {
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 2)
                .when().get("/student/{id}");
        //verify status code
        assertEquals(200, response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        com.cydeo.pojo.ready.Student student = jsonPath.getObject("students[0]", com.cydeo.pojo.ready.Student.class);

        assertEquals("Mark", student.getFirstName());
        System.out.println("FirstName = " + student.getFirstName());
        System.out.println("LastName = " + student.getLastName());
        System.out.println("Company = " + student.getCompany());
        System.out.println("Address = " + student.getCompany().getAddress());

    }
}




















