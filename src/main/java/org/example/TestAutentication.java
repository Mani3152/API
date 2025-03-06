package org.example;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class TestAutentication {
    @Test
    public void BasicAuthentication() {
        RestAssured.given()
                .auth().basic("postman", "password")
                .when().get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();
    }

    @Test
    public void DigestAuthentication() {
        RestAssured.given()
                .auth().digest("postman", "password")
                .when().get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();
    }

    @Test
    public void PremeentiveAuthentication() {
        RestAssured.given()
                .auth().preemptive().basic("postman", "password")
                .when().get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();
    }

    @Test
    public void BarrerTokenAuthentication() {
        String barrerToken = "fghf676ryrggewhvbfewf";
        RestAssured.given()
                .headers("Authorisation", "Barrer " + barrerToken)
                .when().get("https://github.com/user/repo")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void Oauth2Authentication() {
        RestAssured.given()
                .auth().oauth2("gvhb2545cfgvhbnf")
                .when().get("https://github.com/user/repo")
                .then().statusCode(200).log().all();
    }
    @Test
    public void APIKeyAuthentication(){
        RestAssured.given()
                .pathParam("appID","yrufkndm4rujnkmerj")
                .when().get("https://postman-echo.com/basic-auth?id=2/aut=7")
                .then().statusCode(200)
                .log().all();

    }
    //Method2
    @Test
    public void aPIKeyAuthentication(){
        RestAssured.given()
                .queryParam("appID","yrufkndm4rujnkmerj")
                .pathParam("mypath","basic-auth")
                .queryParam("id",2)
                .queryParam("aut","7")
                .when().get("https://postman-echo.com/{mypath}")
                .then().statusCode(200)
                .log().all();

    }
}
