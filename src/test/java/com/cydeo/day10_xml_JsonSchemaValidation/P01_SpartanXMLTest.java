package com.cydeo.day10_xml_JsonSchemaValidation;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class P01_SpartanXMLTest extends SpartanAuthTestBase {

    /**
     * Given accept type is application/xml
     * And basic auth admin admin
     * When we send the request /api/spartans
     * Than status code is 200
     * And content type is application/xml
     * print first name
     */

    @DisplayName("GET /api/spartans hamcrest")
    @Test
    public void test1() {

        //* Given accept type is application/xml
        //     * And basic auth admin admin
        //     * When we send the request /api/spartans
        //     * Than status code is 200
        //     * And content type is application/xml
        //     *    print first name

        given().accept(ContentType.XML)
                .and()
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans")//.prettyPeek()
                .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.XML)
                .and()
                .body("List.item[0].id", is("1"))
                .body("List.item[0].name",is("Meade"))
                .body("List.item[0].gender",is("Male"));

        //<List>
        //  <item>
        //    <id>1</id>
        //    <name>Meade</name>
        //    <gender>Male</gender>
        //    <phone>9994128232</phone>
        //  </item>
        //  <item>


    }

    @DisplayName("GET /api/spartans xmlPath")
    @Test
    public void test() {

        //* Given accept type is application/xml
        //     * And basic auth admin admin
        //     * When we send the request /api/spartans
        //     * Than status code is 200
        //     * And content type is application/xml
        //     *    print first name

        Response response = given().accept(ContentType.XML)
                .and()
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans").prettyPeek()
                .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.XML)
                .and()
                .extract().response();

        //<List>
        //  <item>
        //    <id>1</id>
        //    <name>Meade</name>
        //    <gender>Male</gender>
        //    <phone>9994128232</phone>
        //  </item>
        //  <item>
        XmlPath xmlPath = response.xmlPath();
        String id = xmlPath.getString("List.item[0].id");
        String name = xmlPath.getString("List.item[0].name");
        String gender = xmlPath.getString("List.item[0].gender");
        List<String> names = xmlPath.getList("List.item.name");
        List<Object> ids = xmlPath.getList("List.item.id");


        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("names = " + names);
        System.out.println("ids = " + ids);

    }
}



















