package app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Application {
    protected Logger logger;

    protected WebDriver driver;
    protected WebDriverWait wait;
    private String browserName = "chrome";

    private void defaultConstruct() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        logger = LoggerFactory.getLogger(Application.class);
    }

    protected Application() {
        driver = getDriver(browserName);
        defaultConstruct();
    }

    protected Application(String browserName) {
        this.browserName = browserName;
        driver = getDriver(browserName);
        defaultConstruct();
    }

    protected Application(Application app) {
        app.copy(this);
    }

    private void copy(Application app) {
        app.browserName = browserName;
        app.driver = driver;
        app.wait = wait;
        app.logger = logger;
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
        logger.info("Браузер заркыт");
    }

    public void getPage(String url) {
        driver.get(url);
    }

    public WebElement findElement(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    protected void sendKeys(String xpath, String value) {
        WebElement elem = driver.findElement(By.xpath(xpath));
        elem.sendKeys(value);
    }

    private void clickHelper(String xpath) {
        WebElement searchTariff = driver.findElement(By.xpath(xpath));
        searchTariff.click();
    }

    protected void click(String xpath) {
        click(xpath, false);
    }

    protected void click(String xpath, boolean isWaiting) {
        if (isWaiting) {
            wait.until(d->{
                clickHelper(xpath);
                return true;
            });
        } else {
            clickHelper(xpath);
        }
    }

    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void switchToWindow(String title) {
        driver.switchTo().window(title);
        logger.info("Активная вкладка изменена");
    }

    public void close() {
        driver.close();
        logger.info("Активная вкладка закрыта");
    }

    public void update() {
        driver.navigate().refresh();
        logger.info("Страница обновлена");
    }

    public WebDriver getDriver() {
        return driver;
    }

    protected String getText(String xpath) {
        return driver.findElement(By.xpath(xpath)).getText();
    }

    protected boolean isExistElement(String xpath) {
        return !driver.findElements(By.xpath(xpath)).isEmpty();
    }

    protected void setTimeout(int sec) {
        driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
    }
}
