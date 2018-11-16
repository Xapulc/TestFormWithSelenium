package pages.tinkoffTariffPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class CheckBox {
    public static WebDriver driver = null;
    private String service;
    private WebElement field;

    CheckBox() {
        field = null;
    }

    CheckBox(String service) {
        field = driver.findElement(By.xpath(
                "//*[contains(@class,'checkbox-directive')]"
                        + "//*[contains(text(),'" + service + "')]"));
        this.service = service;
    }

    public void setActive(boolean mode) {
        if (mode != getValue()) {

            field.click();
        }
    }

    public String getText() {
        return field.getText();
    }

    public boolean getValue() {
        boolean value;
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        value = !driver.findElements(By.xpath(
                "//*[contains(@class,'checkbox-directive')]"
                        + "//*[contains(@class,'checked')]"
                        + "//*[contains(text(),'" + service + "')]")).isEmpty();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return value;
    }
}
