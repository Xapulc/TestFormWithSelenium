import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TextInput {
    public static WebDriver driver = null;

    TextInput() {}

    public static void setPhone(String phone) {
        WebElement elem = driver.findElement(By.xpath("//input[@name='phone_mobile']"));
        elem.sendKeys(phone);
    }

    public static String getPhone() {
        WebElement elem = driver.findElement(By.xpath("//input[@name='phone_mobile']"));
        return elem.getAttribute("value");
    }

    public static void setFIO(String phone) {
        WebElement elem = driver.findElement(By.xpath("//input[@name='fio']"));
        elem.sendKeys(phone);
    }

    public static String getFIO() {
        WebElement elem = driver.findElement(By.xpath("//input[@name='fio']"));
        return elem.getAttribute("value");
    }
}
