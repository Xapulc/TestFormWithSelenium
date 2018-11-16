package pages.tinkoffTariffPage;

import pages.Page;

public class TinkoffTariffPage extends Page {
    TinkoffTariffPage() {
        super();
    }

    public TinkoffTariffPage(app.Application app) {
        super(app);
    }

    public void open() {
        getPage("https://www.tinkoff.ru/mobile-operator/tariffs/");
    }

    public void chooseRegion(String city) {
        wait.until(d -> {
            if (getText("//span[contains(@class,'regionName') and @data-qa-file='MvnoRegionConfirmation']")
                    .contains("Москва")) {
                click("//span[contains(@class,'optionAgreement') and @data-qa-file='MvnoRegionConfirmation']");
            } else {
                click("//span[contains(@class,'optionRejection') and @data-qa-file='MvnoRegionConfirmation']");
                click("//*[contains(@class,'_region_') and @data-qa-file='MobileOperatorRegionsPopup'"
                        + "and text()='" + city + "']");
            }
            return true;
        });
    }

    public void changeRegion(String city) {
        click("//div[contains(@class,'title') and @data-qa-file='MvnoRegionConfirmation']");
        click("//*[contains(@class,'_region_') and @data-qa-file='MobileOperatorRegionsPopup']"
                + "/*[contains(text(),'" + city + "')]", true);
    }

    public boolean checkRegion(String city) {
        String region = getText("//div[contains(@class,'title') and @data-qa-file='MvnoRegionConfirmation']");
        return region.contains(city);
    }

    public String getAmountSale() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return getText("//*[@data-qa-file='FormFieldWrapper']/*[contains(@class,'amountTitle')]");
    }

    public void chooseMaxFields() {
        Select.driver = getDriver();
        CheckBox.driver = getDriver();

        Select calls = new Select("Звонки", "Безлимит");
        Select internet = new Select("Интернет", "Безлимит");

        calls.choose();
        internet.choose();

        CheckBox sms = new CheckBox("SMS");
        CheckBox modem = new CheckBox("модем");

        sms.setActive(true);
        modem.setActive(true);
    }

    public void chooseMinFields() {
        Select.driver = getDriver();
        CheckBox.driver = getDriver();
        Button.driver = getDriver();

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
}
