package org.example;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class ParsingXMLResponse {
    @Test
    public void XMLResponse() {
        RestAssured.given()
                .contentType("")
                .when().get("https://www.w3schools.com/xml/plant_catalog.xml")
                .then()
                .body("CATALOG.PLANT[0].COMMON", equalTo("Bloodroot"));

    }

    @Test
    public void XMLResponseBody() {
        Response res = RestAssured.given()
                .contentType("ContentType.JSON")
                .when().get("https://www.w3schools.com/xml/plant_catalog.xml");
        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.header("Content-Type"), "text/xml");
        String name = res.xmlPath().get("CATALOG.PLANT[0].COMMON").toString();
        Assert.assertEquals(name, "Bloodroot");
    }
boolean flag=false;
    @Test
    public void TestXMLResponseBody() {
        Response res = RestAssured.given()
                .contentType("ContentType.JSON")
                .when().get("https://www.w3schools.com/xml/plant_catalog.xml");
        XmlPath xml = new XmlPath(res.asString());
        List<String> value = xml.getList("CATALOG.PLANT.COMMON");
        Assert.assertEquals(value.size(), 36);
//Verify the name in list
        List<String> names = xml.getList("CATALOG.PLANT.COMMON");
        for (String name:names){
            System.out.println(name);
            if (name.equals("Anemone")){
                System.out.println("name is -- "+name);
                flag=true;
                break;
            }
        }
    }
}