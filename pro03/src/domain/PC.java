package domain;

public class PC implements Equipment{
    private String model;

    public PC(String model, String display) {
        this.model = model;
        this.display = display;
    }

    private String display;

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String getDescription() {
        return model+display;
    }
}
