import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestTinkoffMobile {
    private WebDriver driver;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        //options.addArguments("--incognito");
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void SwapBetweenPages() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        String url = "https://www.google.ru/";
        driver.get(url);

        WebElement search = driver.findElement(By.xpath
                ("//form[@role='search']//input[@name='q']"));
        search.sendKeys("мобайл тинькофф");

        search = driver.findElement(By.xpath
                ("//form[@name='f']//div[@class='sbqs_c']"));
        search.click();

        String initTitle = null;
        for (String title : driver.getWindowHandles())
            initTitle = title;

        search = driver.findElement(By.xpath
                ("//a[@href='https://www.tinkoff.ru/mobile-operator/tariffs/']/h3"));
        search.click();

        wait.until(d -> {
            boolean check = false;
            for (String title : driver.getWindowHandles()) {
                driver.switchTo().window(title);
                check = d.getTitle().equals("Тарифы Тинькофф Мобайл");
            }
            return check;
        });

        driver.switchTo().window(initTitle);
        driver.close();
    }

    @Test
    public void ChangeRegion() {
        String url = "https://www.tinkoff.ru/mobile-operator/tariffs/";
        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, 10);

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
                        "//*[contains(@class,'_region_') and @data-qa-file='MobileOperatorRegionsPopup' and @text()='Москва']"));
                region.click();
            }
            return true;
        });

        WebElement region = driver.findElement(By.xpath(
                "//div[contains(@class,'title') and @data-qa-file='MvnoRegionConfirmation']"));
        if (region.getText().contains("Москва"))
            System.out.println("Правильный регион");

        driver.navigate().refresh();

        region = driver.findElement(By.xpath(
                "//div[contains(@class,'title') and @data-qa-file='MvnoRegionConfirmation']"));
        if (region.getText().contains("Москва"))
            System.out.println("Регион сохранился после обновлаения страницы");


    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
