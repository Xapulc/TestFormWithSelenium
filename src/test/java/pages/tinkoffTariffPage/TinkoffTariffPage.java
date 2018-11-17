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
        logger.info("Выбран регион: " + city);
    }

    public void changeRegion(String city) {
        click("//div[contains(@class,'title') and @data-qa-file='MvnoRegionConfirmation']");
        click("//*[contains(@class,'_region_') and @data-qa-file='MobileOperatorRegionsPopup']"
                + "/*[contains(text(),'" + city + "')]", true);
        logger.info("РЕгион изменен на: " + city);
    }

    public boolean checkRegion(String city) {
        String region = getText("//div[contains(@class,'title') and @data-qa-file='MvnoRegionConfirmation']");

        if (region.contains(city)) {
            logger.info("Корректный регион");
            return true;
        } else {
            logger.info("Неправильный регион:\n"
                    + "Актуальный: " + region + "\n"
                    + "Текущий: " + city);
            return false;
        }
    }

    public String getAmountSale() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String amountSale = getText("//*[@data-qa-file='FormFieldWrapper']/*[contains(@class,'amountTitle')]");
        logger.info("Итоговая стоимость: " + amountSale);
        return amountSale;
    }

    public void chooseMaxFields(app.Application app) {
        Select calls = new Select("Звонки", "Безлимит", app);
        Select internet = new Select("Интернет", "Безлимит", app);

        calls.choose();
        internet.choose();

        CheckBox sms = new CheckBox("SMS", app);
        CheckBox modem = new CheckBox("модем", app);

        sms.setActive(true);
        modem.setActive(true);

        logger.info("Выбраны максимальные параметры");
    }

    public void chooseMinFields(app.Application app) {
        Select calls = new Select("Звонки", "0 минут", app);
        Select internet = new Select("Интернет", "0 ГБ", app);

        calls.choose();
        internet.choose();

        CheckBox messenger = new CheckBox("Мессенджеры", app);
        CheckBox socialNetwork = new CheckBox("Социальные сети", app);
        CheckBox music = new CheckBox("Музыка", app);
        CheckBox video = new CheckBox("Видео", app);
        CheckBox sms = new CheckBox("SMS", app);

        messenger.setActive(false);
        socialNetwork.setActive(false);
        music.setActive(false);
        video.setActive(false);
        sms.setActive(false);

        logger.info("Выбраны минимальные параметры");
    }
}
