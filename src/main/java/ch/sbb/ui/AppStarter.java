package ch.sbb.ui;


import ch.sbb.dispatcher.AudioOut;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Optional;
import java.util.concurrent.BlockingQueue;

public class AppStarter extends Application {

    private static BlockingQueue<String> agsboutqueue;
    private static BlockingQueue<AudioOut> audioplayerqueue;

	@Override
	public void start(Stage primaryStage) throws Exception {
		PresentationModel pm = new PresentationModel();
		Parent rootPanel = new ApplicationUI(pm);

		pm.setAgsboutqueue(agsboutqueue);
		pm.setAudioplayerqueue(audioplayerqueue);


		Scene scene = new Scene(rootPanel);
		primaryStage.titleProperty().bind(pm.applicationTitleProperty());
		primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest((event -> {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("AudioServerSim beenden?");
            String s = "Sicher, dass der AudioServerSim beendet werden soll?";
            alert.setContentText(s);

            Optional<ButtonType> result = alert.showAndWait();

            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                System.exit(0);
            } else {
                event.consume();
            }

        }));

		primaryStage.show();
	}

	public static void main(String[] args, BlockingQueue<String> agsboutqueue, BlockingQueue<AudioOut> audioplayerqueue) {
	    agsboutqueue = agsboutqueue;
	    audioplayerqueue = audioplayerqueue;

		launch(args);
	}
}
