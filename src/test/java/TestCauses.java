import app.BaseRunner;
import org.junit.Test;
import pages.TinkoffJobPage;

import static org.junit.Assert.assertEquals;

public class TestCauses extends BaseRunner {
    @Test
    public void TestClickWithFill() {
        TinkoffJobPage tinkoffJobPage = new TinkoffJobPage(app);

        tinkoffJobPage.open();

        tinkoffJobPage.clickField("fio");
        tinkoffJobPage.clickField("email");
        tinkoffJobPage.clickField("phone");
        tinkoffJobPage.clickField("city");

        tinkoffJobPage.fillField("fio", "Иванов");
        tinkoffJobPage.fillField("email", "testing@test.");
        tinkoffJobPage.fillField("phone", "+7 (000) 000-00-00");
        tinkoffJobPage.fillField("city", "Москва-1");

        tinkoffJobPage.clickFreeSpace();

        assertEquals("Недостаточно информации. Введите фамилию, имя и отчество через пробел "
                        + "(Например: Иванов Иван Алексеевич)",
                            tinkoffJobPage.getErrorText("fio"));
        assertEquals("Введите корректный адрес эл. почты",
                tinkoffJobPage.getErrorText("email"));
        assertEquals("Код города/оператора должен начинаться с цифры 3, 4, 5, 6, 8, 9",
                tinkoffJobPage.getErrorText("phone"));
        assertEquals("Допустимо использовать только буквы русского, латинского алфавита и дефис",
                tinkoffJobPage.getErrorText("city"));
    }

    @Test
    public void TestClickWithoutFill() {
        TinkoffJobPage tinkoffJobPage = new TinkoffJobPage(app);

        tinkoffJobPage.open();

        tinkoffJobPage.clickField("fio");
        tinkoffJobPage.clickField("email");
        tinkoffJobPage.clickField("phone");
        tinkoffJobPage.clickField("city");

        tinkoffJobPage.clickFreeSpace();

        assertEquals("Поле обязательное",
                tinkoffJobPage.getErrorText("fio"));
        assertEquals("Поле обязательное",
                tinkoffJobPage.getErrorText("email"));
        assertEquals("Необходимо указать номер телефона",
                tinkoffJobPage.getErrorText("phone"));
        assertEquals("Поле обязательное",
                tinkoffJobPage.getErrorText("city"));
    }
}
