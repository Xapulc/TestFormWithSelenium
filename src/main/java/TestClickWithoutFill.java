import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

public class TestClickWithoutFill {
    private WebDriver driver;
    private String baseUrl;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        baseUrl = "https://moscow-job.tinkoff.ru/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testClickWithoutFill() {
        driver.get(baseUrl + "/");
        driver.findElement(By.name("fio")).click();
        driver.findElement(By.cssSelector("article.Scene__form_3ubwk")).click();
        driver.findElement(By.cssSelector("div.Error__errorMessage_q8BBY")).click();
        driver.findElement(By.cssSelector("div.Row__row_AjrJL")).click();
        driver.findElement(By.cssSelector("div.Error__errorMessage_q8BBY")).click();
        assertEquals("Поле обязательное", driver.findElement(By.cssSelector("div.Error__errorMessage_q8BBY")).getText());
        driver.findElement(By.name("email")).click();
        driver.findElement(By.cssSelector("#form > div")).click();
        driver.findElement(By.cssSelector("article.Scene__form_3ubwk")).click();
        driver.findElement(By.cssSelector(".Row__row_AjrJL:nth-child(2) .Error__errorMessage_q8BBY")).click();
        assertEquals("Поле обязательное", driver.findElement(By.cssSelector(".Row__row_AjrJL:nth-child(2) .Error__errorMessage_q8BBY")).getText());
        driver.findElement(By.name("phone")).click();
        driver.findElement(By.cssSelector("#form > div")).click();
        assertEquals("Поле обязательное", driver.findElement(By.cssSelector("div.Error__errorMessage_q8BBY")).getText());
        driver.findElement(By.name("city")).click();
        driver.findElement(By.cssSelector("#form > div")).click();
        driver.findElement(By.cssSelector("article.Scene__form_3ubwk")).click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
