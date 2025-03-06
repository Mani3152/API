package org.example;

import com.google.gson.JsonObject;
import groovy.transform.ASTTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import   io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import    org.hamcrest.Matchers.*;
import io.restassured.RestAssured.*;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.MatcherAssert.assertThat;

public class getcall {
    String id;
//@Test
   public void get(){
   /* Response res= RestAssured.given().baseUri("https://dummyapi.online/")
              .when().get("api/movies")
              .then().extract().response();
   System.out.println(res.prettyPrint());
   System.out.println(res.getStatusCode());

    Response res=RestAssured.given().baseUri("https://dummyapi.online/")
            .when().get("api/movies")
            .then().assertThat().statusCode(200)
            .extract().response();
   List<String> name= JsonPath.from(res.asString()).getList("movie");*/

    RestAssured.given()
            .when().get("https://dummyapi.online/api/movies")
            .then().statusCode(200)
            .log().all();
}
@Test
    public void post(){
//    HashMap<String, Object> requestBody = new HashMap<>();
//    requestBody.put("name", "Pro 18");
//    HashMap<String,String>innerdata=new HashMap<>();
//    innerdata.put("year","2024");
//    innerdata.put("price","2500");
//    innerdata.put("CPU model","Ryzan 5");
//    innerdata.put("Hard disk size",".5TB");
//    requestBody.put("data",innerdata);

    JSONObject object=new JSONObject();
    JSONObject requestBody=new JSONObject();
    object.put("year","2024");
    object.put("price","2500");
    object.put("CPU model","Ryzan 5");
    object.put("Hard disk size",".5TB");
    requestBody.put("name","mani");
    requestBody.put("data",object);

    RestAssured.given()
            .contentType(ContentType.JSON)
            .body(requestBody.toString())
            .when().post("https://api.restful-api.dev/objects")
//            .jsonPath().getString("id"));
//    System.out.println(id);
            .then().statusCode(200)
            .log().all();
}
@Test
    public void Put(){
    HashMap<String, Object> requestBody = new HashMap<>();
    requestBody.put("name", "Pro 17");
    HashMap<String,String>innerdata=new HashMap<>();
    innerdata.put("year","2024");
    innerdata.put("price","3100");
    innerdata.put("CPU model","Ryzan 6");
    innerdata.put("Hard disk size","15TB");
    innerdata.put("color","red");
    requestBody.put("data",innerdata);

    RestAssured.given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when().put("https://api.restful-api.dev/objects/"+id)
            .then().statusCode(200)
            .log().all();
}
@Test
    public void Delete(){
    RestAssured.given()
            .contentType(ContentType.JSON)
            .when().delete("https://api.restful-api.dev/objects/ff808181932badb6019349270cf84980")
            .then().statusCode(200)
            .log().all();
}

}
