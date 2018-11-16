import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestTinkoffMobile extends BaseRunner {

    @Test
    public void SwapBetweenPages() {
        app.getPage("https://www.google.ru/");
        app.sendKeys("//form[@role='search']//input[@name='q']", "мобайл тинькофф");
        app.click("//form[@name='f']//*[contains(text(),'тарифы')]/..", true);

        String initTitle = app.getWindowHandle();
        app.click("//*[contains(@href,'tinkoff')]//*[text()='Тарифы Тинькофф Мобайл']");
        app.checkWindowHandle("Тарифы Тинькофф Мобайл");

        assertEquals("https://www.tinkoff.ru/mobile-operator/tariffs/",
                app.getCurrentUrl());

        app.switchToWindow(initTitle);
        app.close();
    }

    @Test
    public void ChangeRegion() {
        app.getPage("https://www.tinkoff.ru/mobile-operator/tariffs/");

        app.chooseMoscow();

        Assert.assertTrue(checkRegion("Москва"));
        app.update();
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
        app.getPage("https://www.tinkoff.ru/mobile-operator/tariffs/");

        chooseMinFields();
        char ruble = '\u20BD';
        Assert.assertTrue(getAmountSale().contains("0 " + ruble));
        Assert.assertTrue(Button.isDisable());
    }

    private void chooseMaxFields() {
        Select.driver = app.getDriver();
        CheckBox.driver = app.getDriver();

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
        Select.driver = app.getDriver();
        CheckBox.driver = app.getDriver();
        Button.driver = app.getDriver();

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

        return app.getText("//*[@data-qa-file='FormFieldWrapper']/*[contains(@class,'amountTitle')]");
    }

    private boolean checkRegion(String city) {
        String region = app.getText("//div[contains(@class,'title') and @data-qa-file='MvnoRegionConfirmation']");
        return region.contains(city);
    }

    private void changeRegion(String city) {
        app.click("//div[contains(@class,'title') and @data-qa-file='MvnoRegionConfirmation']");
        app.click("//*[contains(@class,'_region_') and @data-qa-file='MobileOperatorRegionsPopup']"
                + "/*[contains(text(),'" + city + "')]", true);
    }

    @After
    public void tearDown() {
        app.quit();
    }
}
