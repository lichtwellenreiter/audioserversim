package ch.sbb.ui;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PresentationModel {

    private final StringProperty applicationTitle = new SimpleStringProperty("JavaFX Application Starter");
    private final StringProperty commandName = new SimpleStringProperty("Wow!");

    public String getApplicationTitle() {
        return applicationTitle.get();
    }

    public StringProperty applicationTitleProperty() {
        return applicationTitle;
    }

    public void setApplicationTitle(String applicationTitle) {
        this.applicationTitle.set(applicationTitle);
    }

    public String getCommandName() {
        return commandName.get();
    }

    public StringProperty commandNameProperty() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName.set(commandName);
    }
}