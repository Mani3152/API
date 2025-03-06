import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class getDataFromAPI {
    @Test
    public static JSONArray test() throws JsonProcessingException {
        JSONArray payloads = new JSONArray();
        JSONObject object = new JSONObject();
        object.put("eDate", "20241102");
        object.put("sDate", "20240601");
        object.put("pUUID", "d19a5290-2e40-494a-83d2-98f4c845b1f1");
        object.put("pType", "STATE");
        object.put("format", "DAILY");
        object.put("cType", "DISTRICT");
        object.put("pEUUID", "d19a5290-2e40-494a-83d2-98f4c845b1f1");
        object.put("src", "SENTINEL1 DEM");
        object.put("aggr", "LATEST");
        object.put("viewPEUUID", "d19a5290-2e40-494a-83d2-98f4c845b1f1");
        object.put("view", "ADMIN");
        object.put("dept", "WRD");
        object.put("cmp", "MITANK");
        payloads.put(object);

        JSONObject object1 = new JSONObject();
        object1.put("aggr", "LATEST");
        object1.put("cType", "BLOCK");
        object1.put("BLOCK", "MITANK");
        object1.put("dept", "WRD");
        object1.put("eDate", "20241102");
        object1.put("format", "DAILY");
        object1.put("pEUUID", "2edcf805-c410-439d-8da1-2ac4ef80b6e1");
        object1.put("pType", "DISTRICT");
        object1.put("pUUID", "2edcf805-c410-439d-8da1-2ac4ef80b6e1");
        object1.put("sDate", "20240601");
        object1.put("src", "SENTINEL1 DEM");
        object1.put("view", "ADMIN");
        object1.put("viewPEUUID", "d19a5290-2e40-494a-83d2-98f4c845b1f1");
        payloads.put(object1);

        return payloads;


    }

    @Test
    public void PostAPI() throws JsonProcessingException {
        JSONObject object = new JSONObject();
        object.put("eDate", "20241102");
        object.put("sDate", "20240601");
        object.put("pUUID", "d19a5290-2e40-494a-83d2-98f4c845b1f1");
        object.put("pType", "STATE");
        object.put("format", "DAILY");
        object.put("cType", "DISTRICT");
        object.put("pEUUID", "d19a5290-2e40-494a-83d2-98f4c845b1f1");
        object.put("src", "SENTINEL1 DEM");
        object.put("aggr", "LATEST");
        object.put("viewPEUUID", "d19a5290-2e40-494a-83d2-98f4c845b1f1");
        object.put("view", "ADMIN");
        object.put("dept", "WRD");
        object.put("cmp", "MITANK");


        Response response = RestAssured.given()
                .contentType("application/json")
                .body(object.toString())
                .when().post("https://gowater.vassarlabs.com/wrimsApi/mitank/aggregatedData");
        System.out.println(response.prettyPrint());

        JSONObject jsonObject = new JSONObject(response.asString());
        JSONArray res = jsonObject.getJSONArray("response");
        for (int i = 0; i < res.length(); i++) {
            JSONObject value = res.getJSONObject(i);
            if (value.has("md")) {
                JSONObject ob = value.getJSONObject("md");
                // Extract the "tc" value
                if (ob.has("tc")) {
                    double tcValue = ob.getDouble("tc");
                    System.out.println("TC Value: " + tcValue);
                }
            }
        }
    }


    @Test
    public void extractJsonData() throws JSONException, JsonProcessingException {
        JSONObject object = new JSONObject();
        object.put("eDate", "20241102");
        object.put("sDate", "20240601");
        object.put("pUUID", "d19a5290-2e40-494a-83d2-98f4c845b1f1");
        object.put("pType", "STATE");
        object.put("format", "DAILY");
        object.put("cType", "DISTRICT");
        object.put("pEUUID", "d19a5290-2e40-494a-83d2-98f4c845b1f1");
        object.put("src", "SENTINEL1 DEM");
        object.put("aggr", "LATEST");
        object.put("viewPEUUID", "d19a5290-2e40-494a-83d2-98f4c845b1f1");
        object.put("view", "ADMIN");
        object.put("dept", "WRD");
        object.put("cmp", "MITANK");
        Response response = RestAssured.given()
                .contentType("application/json")
                .body(object.toString())
                .when().post("https://gowater.vassarlabs.com/wrimsApi/mitank/aggregatedData");
        System.out.println(response.prettyPrint());
        JSONObject jsonObject = new JSONObject(response.asString());
        JSONArray res = jsonObject.getJSONArray("response");
        Map<String, Map<String, Object>> jsonData = new HashMap<>();
        for (int i = 0; i < res.length(); i++) {
            JSONObject value = res.getJSONObject(i);
            if (value.has("md")) {
                Map<String, Object> mdData = value.getJSONObject("md").toMap();
                if (value.has("nm") && !value.isNull("nm")) {
                    jsonData.put(value.getString("nm"), mdData);
                } else {
                    System.out.println("Key 'nm' not found or is null in response object.");
                }
            } else {
                System.out.println("Key 'md' not found in response object.");
            }
        }


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String jsonString = objectMapper.writeValueAsString(jsonData);
        System.out.println(jsonString);
    }


    public static class BookTicket {




            public static void main(String[] args) {
                // Step 1: Setup ChromeOptions to use a specific user profile
                ChromeOptions options = new ChromeOptions();
                // Replace with your Chrome user profile path
                options.addArguments("user-data-dir=/path/to/your/chrome/profile");

                // Initialize WebDriver
                WebDriver driver = new ChromeDriver(options);

                try {
                    // Step 2: Navigate to the login page
                    driver.get("https://your-website.com/login");
                    System.out.println("Log in manually. After logging in, press Enter to continue...");

                    // Wait for manual login
                    System.in.read();

                    // Step 3: Automate the remaining steps
                    // Navigate to ticket booking page
                    driver.get("https://your-website.com/book-ticket");

                    // Example: Fill the date field
                    WebElement datePicker = driver.findElement(By.id("date-picker"));
                    datePicker.sendKeys("2025-01-10");

                    // Example: Select a destination
                    WebElement destinationDropdown = driver.findElement(By.id("destination"));
                    destinationDropdown.click();
                    WebElement destinationOption = driver.findElement(By.xpath("//option[@value='destination-name']"));
                    destinationOption.click();

                    // Example: Submit booking
                    WebElement submitButton = driver.findElement(By.id("submit-button"));
                    submitButton.click();

                    System.out.println("Automation process completed.");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // Close the browser after a delay (optional)
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    driver.quit();
                }
            }
        }
}