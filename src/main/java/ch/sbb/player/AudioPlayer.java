package ch.sbb.player;

import ch.sbb.config.Config;
import ch.sbb.dispatcher.AudioFile;
import ch.sbb.dispatcher.AudioOut;
import ch.sbb.helpers.Helper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;


// todo send back to server that the message finished

public class AudioPlayer {

    private final static Logger logger = LogManager.getLogger(AudioPlayer.class);
    private static boolean runner = true;
    private static Helper helper = new Helper();
    private static Config config = new Config(helper.getConfigFileWithPath());
    private static File audiofile;

    public static void main(String[] args, BlockingQueue<String> queue, BlockingQueue<AudioOut> audioplayerqueue) {
        logger.info("AudioPlayer ready for messages");
        config.readConfig();

        while (runner) {

            if (!audioplayerqueue.isEmpty()) {

                AudioOut ao = audioplayerqueue.poll();

                if (ao != null) {
                    String handle = String.valueOf(ao.getHandle());
                    logger.info("Polled Handle: {} from audioplayerqueue", handle);

                    // Determine AudioFile Format from Config File

                    try {
                        switch (config.getAudiofilesExt()) {
                            case ".wav":
                                wavePlayer(ao);
                                break;
                            case ".mp3":
                                mp3Player(ao);
                                break;
                            default:
                                throw new NoAudioPlayerException("No default player");
                        }
                    } catch (NoAudioPlayerException e) {
                        logger.error(e.getStackTrace());
                    }


                    queue.add(handle);
                    logger.info("added {} to handlequeue", handle);
                } else {
                    logger.info("Cannot determine handle");
                }

            } else {
                logger.debug("AudioPlayer Queue size: {}", audioplayerqueue.size());
            }

            try {
                Thread.sleep(config.getWaitAfterAudioOut());
            } catch (InterruptedException e) {
                logger.error(e.getStackTrace());
            }


        }
    }


    private static void mp3Player(AudioOut ao) {
        try {
            for (AudioFile af : ao.getAudiofilelist()) {
                if (af.getFilename().startsWith("FT_")) {
                    audiofile = new File(config.getFlexPath() + "/" + af.getFilename() + config.getAudiofilesExt());
                } else {
                    audiofile = new File(config.getAudiofilesPath() + "/" + af.getFilename() + config.getAudiofilesExt());
                }
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audiofile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void wavePlayer(AudioOut ao) {
        for (AudioFile af : ao.getAudiofilelist()) {
            logger.info("Filename in af list playing: {}", af.getFilename());

            //Access file on share
            if (af.getFilename().startsWith("FT_")) {
                audiofile = new File(config.getFlexPath() + "/" + af.getFilename() + config.getAudiofilesExt());
            } else {
                audiofile = new File(config.getAudiofilesPath() + "/" + af.getFilename() + config.getAudiofilesExt());
            }

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

    }

}

