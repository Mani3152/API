import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import groovy.json.JsonToken;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.*;

public class WebTableData {
    @Test
    public void webTable() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://gowater.vassarlabs.com/");

        driver.findElement(By.xpath("//i[@class='bi bi-person-circle']")).isEnabled();
        //clicking on profile icon for login into application
        driver.findElement(By.xpath("//i[@class='bi bi-person-circle']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        System.out.println("clicked on Profile icon");
        //click on the login button
        driver.findElement(By.xpath("//a[text()=' Login']")).click();
        System.out.println("clicked on Login option under the profile icon");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //enter the username and password
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("GOWATERADMIN");
        System.out.println("entered username");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("GOWATERADMIN");
        System.out.println("entered password");
        driver.findElement(By.xpath("//input[@name='login']")).click();
        Thread.sleep(8000);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.findElement(By.xpath("(//img)[8]")).click();
        Thread.sleep(1000);
        System.out.println("Dashboad clicked");
        driver.findElement(By.xpath("(//li)[3]")).click();

        driver.findElement(By.xpath("(//li)[3]//div[text()=' Supply ']")).click();
        driver.findElement(By.xpath("(//li)[3]//ul//li[4]//span[2][text()=' MI Reservoirs ']")).click();

        Map<String, Map<String, Object>> districts = new TreeMap<>();
        int size = driver.findElements(By.xpath("//table/tbody/tr[not(position() = last())]")).size();
        int size1 = driver.findElements(By.xpath("//tbody//tr[not(position() = last())]//td[2]//span")).size();

        for (int i = 1; i < size - 1; i++) {
            Map<String, Object> user = new HashMap<>();
            user.put("cnt", driver.findElement(By.xpath("//tbody//tr[" + i + "]//td[3]")).getText());
            user.put("fp", driver.findElement(By.xpath("//tbody//tr[" + i + "]//td[6]")).getText());
            user.put("wa", driver.findElement(By.xpath("//tbody//tr[" + i + "]//td[7]")).getText());

            districts.put(driver.findElement(By.xpath("//tbody//tr[not(position() = last())][" + i + "]//td[2]//span")).getText(), user);
        }

        System.out.println(districts);
    }
}
