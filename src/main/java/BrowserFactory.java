import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

enum  BrowserFactory {
    chrome {
        public WebDriver create() {
            return new ChromeDriver();
        }
    },
    firefox {
        public WebDriver create() {
            return new FirefoxDriver();
        }
    },
    opera {
        public WebDriver create() {
            return new OperaDriver();
        }
    },;

    public WebDriver create() {
        return null;
    }
}