import org.junit.Test;
import org.openqa.selenium.*;
import static org.junit.Assert.assertEquals;

public class TestCauses extends BaseRunner {
    @Test
    public void TestClickWithFill() {
        driver.get(baseUrl);
        WebElement fio = driver.findElement(By.cssSelector("[name='fio']"));
        WebElement email = driver.findElement(By.cssSelector("[name='email']"));
        WebElement phone = driver.findElement(By.cssSelector("[name='phone']"));
        WebElement city = driver.findElement(By.cssSelector("[name='city']"));

        fio.click();
        fio.clear();
        fio.sendKeys("Иванов");

        email.clear();
        email.sendKeys("testing@test.");

        phone.click();
        phone.clear();
        phone.sendKeys("+7 (000) 000-00-00");

        city.click();
        city.clear();
        city.sendKeys("Москва-1");
        clickFreeSpace();

        assertEquals("Недостаточно информации. Введите фамилию, имя и отчество через пробел (Например: Иванов Иван Алексеевич)",
                driver.findElement(By.cssSelector(
                        "#form div ~ div.Error__errorMessage_q8BBY")).getText());
        assertEquals("Введите корректный адрес эл. почты",
                driver.findElement(By.cssSelector("#form div.Row__row_AjrJL:nth-child(2) div.Error__errorMessage_q8BBY")).getText());
        assertEquals("Код города/оператора должен начинаться с цифры 3, 4, 5, 6, 8, 9",
                driver.findElement(By.cssSelector("#form div.Row__row_AjrJL:nth-child(2) div[class*=FormField]:nth-child(2) div[class*=Error]")).getText());
        assertEquals("Допустимо использовать только буквы русского, латинского алфавита и дефис",
                driver.findElement(By.cssSelector("#form div.Row__row_AjrJL:nth-child(3) div.Error__errorMessage_q8BBY")).getText());
    }

    @Test
    public void TestClickWithoutFill() {
        driver.get(baseUrl);
        WebElement fio = driver.findElement(By.xpath("//form//*[@name='fio']"));
        WebElement email = driver.findElement(By.xpath("//form//*[@name='email']"));
        WebElement phone = driver.findElement(By.xpath("//form//*[@name='phone']"));
        WebElement city = driver.findElement(By.xpath("//form//*[@name='city']"));

        fio.click();
        email.click();
        phone.click();
        city.click();

        clickFreeSpace();

        assertEquals("Поле обязательное", driver.findElement(By.xpath(
                "//form//div[@data-qa='fio']/../following-sibling::"
                        + "div[@class='Error__errorMessage_q8BBY']")).getText());
        assertEquals("Поле обязательное", driver.findElement(By.xpath(
                "//form//div[@data-qa='email']/following-sibling::"
                        + "div[@class='Error__errorMessage_q8BBY']")).getText());
        assertEquals("Необходимо указать номер телефона", driver.findElement(By.xpath(
                "//form//*[@name='phone']/../../../following-sibling::"
                        + "div[@class='Error__errorMessage_q8BBY']")).getText());
        assertEquals("Поле обязательное", driver.findElement(By.xpath(
                "//form//*[@name='city']/../../../following-sibling::"
                        + "div[@class='Error__errorMessage_q8BBY']")).getText());
    }

    private void clickFreeSpace() {
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Пройдите обучение'])[1]/following::article[1]")).click();
    }
}
