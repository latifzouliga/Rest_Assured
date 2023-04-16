package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanTestBase {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://174.129.55.241:8000";

    }
}
