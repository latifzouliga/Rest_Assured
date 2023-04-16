package com.cydeo.utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;

public abstract class BookItTestBase {


   static RequestSpecification reqSpec;
   static ResponseSpecification resSpec;
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "https://api.qa.bookit.cydeo.com";

    }





}