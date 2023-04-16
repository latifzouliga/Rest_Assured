package com.cydeo.day08_Auth;

import com.cydeo.utilities.BookItUtils;
import com.cydeo.utilities.BookItTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class P02_BookItTest extends BookItTestBase {


    String accessToken = BookItUtils.getToken("lfinnisz@yolasite.com", "lissiefinnis");

    @DisplayName("GET /api/campuses")
    @Test
    public void test1() {
        System.out.println("accessToken = " + accessToken);

        given().accept(ContentType.JSON)
                .header("Authorization", accessToken)
                .when().get("/api/campuses").prettyPeek()
                .then().statusCode(200);


    }

    @DisplayName("GET /api/campuses")
    @Test
    public void test2() {
        System.out.println("accessToken = " + accessToken);

        given().accept(ContentType.JSON)
                .header("Authorization", accessToken)
                .when().get("/api/campuses").prettyPeek()
                .then().statusCode(200);


    }

    @DisplayName("GET /api/users/me")
    @Test
    public void test3() {
        given().accept(ContentType.JSON)
                .and()
                .header("Authorization", accessToken)
                .when()
                .get("/api/users/me")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @DisplayName("GET /api/users/me")
    @Test
    public void test4() {
        given().accept(ContentType.JSON)
                .and()
                .auth().oauth2(accessToken)
                .when()
                .get("/api/users/me")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

}
















