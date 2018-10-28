import org.junit.Test;
import org.openqa.selenium.*;
import static org.junit.Assert.assertEquals;

public class TestCauses extends BaseRunner {
    @Test
    public void TestClickWithFill() {
        driver.get(baseUrl);
        driver.findElement(By.name("fio")).click();
        driver.findElement(By.name("fio")).clear();
        driver.findElement(By.name("fio"))
                .sendKeys("Иванов");
        //clickFreeSpace();

        //driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email"))
                .sendKeys("testing@test.");
        //clickFreeSpace();

        driver.findElement(By.name("phone")).click();
        driver.findElement(By.name("phone")).clear();
        driver.findElement(By.name("phone"))
                .sendKeys("+7 (000) 000-00-00");
        //clickFreeSpace();

        driver.findElement(By.name("city")).click();
        driver.findElement(By.name("city")).clear();
        driver.findElement(By.name("city"))
                .sendKeys("Москва-1");
        clickFreeSpace();

        assertEquals("Недостаточно информации. Введите фамилию, имя и отчество через пробел (Например: Иванов Иван Алексеевич)",
                driver.findElement(By.cssSelector("div.Error__errorMessage_q8BBY")).getText());
        assertEquals("Введите корректный адрес эл. почты",
                driver.findElement(By.cssSelector(".Row__row_AjrJL:nth-child(2) .Error__errorMessage_q8BBY")).getText());
        assertEquals("Код города/оператора должен начинаться с цифры 3, 4, 5, 6, 8, 9",
                driver.findElement(By.cssSelector(".FormField__field_1iwkM:nth-child(2) > .Error__errorMessage_q8BBY")).getText());
        assertEquals("Допустимо использовать только буквы русского, латинского алфавита и дефис",
                driver.findElement(By.cssSelector(".Row__row_AjrJL:nth-child(3) .Error__errorMessage_q8BBY")).getText());
    }

    @Test
    public void TestClickWithoutFill() {
        driver.get(baseUrl);
        driver.findElement(By.name("fio")).click();
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("phone")).click();
        driver.findElement(By.name("city")).click();
        clickFreeSpace();

        assertEquals("Поле обязательное", driver.findElement(By.cssSelector(
                "div.Error__errorMessage_q8BBY")).getText());
        assertEquals("Поле обязательное", driver.findElement(By.cssSelector(
                "div.Error__errorMessage_q8BBY")).getText());
        assertEquals("Поле обязательное", driver.findElement(By.cssSelector(
                ".Row__row_AjrJL:nth-child(2) .Error__errorMessage_q8BBY")).getText());
        assertEquals("Поле обязательное", driver.findElement(By.cssSelector(
                ".Row__row_AjrJL:nth-child(3) .Error__errorMessage_q8BBY")).getText());
    }

    private void clickFreeSpace() {
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Пройдите обучение'])[1]/following::article[1]")).click();
    }
}
