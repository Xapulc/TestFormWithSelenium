import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

public class TestClickWithFill {
    private WebDriver driver;
    private String baseUrl;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        baseUrl = "https://moscow-job.tinkoff.ru/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testClickWithFill() {
        driver.get(baseUrl + "/");
        driver.findElement(By.cssSelector(".panels__panel_3SabF:nth-child(3)")).click();
        driver.findElement(By.name("fio")).click();
        driver.findElement(By.name("fio")).clear();
        driver.findElement(By.name("fio")).sendKeys("Иванов");
        driver.findElement(By.cssSelector("#form > div")).click();
        driver.findElement(By.cssSelector("div.Error__errorMessage_q8BBY")).click();
        assertEquals("Недостаточно информации. Введите фамилию, имя и отчество через пробел (Например: Иванов Иван Алексеевич)", driver.findElement(By.cssSelector("div.Error__errorMessage_q8BBY")).getText());
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("testing@test.");
        driver.findElement(By.cssSelector("#form > div")).click();
        driver.findElement(By.cssSelector(".Row__row_AjrJL:nth-child(2) .Error__errorMessage_q8BBY")).click();
        assertEquals("Введите корректный адрес эл. почты", driver.findElement(By.cssSelector(".Row__row_AjrJL:nth-child(2) .Error__errorMessage_q8BBY")).getText());
        driver.findElement(By.name("phone")).click();
        driver.findElement(By.name("phone")).clear();
        driver.findElement(By.name("phone")).sendKeys("+7 (000) 000-00-00");
        driver.findElement(By.cssSelector(".FormField__field_1iwkM:nth-child(2) > .Error__errorMessage_q8BBY")).click();
        assertEquals("Код города/оператора должен начинаться с цифры 3, 4, 5, 6, 8, 9", driver.findElement(By.cssSelector(".FormField__field_1iwkM:nth-child(2) > .Error__errorMessage_q8BBY")).getText());
        driver.findElement(By.name("city")).click();
        driver.findElement(By.name("city")).clear();
        driver.findElement(By.name("city")).sendKeys("Москва-1");
        driver.findElement(By.cssSelector("#form > div")).click();
        driver.findElement(By.cssSelector(".Row__row_AjrJL:nth-child(3) .Error__errorMessage_q8BBY")).click();
        assertEquals("Допустимо использовать только буквы русского, латинского алфавита и дефис", driver.findElement(By.cssSelector(".Row__row_AjrJL:nth-child(3) .Error__errorMessage_q8BBY")).getText());
        driver.findElement(By.cssSelector("#form > div")).click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
