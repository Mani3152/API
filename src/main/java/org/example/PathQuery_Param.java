package org.example;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import javax.sound.midi.Soundbank;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
@Test
public class PathQuery_Param {
    public void QueryAndPAth(){
        RestAssured.given()
                .pathParam("Path","objects")
                .queryParam("id","ff808181932badb601934d5eb802560e")
                .when().get("https://api.restful-api.dev/{Path}")
                .then().statusCode(200).log().all();
    }
@Test
    public void TestCookie(){
        RestAssured.given()
                .when().get("https://google.com")
                .then()
                .cookie("AEC","AZ6Zc-VAKULkQ3ZHKM_4hkEnU8ClhOGKENeKixDebTVa07G2UA7NkpCE39I")
                .log().all();
}
@Test
    public void getCookies(){
       Response res= RestAssured.given()
                .when().get("https://google.com");
//       String cookie_value=res.getCookie("AEC");
//    System.out.println(cookie_value);
    Map<String,String> cookies=res.getCookies();
    System.out.println(cookies);
    for ( String k: cookies.keySet()){
//        System.out.println(k);
       String value= res.getCookie(k);
        System.out.println(value);
    }
}
}
