package org.example;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import javax.sound.midi.Soundbank;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
public class HeadersDemo {
    @Test
    public void headers() {
        RestAssured.given()
                .when().get("https://google.com")
                .then()
                .header("Content-Type", "text/html; charset=ISO-8859-1")
                .header("Content-Encoding", "gzip")
                .header("Server", "gws");
    }

    @Test
    public void header() {
        Response res = RestAssured.given()
                .when().get("https://google.com");
//      String header=res.getHeader("Content-Type");
//        System.out.println(header);
        // getting multiple headers

        Headers headers = res.getHeaders();
        for (Header hd : headers) {
            System.out.println(hd.getName() + " --  " + hd.getValue());
        }

    }

    //Logging Demo
    @Test
    public void Logging() {
        RestAssured.given()
                .when().get("https://google.com")
                .then()
//                .log().body();
//                .log().cookies();
                .log().headers();

    }
}