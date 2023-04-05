package com.cydeo.day07_Post_Patch_Delete;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.Spartan_TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static io.restassured.RestAssured.given;

public class P02 extends Spartan_TestBase {



    @DisplayName("PUT DELETE /api/spartans")
    @Test
    void test1() {

        Spartan spartan = new Spartan();
        spartan.setName("Hello Latif");
        spartan.setGender("Female");
        spartan.setPhone(9999999999L);

        Map<String, Object> spartanMap = new HashMap<>();
        spartanMap.put("name","Jamal");
        spartanMap.put("gender","Male");
        spartanMap.put("phone",1112223333L);

        Response response = given().contentType(ContentType.JSON)
                .when()
                .body(spartanMap)
                .and()
                .post("/api/spartans")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

//        Spartan spar = jsonPath.getObject("data", Spartan.class);
//        int id = spar.getId();
//        System.out.println(id);
        Map<Object, Object> data = jsonPath.getMap("data");
        int id = (int)data.get("id");
        System.out.println(id);
        System.out.println(data);

        // clean up
        given().contentType(ContentType.JSON)
                .when()
                .pathParam("id",id)
                .when()
                .delete("api/spartans/{id}")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);


    }
    @DisplayName("DELETE /api/spartan/{id}")
    @Test
    void test2() {
        given().contentType(ContentType.JSON)
                .when()
                .pathParam("id",116)
                .and()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(204);
    }

    @DisplayName("PUT /api/spartans/{id}")
    @Test
    void test3() {

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .when().pathParam("id", 117)
                .and()
                .get("/api/spartans/{id}")
                .then()
                .extract().jsonPath();

        Spartan spartan = jsonPath.getObject("", Spartan.class);
        System.out.println("spartan = " + spartan);

        Map<String,Object> spartanMap = new HashMap<>();
        spartanMap.put("name",spartan.getName());
        spartanMap.put("gender","Male");
        spartanMap.put("phone",spartan.getPhone());

        given().contentType(ContentType.JSON)
                .when()
                .pathParam("id",117)
                .and()
                .body(spartanMap)
                .and()
                .put("/api/spartans/{id}")
                .then()
                .statusCode(204);

    }
    @DisplayName("PATCH /api/spartans/{id}")
    @Test
    void test4() {
        Map<String, Object> map = new TreeMap<>();
        map.put("name" ,"Patch Latif");

        given().contentType(ContentType.JSON)
                .when()
                .body(map)
                .and()
                .pathParam("id",117)
                .and()
                .patch("/api/spartans/{id}")
                .then()
                .statusCode(204);


    }
}














