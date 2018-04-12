package ch.sbb.ui;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AudioServerSimApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        Parent rootPanel = new AudioServerSimUI();
        Scene scene = new Scene(rootPanel);

        primaryStage.setTitle("AudioServerSim UI");
        primaryStage.setScene(scene);

        primaryStage.setWidth(700);
        primaryStage.setHeight(450);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}