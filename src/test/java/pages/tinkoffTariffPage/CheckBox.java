package pages.tinkoffTariffPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CheckBox extends TinkoffTariffPage {
    private String service;
    private WebElement field;

    CheckBox() {
        super();
    }

    public CheckBox(app.Application app) {
        super(app);
    }

    CheckBox(String service, app.Application app) {
        super(app);
        field = driver.findElement(By.xpath(
                "//*[contains(@class,'checkbox-directive')]"
                        + "//*[contains(text(),'" + service + "')]"));
        this.service = service;
    }

    public void setActive(boolean mode) {
        if (mode != getValue()) {
            field.click();
        }

        logger.info("У сервиса " + service
                + " установлен режим " + mode);
    }

    private boolean getValue() {
        boolean value;
        setTimeout(3);
        value = isExistElement("//*[contains(@class,'checkbox-directive')]"
                + "//*[contains(@class,'checked')]"
                + "//*[contains(text(),'" + service + "')]");

        setTimeout(30);
        return value;
    }
}
