package ch.sbb.ui;

import ch.sbb.player.AudioPlayer;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class AudioServerSimUI extends GridPane {

    private Label aqstate;
    private Label aqstatenum;
    public int aqnum;

    public AudioServerSimUI(){
        initializeSelf();
        initializeControls();
        layoutControls();
        setupEventHandlers();
        setupValueChangedListeners();
        setupBindings();
    }

    private void initializeSelf(){
        String stylesheet = getClass().getResource("style.css").toExternalForm();
        getStylesheets().clear();
        getStylesheets().add(stylesheet);
    }

    private void initializeControls(){

        aqstate = new Label("AudioQueueState: ");
        aqstatenum = new Label();
        aqstatenum.setText(String.valueOf(AudioPlayer.audioplayerqueue.size()));

    }

    private void layoutControls(){
        add(aqstate,0,0);
        add(aqstatenum, 1,0);
    }

    private void setupEventHandlers(){

    }

    private void setupValueChangedListeners(){
    }

    private void setupBindings(){

    }
}
