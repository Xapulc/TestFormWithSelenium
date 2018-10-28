import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BaseRunner {
    WebDriver driver;
    String baseUrl;
    String browserName = (System.getProperty("browser") == null)
            ? "opera" : System.getProperty("browser");

    @Before
    public void setUp() {
        driver = getDriver();
        driver.manage().window().maximize();
        baseUrl = "https://moscow-job.tinkoff.ru/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }


    @After
    public void tearDown() {
        driver.quit();
    }

    private WebDriver getDriver() {
        try {
            BrowserFactory.valueOf(browserName);
        } catch (NullPointerException | IllegalArgumentException e) {
            System.setProperty("browser", browserName);
        }
        return BrowserFactory.valueOf(browserName).create();
    }
}
