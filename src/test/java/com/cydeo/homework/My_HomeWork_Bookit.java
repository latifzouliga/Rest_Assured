package com.cydeo.homework;

import com.cydeo.utilities.BookItUtils;
import com.cydeo.utilities.BookItTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class My_HomeWork_Bookit extends BookItTestBase {

    String accessTokenTeacher = BookItUtils.getToken("blyst6@si.edu", "barbabaslyst");

    //{{BookitUrl}}/api/batch?batch-number=28


    @DisplayName("GET /api/campuses")
    @Test
    public void test12() {
        System.out.println("accessToken = " + accessTokenTeacher);

        given().accept(ContentType.JSON)
                .header("Authorization", accessTokenTeacher)
                .when().get("/api/campuses")
                .then().statusCode(200);

        given().contentType(ContentType.JSON)
                .when()
                .header("Authorization", accessTokenTeacher)
                .and()
                .queryParam("batch-number", 28)
                .and()
                .post("/api/batch")
                .prettyPeek();


    }

    @DisplayName("ADD /api/campuses add batch")
    @Test
    public void test13() {
        System.out.println("accessToken = " + accessTokenTeacher);


        Response response = given().contentType(ContentType.JSON)
                .log().body()
                .when()
                .header("Authorization", accessTokenTeacher)
                .and()
                .queryParam("campus-location", "VA")
                .queryParam("batch-number", 28)
                .queryParam("team-name", "Team Nashia123")
                .and()
                .post("/api/teams/team")
                .prettyPeek()
                .then()
                .statusCode(201)
                .extract().response();
    }
    @DisplayName("GET /api/teams (GET a team id)")
    @Test
    public void test14() {

        JsonPath jsonPath = given().contentType(ContentType.JSON)
                .when()
                .header("Authorization", accessTokenTeacher)
                .and()
                .get("/api/teams")
                .then()
                .extract().jsonPath();

        List<Object> names = jsonPath.getList("name");
        List<Object> ids = jsonPath.getList("id");
        System.out.println(names);

        int index = 0;
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equals("Nashia")) {
                index = i;
            }
        }
        System.out.println(index);
        System.out.println(ids.get(index));


    }

    @DisplayName("Add Students")
    @Test
    void test15() {


        List<String> names = Arrays.asList("Dilvin", "Latif","Kano","Elena", "Cristina","Marhabo","Julian","Fatma","Kubra","Eden");

        for (String eachName : names) {

            given().contentType(ContentType.JSON)
                    .log().body()
                    .when()
                    .header("Authorization", accessTokenTeacher)
                    .and()
                    .queryParam("first-name", eachName)
                    .queryParam("last-name", eachName)
                    .queryParam("email", eachName + "@yahoo.com")
                    .queryParam("password", "nashia123")
                    .queryParam("role", "student-team-member")
                    .queryParam("campus-location", "VA")
                    .queryParam("batch-number", 28)
                    .queryParam("team-name", "Nashia")
                    .and()
                    .post("/api/students/student")
                    .prettyPeek()
                    .then()
                    .statusCode(201);
        }

    }


}
