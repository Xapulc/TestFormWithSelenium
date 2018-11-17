package pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoogleResultPage extends Page {
    GoogleResultPage() {
        super();
    }

    public GoogleResultPage(app.Application app) {
        super(app);
    }

    public void clickRow(String name) {
        click("//*[text()='" + name + "']");
        logger.info("На странице поиска выбран: " + name);
    }
}
