package ch.sbb.player;

import ch.sbb.config.Config;
import ch.sbb.dispatcher.AudioFile;
import ch.sbb.dispatcher.AudioOut;
import ch.sbb.dispatcher.MessageDispatcher;
import ch.sbb.helpers.Helper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class AudioPlayer {

    private static boolean runner = true;
    final static Logger logger = LogManager.getLogger(AudioPlayer.class);
    public final static BlockingQueue<AudioOut> audioplayerqueue = new ArrayBlockingQueue<AudioOut>(100);
    private static Helper helper = new Helper();
    private static Config config = new Config(helper.getConfigFileWithPath());

    public static void main(String[] args) {

        config.readConfig();

        while (runner) {

            //MessageDispatcher md = new MessageDispatcher();
            //md.enqueueMessage("eee");

            if (audioplayerqueue.size() > 0) {

                AudioOut ao = audioplayerqueue.poll();
                logger.info("Peeked Handle: " + ao.getHandle() + " from audioplayerqueue");

                for (AudioFile af : ao.getAudiofilelist()) {
                    logger.info("Filename in af list playing: " + af.getFilename());

                    //Access file on share
                    File audiofile = new File(config.getAudiofilesPath() + "/" + af.getFilename() + config.getAudiofilesExt());
                    AudioInputStream audiostream = null;
                    AudioFormat audioFormat;
                    SourceDataLine sourceLine = null;

                    try {
                        audiostream = AudioSystem.getAudioInputStream(audiofile);
                    } catch (UnsupportedAudioFileException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    audioFormat = audiostream.getFormat();

                    DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
                    try {
                        sourceLine = (SourceDataLine) AudioSystem.getLine(info);
                        sourceLine.open(audioFormat);
                    } catch (LineUnavailableException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    sourceLine.start();

                    int nBytesRead = 0;
                    byte[] abData = new byte[config.getBuffersize()];
                    while (nBytesRead != -1) {
                        try {
                            nBytesRead = audiostream.read(abData, 0, abData.length);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (nBytesRead >= 0) {
                            @SuppressWarnings("unused")
                            int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
                        }
                    }

                    sourceLine.drain();
                    sourceLine.close();

                }

            } else {
                logger.debug("AudioPlayer Queue is empty.");
            }

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }


    private void run() {


    }
}

