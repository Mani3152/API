import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

public class UpdatePasswords {


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
        String excelFilePath = ""; // Path to your Excel file

        FileInputStream fis = new FileInputStream(new File(excelFilePath));
        Workbook workbook = new XSSFWorkbook(fis);
        String sheetName = "Sheet1";
        Sheet sheet = workbook.getSheet(sheetName);


        // Loop through rows and send data to text fields
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            String username = row.getCell(0).getStringCellValue();
            System.out.println("Username " + username);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
            driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Odisha@12345");
            driver.findElement(By.xpath("//input[@name='login']")).click();

            String name = driver.findElement(By.xpath("//h1")).getText();
            if (name.equals("Update password")) {
                driver.findElement(By.xpath("//input[@name='password-new']")).sendKeys(username);
                System.out.println("entered username");
                driver.findElement(By.xpath("//input[@name='password-confirm']")).sendKeys("Pms@123");
            }
        }
    }}