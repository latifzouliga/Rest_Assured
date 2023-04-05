package com.cydeo.day06_Deserialization;

import com.cydeo.pojo.SearchSpartan;
import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.Spartan_TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class P02_SpartanDeserializationToPOJO extends Spartan_TestBase {

    @DisplayName("GET Single spartan for deserialization to POJO (Spartan class)")
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .get("/api/spartans/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        Spartan spartan = response.as(Spartan.class);
        System.out.println("spartan.getName() = " + spartan.getName());
        System.out.println("spartan.getId() = " + spartan.getId());
        System.out.println("spartan.getGender() = " + spartan.getGender());
        System.out.println("spartan.getPhone() = " + spartan.getPhone());

        System.out.println("================= JsonPath ================");
        // jsonPath
        JsonPath jsonPath = response.jsonPath();
        Spartan spartanJP = jsonPath.getObject("", Spartan.class);

        System.out.println("spartan.getName() = " + spartanJP.getName());
        System.out.println("spartan.getId() = " + spartanJP.getId());
        System.out.println("spartan.getGender() = " + spartanJP.getGender());
        System.out.println("spartan.getPhone() = " + spartanJP.getPhone());


    }

    @DisplayName("GET Spartans from search endpoint for deserialization to Search class")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/api/spartans/search")
                //.prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        //I want to get 10th spartan from content array and save into the Spartan object
        //response.as("content[9]",Spartan.class)
        //we cannot do with as() method since it does not support path and class type at the same time

        JsonPath jsonPath = response.jsonPath();
        Spartan spartanJP = jsonPath.getObject("content[9]", Spartan.class);

        System.out.println("spartanJP = " + spartanJP);

    }

    @DisplayName("GET Spartans from search endpoint for deserialization to Search class")
    @Test
    public void test3() {
        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/api/spartans/search")
                //.prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        SearchSpartan search = jsonPath.getObject("", SearchSpartan.class);

        Spartan spartan = search.getContent().get(0);  // get one spartan
        System.out.println(spartan);

        System.out.println("search.getTotalElement() = " + search.getTotalElement());
        System.out.println("spartan.name = " + spartan.name);

        //System.out.println(search); // get all spartans and totalElement

        List<Spartan> allSpartans = jsonPath.getList("content", Spartan.class);
        //allSpartans.forEach(System.out::println);

        System.out.println("jsonPath.getInt(\"totalElement\") = " + jsonPath.getInt("totalElement"));



    }
}
