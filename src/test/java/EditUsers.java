import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

public class EditUsers {
    @Test
    public void test() throws InterruptedException, IOException {
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
        driver.findElement(By.xpath("//i[@class='bi bi-person-circle']")).click();
        driver.findElement(By.xpath("//*[text()='Profile']")).click();


        String excelFilePath = "C:\\Users\\dange\\Downloads\\GO WATER,CE JICA.xlsx"; // Path to your Excel file

        FileInputStream fis = new FileInputStream(new File(excelFilePath));
        Workbook workbook = new XSSFWorkbook(fis);

        // Load the desired sheet
        String sheetName = "Sheet1";
        Sheet sheet = workbook.getSheet(sheetName);


        // Loop through rows and send data to text fields
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);

            // Get data from the 1st and 3rd columns (0-based indices)
            String username = row.getCell(0).getStringCellValue();
            System.out.println("Username " + username);


            driver.findElement(By.xpath("//tr//td[3]//div[text()='" + username + "']//ancestor::tr//td[1]//button[2]//i")).click();
            driver.findElement(By.xpath("(//*[@class='c-list'])[2]")).click();
            String locations = row.getCell(1).getStringCellValue();
            String Role[] = locations.split("##");
            System.out.println("Role lenght " + Role.length);
            for (int k = 0; k < Role.length; k++) {
                driver.findElement(By.xpath("(//input[@aria-labelledby='searchIcon'])[2]")).sendKeys(Role[k]);
                Thread.sleep(1500);
                WebElement checkbox = driver.findElement(By.xpath("//*[contains(text(),'Location')]//following::li//input[@type='checkbox']//following-sibling::label"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);
                Thread.sleep(500); // Allow time for scrolling
                checkbox.click();
                Thread.sleep(500);
                driver.findElement(By.xpath("(//*[@id='searchIcon'])[2]//following-sibling::span//c-icon")).click();
                Thread.sleep(1000);


            }
        }
        driver.findElement(By.xpath("//input[@role='combobox']")).click();
        driver.findElement(By.xpath("//div[@role='option' and contains(., 'Section Engineer')]")).click();
//        driver.findElement(By.xpath("//button[text()='Update ']")).click();


    }
}