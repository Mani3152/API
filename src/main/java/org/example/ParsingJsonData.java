package org.example;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.sound.midi.Soundbank;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class ParsingJsonData {

    @Test
    public void JsonData() {
        String res = "[0].data[\"CPU model\"]";
        Response response = RestAssured.given()
                .contentType("ContentType.JSON")
                .when().get("https://api.restful-api.dev/objects?id=ff808181932badb601934d5eb802560e")
                .then().statusCode(200)
                .header("Content-Type", "application/json").extract().response();
//                .body("[0].data[\"CPU model\"]", equalTo("Intel Core i9"));
//        Assert.assertEquals(response.getStatusCode(),200);
//        Assert.assertEquals(response.header("ContentType"),"ContentType.JSON");
        String cpuModel = response.jsonPath().getString("[0].data[\"CPU model\"]");
        System.out.println(cpuModel);
        Assert.assertEquals(cpuModel, "Intel Core i9");
    }

    @Test
    public void GetJsonData() {
        Response response = RestAssured.given()
                .contentType("ContentType.JSON")
                .when().get("https://api.restful-api.dev/objects");
//        JSONObject JO = new JSONObject(response);
        JSONArray JO = new JSONArray(response.asString());
//    for (int i=0;i<JO.getJSONArray("data").length();i++){
//        System.out.println(i);
//    }
        for (int i = 0; i < JO.length(); i++) {
            // Get the JSONObject for the current element
            JSONObject product = JO.getJSONObject(i);
            if (!product.isNull("data")) {
                JSONObject data = product.getJSONObject("data");
                if (data.has("color")) {
                    // Extract and print the color
                    String color = data.getString("color");
                    System.out.println("Product Name: " + product.getString("name") + ", Color: " + color);
                } else {
                    System.out.println("Product Name: " + product.getString("name") + ", Color: Not available");
                }
            } else {
                System.out.println("Color not available");
            }
        }
    }
}