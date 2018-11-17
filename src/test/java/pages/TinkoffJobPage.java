package pages;

public class TinkoffJobPage extends Page{
    TinkoffJobPage() {
        super();
    }

    public TinkoffJobPage(app.Application app) {
        super(app);
    }

    public void open() {
        getPage("https://moscow-job.tinkoff.ru/");
    }

    public void clickField(String field) {
        click("//*[@name='" + field + "']");
        logger.info("Поле " + field + " прокликано");
    }

    public void fillField(String field, String value) {
        sendKeys("//*[@name='" + field + "']", value);
        logger.info("У поля " + field
                + " установлено значение " + value);
    }

    public void clickFreeSpace() {
        click("(.//*[normalize-space(text()) and normalize-space(.)='Пройдите обучение'])[1]/following::article[1]");
        logger.info("Прокликано свободное прстранство");
    }

    public String getErrorText(String field) {
        String errorXpath;
        if (field.equals("fio")) {
            errorXpath = "//form//*[@name='" + field
                    + "']/../../../../..//following-sibling::div[contains(@class,'errorMessage')]";
        } else {
            errorXpath = "//form//*[@name='" + field
                    + "']/../../../..//following-sibling::div[contains(@class,'errorMessage')]";
        }

        String errorText = getText(errorXpath);
        logger.info("Получено сообщение об ошибке");
        return errorText;
    }
}
