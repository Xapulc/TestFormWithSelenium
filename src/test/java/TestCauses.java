import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestCauses extends BaseRunner {
    @Test
    public void TestClickWithFill() {
        app.getPage("https://moscow-job.tinkoff.ru/");
        String fio = "//*[@name='fio']";
        String email = "//*[@name='email']";
        String phone = "//*[@name='phone']";
        String city = "//*[@name='city']";

        app.click(fio);
        app.click(email);
        app.click(phone);
        app.click(city);

        app.sendKeys(fio, "Иванов");
        app.sendKeys(email, "testing@test.");
        app.sendKeys(phone, "+7 (000) 000-00-00");
        app.sendKeys(city, "Москва-1");

        clickFreeSpace();

        assertEquals("Недостаточно информации. Введите фамилию, имя и отчество через пробел (Например: Иванов Иван Алексеевич)",
                app.getText("//form//div[@data-qa='fio']/../following-sibling::"
                        + "div[@class='Error__errorMessage_q8BBY']"));
        assertEquals("Введите корректный адрес эл. почты",
                app.getText("//form//div[@data-qa='email']/following-sibling::"
                        + "div[@class='Error__errorMessage_q8BBY']"));
        assertEquals("Код города/оператора должен начинаться с цифры 3, 4, 5, 6, 8, 9",
                app.getText("//form//*[@name='phone']/../../../following-sibling::"
                        + "div[@class='Error__errorMessage_q8BBY']"));
        assertEquals("Допустимо использовать только буквы русского, латинского алфавита и дефис",
                app.getText("//form//*[@name='city']/../../../following-sibling::"
                        + "div[@class='Error__errorMessage_q8BBY']"));
    }

    @Test
    public void TestClickWithoutFill() {
        app.getPage("https://moscow-job.tinkoff.ru/");
        String fio = "//*[@name='fio']";
        String email = "//*[@name='email']";
        String phone = "//*[@name='phone']";
        String city = "//*[@name='city']";

        app.click(fio);
        app.click(email);
        app.click(phone);
        app.click(city);

        clickFreeSpace();

        assertEquals("Поле обязательное", app.getText(
                "//form//div[@data-qa='fio']/../following-sibling::"
                        + "div[@class='Error__errorMessage_q8BBY']"));
        assertEquals("Поле обязательное", app.getText(
                "//form//div[@data-qa='email']/following-sibling::"
                        + "div[@class='Error__errorMessage_q8BBY']"));
        assertEquals("Необходимо указать номер телефона", app.getText(
                "//form//*[@name='phone']/../../../following-sibling::"
                        + "div[@class='Error__errorMessage_q8BBY']"));
        assertEquals("Поле обязательное", app.getText(
                "//form//*[@name='city']/../../../following-sibling::"
                        + "div[@class='Error__errorMessage_q8BBY']"));
    }

    private void clickFreeSpace() {
        app.click("(.//*[normalize-space(text()) and normalize-space(.)='Пройдите обучение'])[1]/following::article[1]");
    }
}
