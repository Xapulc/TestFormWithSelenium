import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Application {
    private WebDriver driver;
    private WebDriverWait wait;
    private String browserName = "chrome";

    private void defaultConstruct() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    Application() {
        System.out.println("Start default constr");
        driver = getDriver(browserName);
        defaultConstruct();
    }

    Application(String browserName) {
        System.out.println("Start browser constr");
        this.browserName = browserName;
        driver = getDriver(browserName);
        defaultConstruct();
    }

    private WebDriver getDriver(String browserName) {
        try {
            BrowserFactory.valueOf(browserName);
        } catch (NullPointerException | IllegalArgumentException e) {
            System.setProperty("browser", browserName);
        }
        return BrowserFactory.valueOf(browserName).create();
    }

    public void quit() {
        driver.quit();
    }

    public void getPage(String url) {
        driver.get(url);
    }

    public WebElement findElement(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public void sendKeys(String xpath, String value) {
        WebElement elem = driver.findElement(By.xpath(xpath));
        elem.sendKeys(value);
    }

    private void clickHelper(String xpath) {
        WebElement searchTariff = driver.findElement(By.xpath(xpath));
        searchTariff.click();
    }

    public void click(String xpath) {
        click(xpath, false);
    }

    public void click(String xpath, boolean isWaiting) {
        if (isWaiting) {
            wait.until(d->{
                clickHelper(xpath);
                return true;
            });
        } else {
            clickHelper(xpath);
        }
    }

    public void checkWindowHandle(String handle) {
        wait.until(d -> {
            boolean check = false;
            for (String title : driver.getWindowHandles()) {
                driver.switchTo().window(title);
                check = d.getTitle().equals("Тарифы Тинькофф Мобайл");
            }
            return check;
        });
    }

    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void switchToWindow(String title) {
        driver.switchTo().window(title);
    }

    public void close() {
        driver.close();
    }

    public void chooseMoscow() {
        wait.until(d -> {
            WebElement region = driver.findElement(By.xpath(
                    "//span[contains(@class,'regionName') and @data-qa-file='MvnoRegionConfirmation']"));
            if (region.getText().contains("Москва")) {
                region = driver.findElement(By.xpath(
                        "//span[contains(@class,'optionAgreement') and @data-qa-file='MvnoRegionConfirmation']"));
                region.click();
            } else {
                region = driver.findElement(By.xpath(
                        "//span[contains(@class,'optionRejection') and @data-qa-file='MvnoRegionConfirmation']"));
                region.click();

                region = driver.findElement(By.xpath(
                        "//*[contains(@class,'_region_') and @data-qa-file='MobileOperatorRegionsPopup' and text()='Москва']"));
                region.click();
            }
            return true;
        });
    }

    public void update() {
        driver.navigate().refresh();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getText(String xpath) {
        return driver.findElement(By.xpath(xpath)).getText();
    }
}
