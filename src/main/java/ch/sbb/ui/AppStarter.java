package ch.sbb.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppStarter extends Application {

    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent rootPanel = new ApplicationUI();
        this.primaryStage = primaryStage;

        Scene scene = new Scene(rootPanel);


        primaryStage.setTitle("JavaFX App");
        primaryStage.setWidth(650);
        primaryStage.setHeight(400);
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(650);
        primaryStage.setScene(scene);

        this.registerStageEventHandler();

        primaryStage.show();
    }

    public void registerStageEventHandler() {
        this.primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
    }
}
