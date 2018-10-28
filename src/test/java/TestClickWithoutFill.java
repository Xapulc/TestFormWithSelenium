import org.junit.Test;
import org.openqa.selenium.*;
import static org.junit.Assert.assertEquals;

public class TestClickWithoutFill extends BaseRunner {
    @Test
    public void TestClickabgasWithoutFill() {
        driver.get(baseUrl + '/');
        driver.findElement(By.name("fio")).click();
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("phone")).click();
        driver.findElement(By.name("city")).click();
        driver.findElement(By.name("fio")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Пройдите обучение'])[1]/following::article[1]")).click();

        assertEquals("Поле обязательное", driver.findElement(By.cssSelector(
                "div.Error__errorMessage_q8BBY")).getText());
        assertEquals("Поле обязательное", driver.findElement(By.cssSelector(
                "div.Error__errorMessage_q8BBY")).getText());
        assertEquals("Поле обязательное", driver.findElement(By.cssSelector(
                ".Row__row_AjrJL:nth-child(2) .Error__errorMessage_q8BBY")).getText());
        assertEquals("Поле обязательное", driver.findElement(By.cssSelector(
                ".Row__row_AjrJL:nth-child(3) .Error__errorMessage_q8BBY")).getText());
    }
}
