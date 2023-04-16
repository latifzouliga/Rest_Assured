package com.cydeo.day07_Post_Patch_Delete;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


public class P04_SpartanFlow extends SpartanTestBase {

  static int id;

    @DisplayName("Post /api/spartans")
    @Test
    @Order(1)
    void test1() throws InterruptedException {
        //  - POST     /api/spartans
        //  Create a spartan Map,Spartan class
        //  "name" = "API Flow POST"
        //  "gender"="Male"
        //  "phone"=1231231231l
//
        //          - verify status code 201
        //          - message is "A Spartan is Born!"
        //          - take spartanID from response and save as a global variable

        Map<String, Object> postSpartan = new LinkedHashMap<>();
        postSpartan.put("name", "API Flow POST");
        postSpartan.put("gender", "Male");
        postSpartan.put("phone", 1231231231l);
        Response response = given().contentType(ContentType.JSON)
                .when()
                .body(postSpartan)
                .and()
                .post("/api/spartans");

        id = response.path("data.id");
        System.out.println(id + "from Post");

    }

    @DisplayName("GET /api/spartans")
    @Test()
    @Order(2)
    public void test2() {
        //- GET  Spartan with spartanID     /api/spartans/{id}
        System.out.println(id + "from GET");
        // - verify status code 200
        // - verify name is API Flow POST
        given().accept(ContentType.JSON)
                .when()
                .pathParam("id", id)
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .and()
                .body("name", is("API Flow POST"));

    }

    @DisplayName("PUT")
    @Test
    @Order(3)
    public void test3() {
        // Create a spartan Map
        // name = "API PUT Flow"
        // gender="Female"
        // phone=3213213213l
        //
        //verify status code 204
        Spartan spartan = new Spartan();
        spartan.setName("API PUT Flow");
        spartan.setGender("Female");
        spartan.setPhone(3213213213L);

        given().contentType(ContentType.JSON)
                .when()
                .body(spartan)
                .and()
                .pathParam("id", id)
                .and()
                .put("/api/spartans/{id}")
                .then()
                .statusCode(204);
    }

    @DisplayName("GET /api/spartans")
    @Test()
    @Order(4)
    public void test4() throws InterruptedException {

        given().accept(ContentType.JSON)
                .when()
                .pathParam("id", id)
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .and()
                .body("name", is("API PUT Flow"));

    }

    @DisplayName("DELETE /api/spatans")
    @Test
    @Order(5)
    public void test5() {
        given().contentType(ContentType.JSON)
                .when()
                .pathParam("id", id)
                .and()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(204);
    }

    @DisplayName("GET /api/spartans")
    @Test()
    @Order(6)
    public void test6() throws InterruptedException {

        given().accept(ContentType.JSON)
                .when()
                .pathParam("id", id)
                .get("/api/spartans/{id}")
                .then()
                .statusCode(404);

    }

    /*

    Create a Spartan Flow to run below testCases dynamically

   - POST     /api/spartans
            Create a spartan Map,Spartan class
                name = "API Flow POST"
                gender="Male"
                phone=1231231231l

            - verify status code 201
            - message is "A Spartan is Born!"
            - take spartanID from response and save as a global variable


    - GET  Spartan with spartanID     /api/spartans/{id}


             - verify status code 200
             - verify name is API Flow POST

    - PUT  Spartan with spartanID    /api/spartans/{id}

             Create a spartan Map
                name = "API PUT Flow"
                gender="Female"
                phone=3213213213l

             - verify status code 204

    - GET  Spartan with spartanID     /api/spartans/{id}


             - verify status code 200
             - verify name is API PUT Flow

    - DELETE  Spartan with spartanID   /api/spartans/{id}


             - verify status code 204

     - GET  Spartan with spartanID   /api/spartans/{id}


             - verify status code 404


    Challenges
       Create @Test annotated method for each Request
       Put them in order to execute as expected
                    - Use your googling skills
                    - How to run Junit5 Tests in order  ?



     */

}
