package com.cydeo.day06_Deserialization;

import com.cydeo.pojo.Employee;
import com.cydeo.pojo.Link;
import com.cydeo.pojo.Region;
import com.cydeo.utilities.Hr_TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class P03_HRDeserializationPOJO extends Hr_TestBase {


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
    public void test2(){

        JsonPath jsonPath = get("/employees")
                .then().statusCode(200)
                .extract().jsonPath();

        Employee employee = jsonPath.getObject("items[0]", Employee.class);
        System.out.println(employee);


    }

}




















