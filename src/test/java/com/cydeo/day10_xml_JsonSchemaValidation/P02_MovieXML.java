package com.cydeo.day10_xml;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class P02_MovieXML {

    // https://www.omdbapi.com/
    //


    @Test
    void test1() {
        Response response = given().accept(ContentType.XML)
                .when()
                .queryParam("apikey", "88f66d1b")
                .and()
                .queryParam("r", "xml")
                .queryParam("t", "Avatar")
                .and()
                .get("http://www.omdbapi.com")
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract().response();

        XmlPath xmlPath = response.xmlPath();
        String movieTitle = xmlPath.getString("root.movie.@title");
        String director = xmlPath.getString("root.movie.@director");

        System.out.println(movieTitle);


    }

    @Test
    void test2() {
        Response response = given().accept(ContentType.XML)
                .when()
                .queryParam("apikey", "88f66d1b")
                .and()
                .queryParam("r", "xml")
                .queryParam("s", "Harry Potter")
                .and()
                .get("http://www.omdbapi.com")
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract().response();

        XmlPath xmlPath = response.xmlPath();

        String  title = xmlPath.getString("root.result[0].@title");
        List<Object> titles = xmlPath.getList("root.result.@title");
        System.out.println("Titles: "+titles);
        System.out.println(title);

        /**
         *
         * Try to get all years and verify then are greater than 2000
         * Print each title and make sure each of them contains Harry Potter
         * Verify total result is 127
         */


    }
}

















