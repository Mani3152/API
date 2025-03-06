
import io.github.bonigarcia.wdm.WebDriverManager;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ExistingTest {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-infobars");
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String from = "SECUNDERABAD JN - SC";
        String to = "NARASAPUR - NS";
        String trainNo = " NARASAPUR EXP (17256)";
        String date = "17/February/2025";



        driver.get("https://www.irctc.co.in/nget/train-search");

        driver.findElement(By.xpath("//*[text()=' LOGIN ']")).click();
        driver.findElement(By.xpath("//*[@formcontrolname='userid']")).sendKeys("manI_99");
        driver.findElement(By.xpath("//*[@formcontrolname='password']")).sendKeys("Manikanta@315");

//            Reading Captcha
//            WebElement img = driver.findElement(By.xpath("//img[@class='captcha-img']"));
//            Thread.sleep(500);
//            File src = img.getScreenshotAs(OutputType.FILE);
//            String path = "C:\\Users\\dange\\IdeaProjects\\API\\folder\\captcha.png";
//            FileHandler.copy(src, new File(path));
//            Thread.sleep(1000);
//            ITesseract tesseract = new Tesseract();
//            tesseract.setDatapath("C:\\Users\\dange\\Downloads\\Tess4J-3.4.8-src\\Tess4J\\tessdata");
//            tesseract.setLanguage("eng");
//            String captcha = tesseract.doOCR(new File(path));
//            String orgcaptcha = captcha.replace(" ", "");
//            System.out.println(orgcaptcha+"------------------");
//            driver.findElement(By.xpath("//input[@id='captcha']")).sendKeys(orgcaptcha);

//            driver.findElement(By.xpath("//button[text()='SIGN IN']")).click();
//            Thread.sleep(2000);
//            // Wait for manual login
//            System.in.read();


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(200));
        driver.findElement(By.xpath("(//*[text()='Logout'])[1]")).isDisplayed();
        driver.findElement(By.xpath("//*[@id='origin']//input")).sendKeys(from);
        driver.findElement(By.xpath("//*[@id='destination']//input")).sendKeys(to);
        driver.findElement(By.xpath("//*[@id='jDate']")).click();
        String day = date.split("/")[0];
        String month = date.split("/")[1];
        String Month = driver.findElement(By.xpath("//*[contains(@class, 'ui-datepicker-month')]")).getText();
        while (!month.equals(Month)) {
            driver.findElement(By.xpath("//*[contains(@class, 'ui-datepicker-next')]//span")).click();
            Month = driver.findElement(By.xpath("//*[contains(@class, 'ui-datepicker-month')]")).getText();
        }
        driver.findElement(By.xpath("//tr//td//a[text()='" + day + "']")).click();
        driver.findElement(By.xpath("//*[@id='journeyClass']")).click();
        driver.findElement(By.xpath("//*[@role='option']//span[text()='AC 3 Tier (3A)']")).click();
        driver.findElement(By.xpath("//*[@id='journeyQuota']")).click();
        driver.findElement(By.xpath("//*[@role='option']//span[text()='TATKAL']")).click();
        driver.findElement(By.xpath("//*[text()='Search']")).click();


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(250));
        driver.findElement(By.xpath("//*[text()='" + trainNo + "']//following::table[1]//tr//td[2]//*[contains(text(),'AC 3 Tier (3A)')]")).isDisplayed();


        String targetTimeString = "10:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime targetTime = LocalTime.parse(targetTimeString, formatter);
        System.out.println(targetTime + "---------------");

        // Wait until the target time is reached
        while (LocalTime.now().isBefore(targetTime)) {
            // Keep waiting until the time matches
        }
        String formattedDay = String.format("%02d", Integer.valueOf(day));
        // Click the element when the time matches
        WebElement waitelement = driver.findElement(By.xpath("//*[text()='" + trainNo + "']//following::table[1]//tr//td[2]//*[contains(text(),'AC 3 Tier (3A)')]"));
        waitelement.click();
        Thread.sleep(1000);
        System.out.println("Element clicked at: " + LocalTime.now());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
        WebElement element = driver.findElement(By.xpath("//following::table[1]//tr//td//*[contains(text(), '" + formattedDay + "')]"));
        WebElement firstButton = wait.until(ExpectedConditions.elementToBeClickable(element));
        firstButton.click();
        driver.findElement(By.xpath("//*[text()='" + trainNo + "']//following::button[1]")).click();
        WebElement nameField1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@placeholder='Name'])[1]")));
        nameField1.sendKeys("Manikanta");
        driver.findElement(By.xpath("(//input[@placeholder='Age'])[1]")).sendKeys("28");
        Select sc1 = new Select(driver.findElement(By.xpath("(//select[@formcontrolname='passengerGender'])[1]")));
        sc1.selectByVisibleText("Male");

        driver.findElement(By.xpath("//*[@for='autoUpgradation']")).click();
        driver.findElement(By.xpath("(//button[text()='Continue '])[1]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(250));
        driver.findElement(By.xpath("//img[@class='captcha-img']")).isDisplayed();


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(250));
        driver.findElement(By.xpath("//*[text()='Netbanking']")).isDisplayed();
        WebElement irctc = driver.findElement(By.xpath("(//span[text()='IRCTC eWallet'])[1]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", irctc);
//            driver.findElement(By.xpath("(//button[text()='Pay & Book '])[2]")).click();
    }
}








