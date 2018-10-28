import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

public enum  BrowserFactory {
    chrome {
        public WebDriver create() {
            updateProperty("chrome");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            return new ChromeDriver(options);
        }
    },
    firefox {
        public WebDriver create() {
            updateProperty("firefox");
            System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "./src/test/java/log");

            FirefoxOptions options = new FirefoxOptions();
            options.addPreference("dom.webnotifications.enabled", false);
            return new FirefoxDriver(options);
        }
    },
    opera {
        public WebDriver create() {
            updateProperty("opera");
            OperaOptions options = new OperaOptions();
            options.setBinary(
                    "C:\\Users\\Виктор\\AppData\\Local\\Programs\\Opera\\56.0.3051.52\\opera.exe");
            options.addArguments("--disable-notifications");
            return new OperaDriver(options);
        }
    };

    public WebDriver create() {
        return null;
    }

    void updateProperty(String browserName) {
        System.out.println(String.format("\nstarting %s-browser......", browserName));
        if (System.getProperty("browser") == null) System.setProperty("browser", browserName);
    }
}