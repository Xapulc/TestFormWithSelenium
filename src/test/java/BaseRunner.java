import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BaseRunner {
    String browserName = (System.getProperty("browser") == null)
            ? "chrome" : System.getProperty("browser");
    Application app = new Application(browserName);

    @Before
    public void setUp() {}

    @After
    public void tearDown() {
        app.quit();
    }
}
