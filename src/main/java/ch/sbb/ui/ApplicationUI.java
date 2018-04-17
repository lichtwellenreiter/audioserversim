package ch.sbb.ui;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApplicationUI extends StackPane {

    final static Logger logger = LogManager.getLogger(ApplicationUI.class);
    private Button button;

    public ApplicationUI() {
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
        button = new Button("this is a button!");
    }

    private void layoutControls() {
        getChildren().addAll(button);
    }

    private void setupEventHandlers() {
        button.setOnAction(event -> this.buttonCheck());
    }

    private void setupValueChangedListeners() {
    }

    private void setupBindings() {
    }

    private void buttonCheck() {
        logger.debug("Test the Button ...");
    }
}
