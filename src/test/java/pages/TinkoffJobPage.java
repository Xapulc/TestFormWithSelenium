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
    }

    public void fillField(String field, String value) {
        sendKeys("//*[@name='" + field + "']", value);
    }

    public void clickFreeSpace() {
        click("(.//*[normalize-space(text()) and normalize-space(.)='Пройдите обучение'])[1]/following::article[1]");
    }

//    Требудет фикса, не работает
    public String getErrorText(String field) {
        return getText("//form//*[@name='" + field + "']/../../..//following-sibling::"
                + "div[@class='Error__errorMessage_q8BBY']");
    }
}
