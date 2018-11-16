import app.BaseRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import pages.GooglePage;
import pages.GoogleResultPage;
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
        TinkoffTariffPage tinkoffPage = new TinkoffTariffPage(app);
        tinkoffPage.open();
        tinkoffPage.chooseRegion("Москва");

        Assert.assertTrue(tinkoffPage.checkRegion("Москва"));
        app.update();
        Assert.assertTrue(tinkoffPage.checkRegion("Москва"));

        String moscowSale = tinkoffPage.getAmountSale();

        tinkoffPage.changeRegion("Краснодар");
        Assert.assertTrue(tinkoffPage.checkRegion("Краснодар"));
        String krasnodarSale = tinkoffPage.getAmountSale();

        Assert.assertTrue(!krasnodarSale.equals(moscowSale));

        tinkoffPage.chooseMaxFields();
        krasnodarSale = tinkoffPage.getAmountSale();
        tinkoffPage.changeRegion("Москва");
        tinkoffPage.chooseMaxFields();
        moscowSale = tinkoffPage.getAmountSale();

        assertEquals(krasnodarSale, moscowSale);
    }

    @Test
    public void DisableButton() {
        TinkoffTariffPage tinkoffPage = new TinkoffTariffPage(app);
        tinkoffPage.open();

        tinkoffPage.chooseMinFields();
        char ruble = '\u20BD';
        Assert.assertTrue(tinkoffPage.getAmountSale().contains("0 " + ruble));
        Assert.assertTrue(pages.tinkoffTariffPage.Button.isDisable());
    }

    @After
    public void tearDown() {
        app.quit();
    }
}
