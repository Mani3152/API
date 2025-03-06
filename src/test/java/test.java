
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class test {
    @Test
    public void sampleTest() {
        Response response = RestAssured.given()
                .when()
                .get("https://api.restful-api.dev/objects?id=3&id=5&id=10");
        JSONArray JO = new JSONArray(response.asString());
        for (int i = 0; i < JO.length(); i++) {
            JSONObject object = JO.getJSONObject(i);
            String name = object.getString("name");
            System.out.println(name + "--1---");
            String no = object.getString("id");
            System.out.println(no + "----2----");

            if (!object.isNull("data")) {
                JSONObject value = object.getJSONObject("data");
                if (value.has("Screen size")) {
                    System.out.println(value.get("Screen size"));
                    System.out.println("");
                } else {
                    System.out.println("Data not available");
                }
            } else {
                System.out.println("Data not available");
            }
        }

    }

    @Test
    public void test() {
        Response response = RestAssured.given()
                .when().get("https://fakestoreapi.com/products");
        JSONArray jsonArray = new JSONArray(response.asString());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            if (!object.isNull("rating")) {
                JSONObject value = object.getJSONObject("rating");
                double rate = value.getDouble("rate");
                if (rate == 3) {
                    System.out.println(value.get("count") + "---------");
                    break;
                }
            } else {
                System.out.println("no data");
            }
        }
    }

    @Test
    public void gbfv() {
        Response response = RestAssured.given()
                .when().get("https://fakestoreapi.com/products").then()
                .extract().response();
        System.out.println(response.prettyPrint());
        JsonPath path = response.jsonPath();
        String naes = path.getString("rating.rate");
        System.out.println(naes);
    }

    @Test
    public void hrjnk() {
        Response response = RestAssured.given()
                .when().get("https://freetestapi.com/api/v1/products/20");
        JSONObject object=new JSONObject(response.asString());
       JSONArray value= object.getJSONArray("plants_included");
        System.out.println(value);
    }
}
