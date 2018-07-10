package ch.sbb.ui;


import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class ApplicationUI extends StackPane {

    private PresentationModel pm;
    private Button button;

    public ApplicationUI(PresentationModel pm) {
        this.pm = pm;

        initializeSelf();
        initializeControls();
        layoutControls();
        setupEventHandlers();
        setupValueChangedListeners();
        setupBindings();
    }

    private void initializeSelf() {
        String stylesheet = getClass().getResource("style.css").toExternalForm();
        getStylesheets().add(stylesheet);
    }

    private void initializeControls() {
        button = new Button();
    }

    private void layoutControls() {
        getChildren().addAll(button);
    }

    private void setupEventHandlers() {
        button.setOnAction(event -> {
            pm.getLogger().info("WOW");
        });
    }

    private void setupValueChangedListeners() {
    }

    private void setupBindings() {
     button.textProperty().bind(pm.commandNameProperty());
    }
}
