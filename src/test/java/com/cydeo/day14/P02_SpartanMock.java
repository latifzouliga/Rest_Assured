package com.cydeo.day14;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P02_SpartanMock {

    @BeforeAll
    public static void init(){
        baseURI = "https://e7f52ea8-1877-4816-b6f8-ecdcd1a54805.mock.pstmn.io";
    }
    @DisplayName("GET /api/hello")
    @Test
    public void test1(){
        Response response = given().accept(ContentType.TEXT)
                .log().all()
                .when().get("/api/hello")
                .then().statusCode(200)
                .extract().response();

        assertEquals("Hello from Sparta", response.asString());
    }

    @DisplayName("GET /api/hello")
    @Test
    public void test2(){
        Response response = given().accept(ContentType.TEXT)
                .log().all()
                .when().get("/api/spartans")
                .then().statusCode(200)
                .extract().response();

        response.prettyPeek();
       // assertEquals("Hello from Sparta", response.asString());
    }
}
