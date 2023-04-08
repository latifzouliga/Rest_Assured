package com.cydeo.homework;


import com.cydeo.pojo.ConstructorPOJO;
import com.cydeo.pojo.ConstructorTable;
import com.cydeo.pojo.MRData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class homework_3_task2 {

    @DisplayName("GET /constructorStandings/1/constructors.json Hamcrest")
    @Test
    void task1() {
        //* - Given accept type is json
        // * - When user send request /constructorStandings/1/constructors.json
        // * - Then verify status code is 200
        // * - And content type is application/json; charset=utf-8
        // * - And total is 17
        // * - And limit is 30
        // * - And each constructor has constructorId
        // * - And constructor has name
        // * - And size of constructor is 17
        // * - And constructor IDs has "benetton", "mercedes", "team_lotus"


        given().accept(ContentType.JSON)
                .when()
                //http://ergast.com/api/f1/constructorStandings/1/constructors.json
                .get("http://ergast.com/api/f1/constructorStandings/1/constructors.json")
                .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON.toString())
                .body("MRData.total", is("17"))
                .body("MRData.limit", is("30"))
                .body("MRData.ConstructorTable.Constructors.constructorId", is(notNullValue()))
                .body("MRData.ConstructorTable.Constructors.name", is(notNullValue()))
                .body("MRData.ConstructorTable.Constructors", is(hasSize(17)));
        //.body("MRData.ConstructorTable.Constructors.constructorId", is(containsInAnyOrder("benetton", "mercedes", "team_lotus")));


    }

    @DisplayName("GET /constructorStandings/1/constructors.json JsonPath")
    @Test
    void task2() {
        //* - Given accept type is json
        // * - When user send request /constructorStandings/1/constructors.json
        // * - Then verify status code is 200
        // * - And content type is application/json; charset=utf-8
        // * - And total is 17
        // * - And limit is 30
        // * - And each constructor has constructorId
        // * - And constructor has name
        // * - And size of constructor is 17
        // * - And constructor IDs has "benetton", "mercedes", "team_lotus"


        JsonPath jsonPath = given().accept(ContentType.JSON)
                .when()
                //http://ergast.com/api/f1/constructorStandings/1/constructors.json
                .get("http://ergast.com/api/f1/constructorStandings/1/constructors.json")
                .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON.toString())
                .extract().jsonPath();

        // * - And total is 17
        assertEquals("17", jsonPath.getString("MRData.total"));

        // * - And limit is 30
        assertEquals("30", jsonPath.getString("MRData.limit"));
        // * - And each constructor has constructorId
        assertThat(jsonPath.getList("MRData.ConstructorTable.Constructors.constructorId"), is(notNullValue()));

        // * - And constructor has name
        assertThat(jsonPath.getList("MRData.ConstructorTable.Constructors.name"), is(notNullValue()));

        // * - And size of constructor is 17
        assertThat(jsonPath.getList("MRData.ConstructorTable.Constructors"), is(hasSize(17)));

        // * - And constructor IDs has "benetton", "mercedes", "team_lotus"
        assertThat(jsonPath.getList("MRData.ConstructorTable.Constructors.constructorId"),is(containsInRelativeOrder("benetton", "mercedes", "team_lotus")));


    }

    @DisplayName("GET /constructorStandings/1/constructors.json Map")
    @Test
    void task3() {

        List<String> ids = Arrays.asList("benetton", "mercedes", "team_lotus");

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .when()
                //http://ergast.com/api/f1/constructorStandings/1/constructors.json
                .get("http://ergast.com/api/f1/constructorStandings/1/constructors.json")
                .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON.toString())
                .extract().jsonPath();

        Map<String, Object> jsMap = jsonPath.getObject("MRData", Map.class);

        String o = (String) jsMap.get("total");

        // * - And total is 17
        assertThat("17", is(jsMap.get("total")));

        // * - And limit is 30
        assertThat("30", is(jsMap.get("limit")));

        // * - And each constructor has constructorId

        List<String> constructorIds = new ArrayList<>();
        List<Map> list = jsonPath.getList("MRData.ConstructorTable.Constructors", Map.class);
        for (Map each : list) {
            assertThat(each.get("constructorId"), is(notNullValue()));
            assertThat(each.get("name"), is(notNullValue()));
            constructorIds.add((String) each.get("constructorId"));
        }


        List<Object> list1 = jsonPath.getList("MRData.ConstructorTable.Constructors.constructorId");
        System.out.println(list1);

        assertThat(list1, containsInRelativeOrder("benetton", "mercedes", "team_lotus"));
        // * - And constructor has name
        // * - And size of constructor is 17
        assertThat(list.size(), is(17));

        // * - And constructor IDs has ["benetton", "mercedes", "team_lotus"]
        //System.out.println(list1);

    }

    @DisplayName("GET /constructorStandings/1/constructors.json POJO")
    @Test
    void task4() {

        List<String> ids = Arrays.asList("benetton", "mercedes", "team_lotus");

        Response response = given().accept(ContentType.JSON)
                .when()
                //http://ergast.com/api/f1/constructorStandings/1/constructors.json
                .get("http://ergast.com/api/f1/constructorStandings/1/constructors.json")
                .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON.toString())
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        Object o = jsonPath.getList("MRData.ConstructorTable.Constructors").get(0);
        List<Object> o1 = jsonPath.getList("MRData.ConstructorTable.Constructors");

        MRData mrData = jsonPath.getObject("MRData", MRData.class);
        System.out.println("mrData.getTotal() = " + mrData.getTotal());
        System.out.println("as.getLimit() = " + mrData.getLimit());

        Object oo = jsonPath.getList("MRData.ConstructorTable.Constructors", ConstructorPOJO.class);
        System.out.println(oo);

        ConstructorTable object = jsonPath.getObject("MRData.ConstructorTable", ConstructorTable.class);
        System.out.println(object.getConstructorStandings());

        System.out.println("mrData.getConstructorTable().getConstructorPOJOS() = " + mrData.getConstructorTable().getConstructorPOJOS());
    }
}

/**
 * TASK 2 : Solve same task with 4 different way
 * - Use JSONPATH
 * int total = jsonpath.getInt(“pathOfField”)
 * - Use HAMCREST MATCHERS
 * then().body(..........)
 * Print all names of constructor by using extract method after HamCrest
 * - Convert Constructor information to Java Structure
 * List<Map<String,Object>> constructorMap=jsonpath.getList(“pathOfConsts”);
 * - Convert Constructor information POJO Class
 * List<ConstructorPOJO>
 * constr=getObject(“pathOfConstr”,ConstructorPOJO.class)
 * NOTE
 * —> There is a class in JAVA That’s why give your class name
 * ConstrutorPOJO to separate from existing one. Wrong imports may cause
 * issue
 * - Given accept type is json
 * - When user send request /constructorStandings/1/constructors.json
 * - Then verify status code is 200
 * - And content type is application/json; charset=utf-8
 * - And total is 17
 * - And limit is 30
 * - And each constructor has constructorId
 * - And constructor has name
 * - And size of constructor is 17
 * - And constructor IDs has “benetton”, “mercedes”,”team_lotus
 */