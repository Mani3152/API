import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class WhatsApp {

    @Test
    public void Whatsapp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://web.whatsapp.com/");
        System.out.println("Please scan the QR code to log in to WhatsApp Web.");
        Thread.sleep(15000); // Wait for the user to log in

        // Select a specific chat
        WebElement chat = driver.findElement(By.xpath("//*[@title='Madhava Reddy']")); // Replace 'Chat Name' with the actual chat title
        chat.click();
        Thread.sleep(2000);

        String name = driver.findElement(By.xpath("(//div[contains(@class, 'message-in')])[last()]")).getText();
        System.out.println(name);
        // Get all messages from the chat
//            List<WebElement> messages = driver.findElements(By.xpath("(//div[contains(@class, 'message-in')])[last()]"));
//            for (WebElement message : messages) {
//                // Extract message text
//                String messageText = message.findElement(By.xpath(".//span[@class='selectable-text']")).getText();
//                System.out.println("Message: " + messageText);
//            }

        // Close the browser
//            driver.quit();
    }
}


