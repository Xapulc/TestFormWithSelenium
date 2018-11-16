package pages;

import app.Application;

public class Page extends Application {
    protected Page() {
        super();
    }

    protected Page(app.Application app) {
        super(app);
    }

    public void getPage(String url) {
        driver.get(url);
    }

    public void update() {
        driver.navigate().refresh();
    }

    public void checkWindowHandle(String handle) {
        wait.until(d -> {
            boolean check = false;
            for (String title : driver.getWindowHandles()) {
                driver.switchTo().window(title);
                check = d.getTitle().equals(handle);
            }
            return check;
        });
    }

    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    public void switchToWindow(String title) {
        driver.switchTo().window(title);
    }

    public void close() {
        driver.close();
    }
}
