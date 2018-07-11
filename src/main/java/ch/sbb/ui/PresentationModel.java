package ch.sbb.ui;


import ch.sbb.dispatcher.AudioOut;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;

public class PresentationModel {

    private final StringProperty applicationTitle = new SimpleStringProperty("AudioServerSim");
    private final StringProperty commandName = new SimpleStringProperty("Wow!");
    private final Logger logger = LogManager.getLogger(ApplicationUI.class);
    private BlockingQueue<String> agsboutqueue;
    private BlockingQueue<AudioOut> audioplayerqueue;

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

    public Logger getLogger() {
        return logger;
    }

    public BlockingQueue<String> getAgsboutqueue() {
        return agsboutqueue;
    }

    public void setAgsboutqueue(BlockingQueue<String> agsboutqueue) {
        this.agsboutqueue = agsboutqueue;
    }

    public BlockingQueue<AudioOut> getAudioplayerqueue() {
        return audioplayerqueue;
    }

    public void setAudioplayerqueue(BlockingQueue<AudioOut> audioplayerqueue) {
        this.audioplayerqueue = audioplayerqueue;
    }
}