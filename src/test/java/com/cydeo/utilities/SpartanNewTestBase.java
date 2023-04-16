package com.cydeo.utilities;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanNewTestBase {

    public static RequestSpecification userReqSpec;
    public static RequestSpecification adminReqSpec;
    public static RequestSpecification editorReqSpec;
    public static ResponseSpecification resSpec;


    /**
     *
     */
    @BeforeAll
    public static void setUp() {
        baseURI = "http://174.129.55.241";
        port = 7000;
        basePath = "/api";// for the path that is repeating

        // baseURI + port + basePath
        // http://174.129.55.241:7000/api

        userReqSpec = given().accept(ContentType.JSON).log().uri()
                .auth().basic("user", "user");

        adminReqSpec = given().accept(ContentType.JSON).log().uri()
                .auth().basic("admin", "admin");

        editorReqSpec = given().accept(ContentType.JSON).log().uri()
                .auth().basic("editor", "editor");

        resSpec = expect().statusCode(200).
                and()
                .contentType(ContentType.JSON);

    }

    /**
     *
     * @param user
     * @param password
     * @return
     */
    public static RequestSpecification reqSpec(String user, String password){

       return given().accept(ContentType.JSON).log().all()
                .and()
                .auth().basic(user, password);
    }

    /**
     *
     * @param statusCode
     * @return
     */

    public static ResponseSpecification resSpec(int statusCode){

       return expect().then()
                .contentType(ContentType.JSON)
                .and()
                .statusCode(statusCode);
    }
}














