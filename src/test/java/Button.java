import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

class Button {
    public static WebDriver driver = null;

    Button() {}

    static boolean isDisable() {
        return !driver.findElements(By.xpath("//button[@disabled]")).isEmpty();
    }
}
