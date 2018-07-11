package ch.sbb.ui;


import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class ApplicationUI extends VBox {

    private PresentationModel pm;
    private Button button;
    private TextArea textArea;

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
        textArea = new TextArea();
    }

    private void layoutControls() {
        getChildren().addAll(button, textArea);
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
