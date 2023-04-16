package com.cydeo.day06_Deserialization;

import com.cydeo.pojo.Employee;
import com.cydeo.pojo.Link;
import com.cydeo.pojo.Region;
import com.cydeo.pojo.region.Items;
import com.cydeo.pojo.region.Regions;
import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class P03_HRDeserializationPOJO extends HrTestBase {


    @DisplayName("GET regions with POJO")
    @Test
    void test() {

        Response response = given().accept(ContentType.JSON)
                //.log().ifValidationFails()
                .and().get("/regions/")
                //.prettyPeek()
                .then().statusCode(HttpStatus.SC_OK)
                .and().contentType(ContentType.JSON.toString())
                .extract().response();


        JsonPath jsonPath = response.jsonPath();

        Region region1 = jsonPath.getObject("items[0]", Region.class);
        System.out.println();
        System.out.println("region1.getRegion_id() = " + region1.getRegionId());
        System.out.println("region1.getRegion_name() = " + region1.getRegionName());
        System.out.println("region1.getLinks() = " + region1.getLinks());

        System.out.println("========================\n");
        Link links = jsonPath.getObject("items[0].links[0]", Link.class);

        System.out.println(links);
        System.out.println("links.getHref() = " + links.getHref());
        System.out.println("links.getRel() = " + links.getRel());


    }

    @DisplayName("GET employee to deserialization to POJO with only required fields")
    @Test
    public void test2() {

        JsonPath jsonPath = get("/employees")
                .then().statusCode(200)
                .extract().jsonPath();

        Employee employee = jsonPath.getObject("items[0]", Employee.class);
        System.out.println(employee);


    }

    //Given accept is application/json
    //    When send request  to /regions endpoint
    //    Then status should be 200
    //            verify that region ids are 1,2,3,4
    //            verify that regions names "Europe" ,"Americas" , "Asia"," Middle East and Africa"
    //            verify that count is 4
    //        -- Create Regions POJO
    //        -- And ignore field that you dont need

    @DisplayName("GET /regions POJO")
    @Test
    public void test3() {

        Response response = given().accept(ContentType.JSON)
                .when().get("/regions")
                .then()
                .statusCode(200).extract().response();

        JsonPath jsonPath = response.jsonPath();

        Regions regions = response.as(Regions.class);

        List<Integer> regionIds = new ArrayList<>();
        List<String> regionNames = new ArrayList<>();

        for (Items eachRegion : regions.getItems()) {
            regionIds.add(eachRegion.getRegionId());
            regionNames.add(eachRegion.getRegionName());
        }

        //            verify that region ids are 1,2,3,4
        //            verify that regions names "Europe" ,"Americas" , "Asia"," Middle East and Africa"
        //            verify that count is 4
        assertThat(regionIds, hasItems(1, 2, 3, 4));
        assertThat(regionNames, hasItems("Europe" ,"Americas" , "Asia", "Middle East and Africa"));
        assertThat(regions.getCount(), is(4));






    }

}




















