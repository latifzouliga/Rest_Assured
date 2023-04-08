package com.cydeo.day08;

import com.cydeo.utilities.BookItUtils;
import com.cydeo.utilities.BookIt_TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class P02_BookItTest extends BookIt_TestBase{


    String accessToken = BookItUtils.getToken("lfinnisz@yolasite.com","lissiefinnis");

    @DisplayName("GET /api/campuses")
    @Test
    public void test1(){
        System.out.println("accessToken = " + accessToken);

        given().accept(ContentType.JSON)
                .header("Authorization",accessToken)
                .when().get("/api/campuses").prettyPeek()
                .then().statusCode(200);


    }

    @DisplayName("GET /api/campuses")
    @Test
    public void test2(){
        System.out.println("accessToken = " + BookItUtils.getToken());

        given().accept(ContentType.JSON)
                .header("Authorization",BookItUtils.getToken())
                .when().get("/api/campuses").prettyPeek()
                .then().statusCode(200);


    }

    @DisplayName("GET /api/users/me")
    @Test
    public void test3(){
        given().accept(ContentType.JSON)
                .and()
                .header("Authorization",BookItUtils.getToken())
                .when()
                .get("/api/users/me")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

}
















