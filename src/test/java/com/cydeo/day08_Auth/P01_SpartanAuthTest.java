package com.cydeo.day08_Auth;

import com.cydeo.utilities.SpartanAuth_TestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class P01_SpartanAuthTest extends SpartanAuth_TestBase {

    @DisplayName("GET /api/partans as GUEST user --> Except --401")
    @Test
    public void test1() {
        given().when()
                .accept(ContentType.JSON)
                .get("/api/spartans")
                .prettyPeek()
                .then()
                .statusCode(401)
                .body("error", is("Unauthorized"));
    }

    @DisplayName("GET /api/partans as USER user --> Except --401")
    @Test
    public void test2() {
        given().when()
                .accept(ContentType.JSON)
                .auth()
                .basic("user", "user")
                .get("/api/spartans")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @DisplayName("DELETE /api/spartans as EDITOR user --> Except --403")
    @Test
    public void test3() {
        given().accept(ContentType.JSON)
                .auth().basic("editor", "editor")
                .pathParam("id", 101)
                .and()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(403)
                .and()
                .body("error", is("Forbidden"));
    }

    @DisplayName("DELETE /api/spartans as ADMIN user --> Except --403")
    @Test
    public void test4() {
        given().accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .pathParam("id", 101)
                .and()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(204);
    }
}

//TODO: Based Control Test Homework
/**
 *  HOMEWORKS
 *
 *  	Role Based Control Test --> RBAC
 *
 * 			ADMIN  -->  GET  POST PUT PATCH  DELETE   --> Spartan Flow
 * 			EDITOR -->  GET  POST PUT PATCH   403
 * 			USER   -->  GET  403  403  403    403
 * 			GUEST  -->  401  401  401  401    401
 *
 *
 *   -- Create RBAC Test for all different roles from Spartan Application including with Negative Test cases
 public static void  GETSpartans(String role,String password,int statusCode,int id){
 *
 *                 given().pathParam("id",id)
 *                 .auth().basic(role,password).
 *                 when().get("/api/spartans/{id}").then().statusCode(statusCode);
 *
 *               }
 */


