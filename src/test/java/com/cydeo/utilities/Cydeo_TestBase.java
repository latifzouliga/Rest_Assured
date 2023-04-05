package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class Cydeo_TestBase {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://api.training.cydeo.com";

    }
}
