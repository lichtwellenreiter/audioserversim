package ch.sbb;

import ch.sbb.adapter.AgsbAdapterThread;
import ch.sbb.config.Config;
import ch.sbb.dispatcher.AudioOut;
import ch.sbb.helpers.Helper;
import ch.sbb.player.AudioPlayer;
import ch.sbb.ui.AppStarter;
import org.apache.commons.cli.*;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


/**
 * jdk1.8.0_171
 */

public class AudioServerSim {

    private static Helper helper = new Helper();
    private static Config config;

    public static void main(final String[] args) throws ParseException {

        Options options = new Options();
        options.addOption("c", true, "path to configfile");
        options.addOption("ui", false, "start application ui");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        config = new Config(helper.getConfigFileWithPath());
        config.readConfig();
        helper.printHead();

        /**************************************
         * Declaring Queues for Communication between Threads
         **************************************/
        final BlockingQueue<String> agsboutqueue = new ArrayBlockingQueue<String>(config.getAgsboutqueuedepth());
        final BlockingQueue<AudioOut> audioplayerqueue = new ArrayBlockingQueue<AudioOut>(config.getAudioplayerqueuedepth());

        new Thread() {

            public void run() {
                currentThread().setName("AGSBAdapter");
                AgsbAdapterThread.main(args, agsboutqueue, audioplayerqueue);
            }
        }.start();

        new Thread() {
            public void run() {
                currentThread().setName("AudioPlayer");
                AudioPlayer.main(args, agsboutqueue, audioplayerqueue);
            }
        }.start();

        if (cmd.hasOption("ui")) {
            new Thread() {

                public void run() {
                    currentThread().setName("AudioServerUI");
                    this.setName("AudioServerUI");
                    AppStarter.main(args, agsboutqueue, audioplayerqueue);
                }
            }.start();
        }
    }
}
