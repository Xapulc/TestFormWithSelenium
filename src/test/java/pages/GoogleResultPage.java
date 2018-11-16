package pages;

public class GoogleResultPage extends Page {
    GoogleResultPage() {
        super();
    }

    public GoogleResultPage(app.Application app) {
        super(app);
    }

    public void clickRow(String name) {
        click("//*[text()='" + name + "']");
    }
}
