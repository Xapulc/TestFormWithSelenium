package pages.tinkoffTariffPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Button {
    public static WebDriver driver = null;

    Button() {}

    public static boolean isDisable() {
        return !driver.findElements(By.xpath("//button[@disabled]")).isEmpty();
    }
}
