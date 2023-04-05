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

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class P02_SpartanPost extends Spartan_TestBase {

    /**
     * Given accept type is JSON
     * And Content type is JSON
     * And request json body is:
     * {
     * "gender": "Male",
     * "name": "John Doe",
     * "phone":8877445596
     * }
     * When user sends POST request to "/api/spartans"
     * Then status code 201
     * And content type should be application/json
     * And json payload/response/body should contain:
     * verify the success value is 'A Spartan is Born!'
     * "name": "John Doe",
     * "gender": "Male",
     * "phone": 1231231231
     */
    @DisplayName("POST spartans with body String")
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "     \"gender\": \"Male\",\n" +
                        "     \"name\": \"John Doe\",\n" +
                        "     \"phone\":8877445596\n" +
                        "     }")
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .and()
                .contentType(ContentType.JSON.toString())
                // Hamcrest verification with chained methods
                .body("success", is("A Spartan is Born!"))
                .body("data.name", is("John Doe"))
                .body("data.gender", is("Male"))
                .body("data.phone", is(8877445596L))
                .extract().response();

        // the above verification is enough
        // Hamcrest verification with assetThat() methods
        JsonPath jsonPath = response.jsonPath();
        Spartan newSpartan = jsonPath.getObject("data", Spartan.class);

        assertThat("John Doe", is(equalTo(newSpartan.getName())));
        assertThat("Male", is(equalTo(newSpartan.getGender())));
        assertThat(8877445596L, is(equalTo(newSpartan.getPhone())));

        int id = newSpartan.getId();
        System.out.println("ID of newly created Spartan: " + id);

        // delete newly created Spartan
        given().contentType(ContentType.JSON)
                .when()
                .pathParam("id", id)
                .and()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(204);

        System.out.println("Sorry the newly created Spartan is deleted!");


    }

    @DisplayName("POST spartans with body Map")
    @Test
    public void test2() {

        Map<String, Object> spartanMap = new HashMap<>();
        spartanMap.put("gender", "Male");
        spartanMap.put("name", "John Moe");
        spartanMap.put("phone", 9999944444L);

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(spartanMap)
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .and()
                .contentType(ContentType.JSON.toString())
                // Hamcrest verification with chained methods
                .body("success", is("A Spartan is Born!"))
                .body("data.name", is(spartanMap.get("name")))
                .body("data.gender", is(spartanMap.get("gender")))
                .body("data.phone", is(spartanMap.get("phone")))
                .extract().response();

        // the above verification is enough
        // Hamcrest verification with assetThat() methods
        JsonPath jsonPath = response.jsonPath();


        assertThat(spartanMap.get("name"), is(equalTo(jsonPath.getString("data.name"))));
        assertThat(spartanMap.get("gender"), is(equalTo(jsonPath.getString("data.gender"))));
        assertThat(spartanMap.get("phone"), is(equalTo(jsonPath.getLong("data.phone"))));

        int id = jsonPath.getInt("data.id");
        System.out.println("ID of newly created Spartan: " + id);

        // delete newly created Spartan
        given().contentType(ContentType.JSON)
                .when()
                .pathParam("id", id)
                .and()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(204);

        System.out.println("Sorry the newly created Spartan is deleted!");


    }

    @DisplayName("POST spartans with body POJO")
    @Test
    public void test3() {


        Spartan spartanPOST = new Spartan();
        spartanPOST.setName("Jamal Jamal");
        spartanPOST.setGender("Male");
        spartanPOST.setPhone(1122334455L);
        System.out.println(spartanPOST);

        Response response = given().log().body()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(spartanPOST)
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .and()
                .contentType(ContentType.JSON.toString())
                // Hamcrest verification with chained methods
                .body("success", is("A Spartan is Born!"))
                .body("data.name", is(spartanPOST.getName()))
                .body("data.gender", is(spartanPOST.getGender()))
                //.body("data.phone", is(spartanPOST.getPhone()))
                .extract().response();


        JsonPath jsonPath = response.jsonPath();

        int id = jsonPath.getInt("data.id");
        System.out.println("ID of newly created Spartan: " + id);

        // delete newly created Spartan
        given().contentType(ContentType.JSON)
                .when()
                .pathParam("id", id)
                .and()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(204);

        System.out.println("Sorry the newly created Spartan is deleted!");


    }

    @DisplayName("POST spartans with body Map")
    @Test
    public void test4() {

        //empty spartan object and we use setters to set some value
        //values can be from faker library or somewhere else which is changing dynamically
        //===================================== POST REQUEST ================================

        Map<String, Object> spartanMap = new HashMap<>();
        spartanMap.put("gender", "Male");
        spartanMap.put("name", "John Moe");
        spartanMap.put("phone", 9999944444L);

        JsonPath jsonPath = given().log().body()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(spartanMap)
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .and()
                .contentType(ContentType.JSON.toString())
                .extract().jsonPath();

         assertThat("A Spartan is Born!",is(jsonPath.getString("success")));
        assertThat(spartanMap.get("name"), is(equalTo(jsonPath.getString("data.name"))));
        assertThat(spartanMap.get("gender"), is(equalTo(jsonPath.getString("data.gender"))));
        assertThat(spartanMap.get("phone"), is(equalTo(jsonPath.getLong("data.phone"))));

        int id = jsonPath.getInt("data.id");
        System.out.println("ID of newly created Spartan: " + id);

        //===================================== GET REQUEST ================================

        //SEND GET REQUEST TO THE SPARTAN THAT IS CREATED THEN DESERIALIZE TO SPARTAN CLASS and compare
        Response response = given().accept(ContentType.JSON)
                .when().pathParam("id", id)
                .and().get("/api/spartans/{id}")
                .then().statusCode(HttpStatus.SC_OK)
                .and().extract().response();

        Spartan newlyCreatedSP = response.as(Spartan.class);

        assertThat(newlyCreatedSP.getName(), is(spartanMap.get("name")));
        assertThat(newlyCreatedSP.getGender(),is(spartanMap.get("gender")));
        assertThat(newlyCreatedSP.getPhone(),is(spartanMap.get("phone")));

        //===================================== DELETE REQUEST ================================

        // delete newly created Spartan
        given().contentType(ContentType.JSON)
                .when()
                .pathParam("id", id)
                .and()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(204);

        System.out.println("Sorry the newly created Spartan is deleted!");

        //SEND GET REQUEST TO THE SPARTAN THAT IS CREATED THEN DESERIALIZE TO SPARTAN CLASS and com


    }



}

//SEND GET REQUEST TO THE SPARTAN THAT IS CREATED THEN DESERIALIZE TO SPARTAN CLASS and com


