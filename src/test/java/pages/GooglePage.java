package pages;

public class GooglePage extends Page {
    GooglePage() {
        super();
    }

    public GooglePage(app.Application app) {
        super(app);
    }

    public void open() {
        getPage("https://www.google.ru/");
        checkWindowHandle("Google");
    }

    public void openSearchResultsPageByRequest(String request) {
        sendKeys("//form[@role='search']//input[@name='q']", request);
    }

    public void clickSomeInDropdown(String request) {
        click("//form[@name='f']//*[contains(text(),'"
                + request + "')]/..", true);
    }
}
