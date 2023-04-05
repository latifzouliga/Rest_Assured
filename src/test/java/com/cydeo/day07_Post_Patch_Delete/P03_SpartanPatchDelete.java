package com.cydeo.day07_Post_Patch_Delete;

import com.cydeo.utilities.Spartan_TestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class P03_SpartanPatchDelete extends Spartan_TestBase {

    @DisplayName("PUT Spartan with Map")
    @Test
    void test1() {

        //we can provide json request body with map,pojo,string all is valid here too.

        Map<String, Object> spartanMap = new HashMap<>();
        spartanMap.put("gender", "Male");
        spartanMap.put("name", "John Moe Put");
        spartanMap.put("phone", 9999944444L);

        //PUT will update existing record so we choose one the existing ID, make sure it exist in

        int id = 157;
        given().contentType(ContentType.JSON)
                .pathParam("id",id)
                .body(spartanMap)
                .when().put("/api/spartans/{id}")
                .then()
                .statusCode(204);

    }
    @DisplayName("PATCH Spartan with Map")
    @Test
    void test2() {

        //we can provide json request body with map,pojo,string all is valid here too.

        Map<String, Object> spartanMap = new HashMap<>();
        spartanMap.put("name", "John Moe Patch");

        //PATCH will update existing record so we choose one the existing ID, make sure it exist in

        int id = 157;
        given().contentType(ContentType.JSON)
                .pathParam("id",id)
                .body(spartanMap)
                .when().patch("/api/spartans/{id}")
                .then()
                .statusCode(204);

    }
    @DisplayName("DELETE Spartan with Map")
    @Test
    void test3() {


        //DELETE will update existing record so we choose one the existing ID, make sure it exist in

        int id = 155;
        given().contentType(ContentType.JSON)
                .pathParam("id",id)
                .when().delete("/api/spartans/{id}")
                .then()
                .statusCode(204);

        //after deleted when we send get request to id that we deleted, it needs to give 404
        given().contentType(ContentType.JSON)
                .pathParam("id",id)
                .when().get("/api/spartans/{id}")
                .then()
                .statusCode(404);

    }

}
