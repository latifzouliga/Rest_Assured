package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanAuthTestBase {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://174.129.55.241:7000";

    }
}
