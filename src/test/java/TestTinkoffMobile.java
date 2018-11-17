import app.BaseRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import pages.GooglePage;
import pages.GoogleResultPage;
import pages.tinkoffTariffPage.Button;
import pages.tinkoffTariffPage.TinkoffTariffPage;

import static org.junit.Assert.assertEquals;

public class TestTinkoffMobile extends BaseRunner {

    @Test
    public void SwapBetweenPages() {
        GooglePage googlePage = new GooglePage(app);
        googlePage.open();
        googlePage.openSearchResultsPageByRequest("мобайл тинькофф");
        googlePage.clickSomeInDropdown("тарифы");

        GoogleResultPage googleResultPage = new GoogleResultPage(app);
        String initTitle = googleResultPage.getWindowHandle();
        googleResultPage.clickRow("Тарифы Тинькофф Мобайл");
        googleResultPage.checkWindowHandle("Тарифы Тинькофф Мобайл");

        assertEquals("https://www.tinkoff.ru/mobile-operator/tariffs/",
                app.getCurrentUrl());

        app.switchToWindow(initTitle);
        app.close();
    }

    @Test
    public void ChangeRegion() {
        TinkoffTariffPage tinkoffTariffPage = new TinkoffTariffPage(app);
        tinkoffTariffPage.open();
        tinkoffTariffPage.chooseRegion("Москва");

        Assert.assertTrue(tinkoffTariffPage.checkRegion("Москва"));
        app.update();
        Assert.assertTrue(tinkoffTariffPage.checkRegion("Москва"));

        String moscowSale = tinkoffTariffPage.getAmountSale();

        tinkoffTariffPage.changeRegion("Краснодар");
        Assert.assertTrue(tinkoffTariffPage.checkRegion("Краснодар"));
        String krasnodarSale = tinkoffTariffPage.getAmountSale();

        Assert.assertTrue(!krasnodarSale.equals(moscowSale));

        tinkoffTariffPage.chooseMaxFields(app);
        krasnodarSale = tinkoffTariffPage.getAmountSale();
        tinkoffTariffPage.changeRegion("Москва");
        tinkoffTariffPage.chooseMaxFields(app);
        moscowSale = tinkoffTariffPage.getAmountSale();

        assertEquals(krasnodarSale, moscowSale);
    }

    @Test
    public void DisableButton() {
        Button button = new Button(app);
        TinkoffTariffPage tinkoffPage = new TinkoffTariffPage(app);
        tinkoffPage.open();

        tinkoffPage.chooseMinFields(app);
        char ruble = '\u20BD';
        Assert.assertTrue(tinkoffPage.getAmountSale().contains("0 " + ruble));
        Assert.assertTrue(button.isDisable());
    }

    @After
    public void tearDown() {
        app.quit();
    }
}
