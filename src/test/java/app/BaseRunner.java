package app;

import org.junit.After;
import org.junit.Before;

public class BaseRunner {
    protected String browserName = (System.getProperty("browser") == null)
            ? "chrome" : System.getProperty("browser");
    protected app.Application app = new app.Application(browserName);

    @Before
    public void setUp() {}

    @After
    public void tearDown() {
        app.quit();
    }
}
