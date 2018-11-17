package pages.tinkoffTariffPage;

public class Button extends TinkoffTariffPage {
    Button() {
        super();
    }

    public Button(app.Application app) {
        super(app);
    }

    public boolean isDisable() {
        if (isExistElement("//button[@disabled]")) {
            logger.info("Кнопка неактивна");
            return true;
        } else {
            logger.info("Кнопка активна");
            return false;
        }
    }
}
