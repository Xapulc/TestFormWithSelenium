package pages.tinkoffTariffPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Select {
    public static WebDriver driver = null;
    private String service;
    private String mode;

    Select() {}

    Select(String service, String mode) {
        this.service = service;
        this.mode = mode;

        if (service.equals("Интернет"))
            this.service = "internet";
        else if (service.equals("Звонки"))
            this.service = "calls";
    }

    public void choose() {
        String addService = "//*[@name='" + service + "']/../..";
        WebElement field = driver.findElement(By.xpath(addService
                + "//*[contains(@class,'title-flex-text') and @data-qa-file='UISelectTitle']"));

        field.click();

        field = driver.findElement(By.xpath(addService
                + "//*[contains(@class,'dropdown') and contains(text(),'" + mode + "')]"));
        field.click();
    }

    public String getMode() {
        return mode;
    }


}
