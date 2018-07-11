package ch.sbb.adapter;

import ch.sbb.config.Config;
import ch.sbb.dispatcher.AudioOut;

import java.util.concurrent.BlockingQueue;

public class AgsbWriter extends AgsbAdapter {

    public AgsbWriter(Config config, BlockingQueue<String> handlequeue, BlockingQueue<AudioOut> audioplayerqueue) {
        super(config, handlequeue, audioplayerqueue);
    }
}
