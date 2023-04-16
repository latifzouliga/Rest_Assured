package com.cydeo.day12;



import com.cydeo.utilities.BookItTestBase;
import com.cydeo.utilities.BookItUtils;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static com.cydeo.utilities.BookItUtils.*;
import static io.restassured.RestAssured.given;

public class P03_BookitSpecTest extends BookItTestBase {

    @Test
    void test1() {
        given().accept(ContentType.JSON)
                .auth().oauth2(BookItUtils.getTokenByRole("teacher"))
                .when()
                .get("/api/users/me")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);

    }

    @Test
    void test2() {
        given().spec(reqSpec("teacher"))
                .when()
                .get("/api/users/me")
                .then()
                .spec(resSpec(200));

    }


}



















