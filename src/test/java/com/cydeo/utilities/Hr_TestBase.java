package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class Hr_TestBase {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://174.129.55.241:1000/ords/hr";

    }
}
