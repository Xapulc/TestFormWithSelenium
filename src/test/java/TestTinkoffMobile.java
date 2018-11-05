import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

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

        assertEquals("https://www.tinkoff.ru/mobile-operator/tariffs/",
                driver.getCurrentUrl());

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
                        "//*[contains(@class,'_region_') and @data-qa-file='MobileOperatorRegionsPopup' and text()='Москва']"));
                region.click();
            }
            return true;
        });

        Assert.assertTrue(checkRegion("Москва"));

        driver.navigate().refresh();
        Assert.assertTrue(checkRegion("Москва"));

        String moscowSale = getAmountSale();
        changeRegion("Краснодар");
        Assert.assertTrue(checkRegion("Краснодар"));
        String krasnodarSale = getAmountSale();

        Assert.assertTrue(!krasnodarSale.equals(moscowSale));

        chooseMaxFields();
        krasnodarSale = getAmountSale();
        changeRegion("Москва");
        chooseMaxFields();
        moscowSale = getAmountSale();

        assertEquals(krasnodarSale, moscowSale);
    }

    @Test
    public void DisableButton() {
        String url = "https://www.tinkoff.ru/mobile-operator/tariffs/";
        driver.get(url);

        chooseMinFields();
        Assert.assertTrue(getAmountSale().contains("0 \u20BD"));
        Assert.assertTrue(Button.isDisable());
    }

    private void chooseMaxFields() {
        Select.driver = driver;
        CheckBox.driver = driver;

        Select calls = new Select("Звонки", "Безлимит");
        Select internet = new Select("Интернет", "Безлимит");

        calls.choose();
        internet.choose();

        CheckBox sms = new CheckBox("SMS");
        CheckBox modem = new CheckBox("модем");

        sms.setActive(true);
        modem.setActive(true);
    }

    private void chooseMinFields() {
        Select.driver = driver;
        CheckBox.driver = driver;
        Button.driver = driver;

        Select calls = new Select("Звонки", "0 минут");
        Select internet = new Select("Интернет", "0 ГБ");

        calls.choose();
        internet.choose();

        CheckBox messenger = new CheckBox("Мессенджеры");
        CheckBox socialNetwork = new CheckBox("Социальные сети");
        CheckBox music = new CheckBox("Музыка");
        CheckBox video = new CheckBox("Видео");
        CheckBox sms = new CheckBox("SMS");

        messenger.setActive(false);
        socialNetwork.setActive(false);
        music.setActive(false);
        video.setActive(false);
        sms.setActive(false);
    }

    private String getAmountSale() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return driver.findElement(By.xpath(
                "//*[@data-qa-file='FormFieldWrapper']/*[contains(@class,'amountTitle')]")).getText();
    }

    private boolean checkRegion(String city) {
        WebElement region = driver.findElement(By.xpath(
                "//div[contains(@class,'title') and @data-qa-file='MvnoRegionConfirmation']"));
        return region.getText().contains(city);
    }

    private void changeRegion(String city) {
        WebElement region = driver.findElement(By.xpath(
                "//div[contains(@class,'title') and @data-qa-file='MvnoRegionConfirmation']"));
        region.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(d -> {
            WebElement regionLambda;
            regionLambda = driver.findElement(By.xpath(
                    "//*[contains(@class,'_region_') and @data-qa-file='MobileOperatorRegionsPopup'"
                            + " and text()='" + city + "']"));
            regionLambda.click();
            return true;
        });
    }

    /*@Test
    public void download() {
        String url = "https://www.tinkoff.ru/mobile-operator/documents/";
        driver.get(url);

        WebElement elem = driver.findElement(By.xpath(
                "//*[contains(text(),'Удвоим минуты и гигабайты')]"));
        elem.click();

        File file = new File("buf.pdf");
        try {
            FileUtils.copyURLToFile(new URL("blob:chrome-extension://mhjfbmdgcfjbbpaeojofohoefgiehjai/4d34808f-595f-4a6f-8ab5-4eaf84920d11"), file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(file.exists());
    }*/

    @After
    public void tearDown() {
        driver.quit();
    }
}
