import io.github.bonigarcia.wdm.WebDriverManager;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

public class Excel {
    @Test
    public void test() throws InterruptedException {
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.findElement(By.xpath("//*[@class='btn btn-sideblue']")).click();
        System.out.println("Clicked on menu icon");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.findElements(By.xpath("//span[text()=' Content Management ']")).get(4).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.findElement(By.xpath("//*[text()=' Add Work']")).click();


        driver.findElement(By.xpath("//span[text()='Update']/preceding-sibling::label/span[contains(@class, 'slider')]")).click();
        Select select = new Select(driver.findElement(By.xpath("//*[@id='dataType']")));
        select.selectByValue("MAJOR MEDIUM FLOW");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[text()=' All Structures of Rengali Irrigation Project under Package C4']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[text()='Work No']//following-sibling::select")).click();
       driver.findElement(By.xpath("//option[text()=' LCB-01/2018-19']")).click();

        String excelFilePath = "C:\\Users\\dange\\Downloads\\GO WATER,CE JICA.xlsx"; // Path to your Excel file

        try (FileInputStream fis = new FileInputStream(new File(excelFilePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            // Load the desired sheet
            String sheetName = "LCD-III";
            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                System.out.println("Sheet " + sheetName + " does not exist.");
                return;
            }


            // Loop through rows and send data to text fields
            for (int i = 41; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);

                // Get data from the 1st and 3rd columns (0-based indices)
                String name = row.getCell(1).getStringCellValue();
                double qt = row.getCell(3).getNumericCellValue();
                double div = row.getCell(4).getNumericCellValue();
                double rup = row.getCell(5).getNumericCellValue();

                if (name == null || name.isEmpty()) {
                    break;
                }
                System.out.println("Inserting Row " + (i + 1) + ": " + name + " - " + qt + " - " + rup);
                Thread.sleep(500);

                // Find the input fields for the newly added row (Assuming each row has unique inputs)
                WebElement item = driver.findElement(By.xpath("((//*[@class='card-body p-0'])[4]//div//div[1]//input)[" + (i - 40) + "]"));
                WebElement quantity = driver.findElement(By.xpath("((//*[@class='card-body p-0'])[4]//div//div[3]//input)[" + (i - 40) + "]"));
                WebElement deviation = driver.findElement(By.xpath("((//*[@class='card-body p-0'])[4]//div//div[4]//input)[" + (i - 40) + "]"));
                WebElement rupees = driver.findElement(By.xpath("((//*[@class='card-body p-0'])[4]//div//div[6]//input)[" + (i - 40) + "]"));


                String modifiedItemValue = name.replaceAll("^[0-9]+\\.?", "").trim();
                System.out.println("Modified   ::   " + modifiedItemValue);

                // Clear and send data to text fields in the current row
                item.clear();
                item.sendKeys(modifiedItemValue);

                quantity.clear();
                quantity.sendKeys(String.valueOf(qt));

                deviation.clear();
                deviation.sendKeys(String.valueOf(div));

                rupees.clear();
                rupees.sendKeys(String.valueOf(rup));// Convert age to String if numeric

                WebElement addRowButton = driver.findElement(By.xpath("//*[text()='Add Row']"));
                addRowButton.click();

            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


    }


}
