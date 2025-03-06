import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Ticket {


    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-infobars");
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String from = "SECUNDERABAD JN - SC";
        String to = "NARASAPUR - NS";
        String trainNo = " NARASAPUR EXP (17256)";
        String date = "17/March/2025";


        driver.get("https://www.irctc.co.in/nget/train-search");
        driver.findElement(By.xpath("//*[text()=' LOGIN ']")).click();
        driver.findElement(By.xpath("//*[@formcontrolname='userid']")).sendKeys("manI_99");
        driver.findElement(By.xpath("//*[@formcontrolname='password']")).sendKeys("Manikanta@315");


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
        driver.findElement(By.xpath("//*[@role='option']//span[text()='Sleeper (SL)']")).click();
//        driver.findElement(By.xpath("//*[@id='journeyQuota']")).click();
//        driver.findElement(By.xpath("//*[@role='option']//span[text()='TATKAL']")).click();
        driver.findElement(By.xpath("//*[text()='Search']")).click();
        String formattedDay = String.format("%02d", Integer.valueOf(day));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(250));
        driver.findElement(By.xpath("//*[text()='" + trainNo + "']//following::table[1]//tr//td[1]//*[contains(text(),'Sleeper (SL)')]")).isDisplayed();


        String targetTimeString = "11:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime targetTime = LocalTime.parse(targetTimeString, formatter);
        System.out.println(targetTime + "---------------");

        // Wait until the target time is reached
        while (LocalTime.now().isBefore(targetTime)) {
            // Keep waiting until the time matches
        }

        // Click the element when the time matches
        WebElement waitelement = driver.findElement(By.xpath("//*[text()='" + trainNo + "']//following::table[1]//tr//td[1]//*[contains(text(),'Sleeper (SL)')]"));
        waitelement.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
        WebElement element = driver.findElement(By.xpath("//following::table[1]//tr//td//*[contains(text(), '" + formattedDay + "')]"));
        WebElement firstButton = wait.until(ExpectedConditions.elementToBeClickable(element));
        firstButton.click();
//        String value = driver.findElement(By.xpath("//*[text()='" + trainNo + "']//following::button[1]")).getAttribute("class");
//        System.out.println(value+"<====================>");
//        while (value.contains("disable-book")){
//            firstButton.click();
//        }

        driver.findElement(By.xpath("//*[text()='" + trainNo + "']//following::button[1]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
        driver.findElement(By.xpath("//span[text()='Passenger Details']")).isDisplayed();
        WebElement nameField1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@placeholder='Name'])[1]")));
        nameField1.sendKeys("Ganesh");
        driver.findElement(By.xpath("(//input[@placeholder='Age'])[1]")).sendKeys("25");
        Select sc1 = new Select(driver.findElement(By.xpath("(//select[@formcontrolname='passengerGender'])[1]")));
        sc1.selectByVisibleText("Male");

//        driver.findElement(By.xpath("//*[text()='+ Add Passenger']")).click();
//
//        WebElement nameField2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@placeholder='Name'])[2]")));
//        nameField2.sendKeys("Srikanth");
//        driver.findElement(By.xpath("(//input[@placeholder='Age'])[2]")).sendKeys("28");
//        Select sc = new Select(driver.findElement(By.xpath("(//select[@formcontrolname='passengerGender'])[2]")));
//        sc.selectByVisibleText("Male");


        driver.findElement(By.xpath("//*[@for='autoUpgradation']")).click();
        driver.findElement(By.xpath("(//button[text()='Continue '])[1]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(250));
        driver.findElement(By.xpath("//img[@class='captcha-img']")).isDisplayed();


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(250));
        driver.findElement(By.xpath("//*[text()='Netbanking']")).isDisplayed();
        WebElement irctc = driver.findElement(By.xpath("(//span[text()='IRCTC eWallet'])[1]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", irctc);
//        driver.findElement(By.xpath("(//button[text()='Pay & Book '])[2]")).click();


    }
}




