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

        wait.until(d->{
            WebElement searchTariff = driver.findElement(By.xpath
                    ("//form[@name='f']//*[contains(text(),'тарифы')]/.."));
            searchTariff.click();
            return true;
        });

        String initTitle = driver.getWindowHandle();

        search = driver.findElement(By.xpath
                ("//*[contains(@href,'tinkoff')]//*[text()='Тарифы Тинькофф Мобайл']"));
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

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
                        "//*[contains(@class,'_region_') and @data-qa-file='MobileOperatorRegionsPopup' and text()='Москва']"));
                region.click();
            }
            return true;
        });

        checkRegion("Москва");

        driver.navigate().refresh();

        System.out.println("Страница обновлена");
        checkRegion("Москва");

        String moscowSale = getAmountSale();
        changeRegion("Краснодар");
        String krasnodarSale = getAmountSale();

        System.out.println("Дефолтные параметры");
        compareSales(krasnodarSale, moscowSale);

        chooseMaxFields();
        krasnodarSale = getAmountSale();
        changeRegion("Москва");
        chooseMaxFields();
        moscowSale = getAmountSale();

        System.out.println("Максимальные параметры");
        compareSales(krasnodarSale, moscowSale);
    }

    private void chooseMaxFields() {
        chooseUnlimited("calls");
        chooseUnlimited("internet");

        WebElement field = driver.findElement(By.xpath(
                "//*[contains(@class,'checkbox-directive')]/*[@for='2048']"));
        field.click();
    }

    private void chooseUnlimited(String service) {
        String addService = "//*[@name='" + service + "']/../..";
        WebElement field = driver.findElement(By.xpath(addService
                + "//*[contains(@class,'title-flex-text') and @data-qa-file='UISelectTitle']"));
        field.click();

        field = driver.findElement(By.xpath(addService
                + "//*[contains(@class,'dropdown') and contains(text(),'Безлимит')]"));
        field.click();
    }

    private String getAmountSale() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return driver.findElement(By.xpath(
                "//*[@data-qa-file='FormFieldWrapper']/*[contains(@class,'amountTitle')]")).getText();
    }

    private void compareSales(String sale1, String sale2) {
        if (sale1.equals(sale2))
            System.out.println("Цены одинаковые");
        else
            System.out.println("Цены разные");
    }

    private void checkRegion(String city) {
        WebElement region = driver.findElement(By.xpath(
                "//div[contains(@class,'title') and @data-qa-file='MvnoRegionConfirmation']"));
        if (region.getText().contains(city))
            System.out.println("Правильный регион");
        else
            System.out.println("Неправильный регион");
    }

    private void changeRegion(String city) {
        WebElement region = driver.findElement(By.xpath(
                "//div[contains(@class,'title') and @data-qa-file='MvnoRegionConfirmation']"));
        region.click();

        region = driver.findElement(By.xpath(
                "//*[contains(@class,'_region_') and @data-qa-file='MobileOperatorRegionsPopup'"
                        + " and text()='" + city + "']"));
        region.click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
