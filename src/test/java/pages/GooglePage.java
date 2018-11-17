package pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GooglePage extends Page {
    GooglePage() {
        super();
    }

    public GooglePage(app.Application app) {
        super(app);
    }

    public void open() {
        getPage("https://www.google.ru/");
    }

    public void openSearchResultsPageByRequest(String request) {
        sendKeys("//form[@role='search']//input[@name='q']", request);
        logger.info("В окно поиска написано " + request);
    }

    public void clickSomeInDropdown(String request) {
        click("//form[@name='f']//*[contains(text(),'"
                + request + "')]/..", true);
        logger.info("Из выпадающего списка выбран: " + request);
    }
}
