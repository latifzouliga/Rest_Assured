package com.cydeo.utilities;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BookItUtils {



    public static String getToken(String email, String password){


        JsonPath jsonPath = given().accept(ContentType.JSON)
                .when()
                .queryParam("email",email)
                .queryParam("password",password)
                .and()
                .get("/sign")
                .then()
                .statusCode(200)
                .extract().jsonPath();

        String accessToken1 = jsonPath.getString("accessToken");
        return "Bearer " + accessToken1;

    }

    public static String getToken(){

        String email = "lfinnisz@yolasite.com";
        String password = "lissiefinnis";

        return getToken(email, password);

    }
}













