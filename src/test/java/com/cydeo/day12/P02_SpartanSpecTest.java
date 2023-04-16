package com.cydeo.day12;

import com.cydeo.utilities.SpartanNewTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class P02_SpartanSpecTest extends SpartanNewTestBase {

    @DisplayName("GET all spartans")
    @Test
    void test1() {
        given().accept(ContentType.JSON)
                .auth().basic("admin", "admin").
                when()
                .get("/spartans").
                then()
                .statusCode(200).
                and()
                .contentType(ContentType.JSON);
    }


    @DisplayName("GET all spartans with reqSpec and resSpec")
    @Test
    void test2() {

        given().spec(adminReqSpec).log().all()
                .when()
                .get("/spartans").prettyPeek()
                .then()
                .spec(resSpec);


    }

    @DisplayName("GET one spartan with reqSpec and resSpec")
    @Test
    void test3() {

        given().spec(adminReqSpec).log().all()
                .and()
                .pathParam("id", 10)
                .when()
                .get("/spartans/{id}").prettyPeek()
                .then()
                .spec(resSpec)
                .body("id", is(10));


    }

    @DisplayName("GET one spartan as a USER")
    @Test
    void test4() {

        given().spec(userReqSpec)
                .pathParam("id", 11)
                .when()
                .get("/spartans/{id}").prettyPeek()
                .then()
                .spec(resSpec)
                .body("id", is(11));


    }

    @DisplayName("GET one spartan as a USER")
    @Test
    void test5() {

       given().spec(reqSpec("editor","editor"))
               .and()
                .pathParam("id", 11)
                .when()
                .get("/spartans/{id}").prettyPeek()
                .then()
                .spec(resSpec(200))
                .body("id", is(11));


    }

    /**
     *    Create GET_RBAC.csv
     *    username,password,id,statusCode
     *    admin,admin,3,200
     *    editor,editor,3,200
     *    user,user,3,200
     *
     *  Create a parameterized test to check RBAC for GET method
     *
     *
     */

    @DisplayName("GET spartans, RBAC test with csv file")
    @ParameterizedTest
    @CsvFileSource(resources = "/GET_RBAC.csv", numLinesToSkip = 1)
    public void test6(String username, String password, int id, int statusCode){

        given().spec(reqSpec(username,password))
                .and()
                .pathParam("id",id)
                .when()
                .get("/spartans/{id}").prettyPeek()
                .then()
                .spec(resSpec(statusCode));
    }

    /**
     *    Create DELETE_RBAC.csv
     *    username,password,id,statusCode
     *    editor,editor,3,403
     *    user,user,3,403
     *    admin,admin,3,204
     *
     *  Create a parameterized test to check RBAC for DELETE method
     *
     *
     */

    // This test will work for one time only,
    // If we rerun this test it will fail because a spartan with id 3 is already deleted in the first test
    // If you still wanna rerun this test, you need to change id number in CSV file
    @DisplayName("DELETE spartan, RBAC test with csv file")
    @ParameterizedTest
    @CsvFileSource(resources = "/DELETE_RBAC.csv", numLinesToSkip = 1)
    public void test7(String username, String password, int id, int statusCode){

        given().spec(reqSpec(username,password))
                .and()
                .pathParam("id",id)
                .when()
                .delete("/spartans/{id}").prettyPeek()
                .then()
                .spec(resSpec(statusCode));
    }


}

































