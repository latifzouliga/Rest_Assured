package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanTestBase {

    public Logger log = LogManager.getLogger(this.getClass());
    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://174.129.55.241:8000";

    }
}
