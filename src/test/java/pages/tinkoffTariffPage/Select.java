package pages.tinkoffTariffPage;

public class Select extends TinkoffTariffPage {
    private String service;
    private String mode;

    Select() {
        super();
    }

    public Select(app.Application app) {
        super(app);
    }

    Select(String service, String mode, app.Application app) {
        super(app);
        this.service = service;
        this.mode = mode;

        if (service.equals("Интернет"))
            this.service = "internet";
        else if (service.equals("Звонки"))
            this.service = "calls";
    }

    public void choose() {
        String addService = "//*[@name='" + service + "']/../..";
        click(addService
                + "//*[contains(@class,'title-flex-text') and @data-qa-file='UISelectTitle']");
        click(addService
                + "//*[contains(@class,'dropdown') and contains(text(),'" + mode + "')]");

        logger.info("В выпадающем окне " + service
                + "выбран пункт " + mode);
    }

    public String getMode() {
        return mode;
    }
}
