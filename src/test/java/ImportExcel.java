import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class ImportExcel {
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
        System.out.println("clicked on login button");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.findElement(By.xpath("//div[text()=' Project Management ']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        System.out.println("Clicked on project management button");
        driver.findElement(By.xpath("//button[text()=' Total No of Projects :']")).click();
        Thread.sleep(2000);

        // Locate multiple elements
        List<WebElement> elements = driver.findElements(By.xpath("//div[@id='major']//div//a//h3"));

        // Create an Excel workbook and sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Web Data");

        // Iterate through elements and write to Excel
        int rowNum = 0;
        for (WebElement element : elements) {
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue(element.getText());  // Get text and write it to Excel
        }

        // Save the file
        FileOutputStream fileOut = new FileOutputStream("C:\\Users\\dange\\Desktop\\New Microsoft Excel Worksheet (2).xlsx");
        workbook.write(fileOut);

        // Close resources
        fileOut.close();
        workbook.close();
    }
}
